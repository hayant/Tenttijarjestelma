package tenttijarjestelma;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import entity.Oppilas;
import entity.Tentti;
import entity.Tenttikysymys;
import entity.Tulos;
import entity.Vastaus;
import entity.Vastausvaihtoehto;
import repository.OppilasRepository;
import repository.TulosRepository;
import repository.VastausRepository;

@Component
public class Tarkastus {

	@Autowired
	private VastausRepository vastaukset;

	@Autowired
	private OppilasRepository opiskelijat;
	
	@Autowired
	private TulosRepository tulokset;

	public void arvioiKorjaamattomatVastaukset() {
		List<Vastaus> korjaamattomat = vastaukset.findByMenikoOikein(null);
		for (Vastaus vastaus : korjaamattomat) {
			vastaus.setMenikoOikein(vastaus.getOppilaanVastaus() == vastaus.getVastausvaihtoehto().getOikeaVastaus());
		}
		vastaukset.saveAll(korjaamattomat);
	}

	
	public void arvioiTehtavat(Oppilas opiskelija) {

		Iterable<Vastaus> haetutVastaukset = vastaukset.findByOppilasId(opiskelija.getId());
		List<Vastaus> jonnenVastaukset = new ArrayList<>();
		HashSet<Tenttikysymys> vastatutTenttikysymykset = new HashSet<>();

		List<Tulos> kysymystenTulokset = new ArrayList<>();
		
		for (Vastaus uusiVastaus : haetutVastaukset) {
			vastatutTenttikysymykset.add(uusiVastaus.getVastausvaihtoehto().getTenttikysymys());
		}

		for (Tenttikysymys tenttikysymys : vastatutTenttikysymykset) {

			boolean vastasikoJonneOikein = true;

			for (Vastausvaihtoehto vastausvaihtoehto : tenttikysymys.getVastausvaihtoehdot()) {

				List<Vastaus> jonnenVastaus = vastaukset.findByOppilasIdAndVastausvaihtoehtoId(opiskelija.getId(),
						vastausvaihtoehto.getId());
				if (jonnenVastaus.size() > 0) {
					if (jonnenVastaus.get(0).getOppilaanVastaus() != vastausvaihtoehto.getOikeaVastaus()) {
						vastasikoJonneOikein = false;
					}
				}
			}

			for (Vastausvaihtoehto vastausvaihtoehto : tenttikysymys.getVastausvaihtoehdot()) {

				List<Vastaus> jonnenVastaus = vastaukset.findByOppilasIdAndVastausvaihtoehtoId(opiskelija.getId(),
						vastausvaihtoehto.getId());
				if (jonnenVastaus.size() > 0) {
					if (vastasikoJonneOikein) {
						// jonnenVastaus.get(0).setKokoKysymysOikein(true);
						kysymystenTulokset.add(new Tulos(opiskelija, tenttikysymys, null, true));
					} else {
						kysymystenTulokset.add(new Tulos(opiskelija, tenttikysymys, null, false));
					}

					jonnenVastaukset.add(jonnenVastaus.get(0));
				}
			}
			tulokset.saveAll(kysymystenTulokset);
		}
	}

	public void arvioiTehtavat(Oppilas opiskelija, Tentti tentti) {

		Iterable<Vastaus> haetutVastaukset = vastaukset.findByOppilasId(opiskelija.getId());
		List<Vastaus> jonnenVastaukset = new ArrayList<>();
		HashSet<Tenttikysymys> vastatutTenttikysymykset = new HashSet<>();

		List<Tulos> kysymystenTulokset = new ArrayList<>();

		for (Vastaus uusiVastaus : haetutVastaukset) {
			vastatutTenttikysymykset.add(uusiVastaus.getVastausvaihtoehto().getTenttikysymys());
		}

		for (Tenttikysymys tenttikysymys : vastatutTenttikysymykset) {

			boolean vastasikoJonneOikein = true;

			for (Vastausvaihtoehto vastausvaihtoehto : tenttikysymys.getVastausvaihtoehdot()) {

				List<Vastaus> jonnenVastaus = vastaukset.findByOppilasIdAndVastausvaihtoehtoIdAndTenttiId(opiskelija.getId(),
						vastausvaihtoehto.getId(), tentti.getId());
				if (jonnenVastaus.size() > 0) {
					if (jonnenVastaus.get(0).getOppilaanVastaus() != vastausvaihtoehto.getOikeaVastaus()) {
						vastasikoJonneOikein = false;
					}
				}
			}

			for (Vastausvaihtoehto vastausvaihtoehto : tenttikysymys.getVastausvaihtoehdot()) {

				List<Vastaus> jonnenVastaus = vastaukset.findByOppilasIdAndVastausvaihtoehtoIdAndTenttiId(opiskelija.getId(),
						vastausvaihtoehto.getId(), tentti.getId());
				if (jonnenVastaus.size() > 0) {
					if (vastasikoJonneOikein) {
						kysymystenTulokset.add(new Tulos(opiskelija, tenttikysymys, tentti, true));
					} else {
						kysymystenTulokset.add(new Tulos(opiskelija, tenttikysymys, tentti, false));
					}

					jonnenVastaukset.add(jonnenVastaus.get(0));
				}
			}
			tulokset.saveAll(kysymystenTulokset);
		}
	}
	
	public void arvioiTehtavat() {

		Iterable<Oppilas> kaikkiOpiskelijat = opiskelijat.findAll();

		for (Oppilas opiskelija : kaikkiOpiskelijat) {

			arvioiTehtavat(opiskelija);
		}
	}

	public int getOikeidenVastaustenMaara(Oppilas opiskelija) {

		Iterable<Tulos> oikeatVastaukset = tulokset.findByOppilasIdAndKokoKysymysOikein(opiskelija.getId(),
				true);

		// HashSet<Tenttikysymys> vastaustenTenttikysymykset = new HashSet<>();

		int oikeidenVastaustenMaara = 0;
		for (Tulos uusiTulos : oikeatVastaukset) {
			oikeidenVastaustenMaara++;
		}

		return oikeidenVastaustenMaara;
	}
	
	public int getOikeidenVastaustenMaara(Oppilas opiskelija, Tentti tentti) {

		Iterable<Tulos> oikeatVastaukset = tulokset.findByOppilasIdAndTenttiIdAndKokoKysymysOikein(opiskelija.getId(),
				tentti.getId(), true);

		int oikeidenVastaustenMaara = 0;

		for (Tulos uusiVastaus : oikeatVastaukset) {
			oikeidenVastaustenMaara++;
		}

		return oikeidenVastaustenMaara;
	}
	
}
