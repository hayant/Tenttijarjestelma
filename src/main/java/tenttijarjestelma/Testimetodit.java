package tenttijarjestelma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Testimetodit {
	
	@Autowired
	private TenttiRepository tentit;
	
	@Autowired
	private OppilasRepository opiskelijat;
	
	@Autowired
	private VastausRepository vastaukset;

	@Autowired
	private OpettajaRepository opettajat;
	
	public void luoTestidata() {
		
		Iterable<Opettaja> tietokantaOpettajat = opettajat.findAll();
		List<Opettaja> kaikkiOpettajat = new ArrayList<>();
		
		for(Opettaja opettaja : tietokantaOpettajat) {
			kaikkiOpettajat.add(opettaja);
		}
		
		Tentti t = new Tentti();
		t.setNimi("Yleistieto I");
		List<Tentti> kaikkiTentit = new ArrayList<>();
		kaikkiTentit.add(t);
		
		Tentti t2 = new Tentti();
		t2.setNimi("Rajatieto I");
		List<Tentti> kaikkiTentit2 = new ArrayList<>();
		kaikkiTentit2.add(t2);
	
		//tentit.save(t);
		//Iterable<Tentti> kaikkiTentit = tentit.findAll();
		//for(Tentti tentti : kaikkiTentit) {
		//	t = tentti;
		//}
		
		Tenttikysymys k1 = new Tenttikysymys();
		k1.setKysymys("Paljonko on 1+2?");
		k1.setTentit(kaikkiTentit);
		Tenttikysymys k2 = new Tenttikysymys();
		k2.setKysymys("Mistä kuu on tehty?");
		k2.setTentit(kaikkiTentit);
		Tenttikysymys k3 = new Tenttikysymys();
		k3.setKysymys("Mikä maa, mikä valuutta?");
		k3.setTentit(kaikkiTentit2);

		t.setTenttikysymykset(Arrays.asList(k1, k2, k3));

		Vastausvaihtoehto v1 = new Vastausvaihtoehto();
		v1.setVaihtoehto("1");
		v1.setTenttikysymys(k1);
		v1.setOikeaVastaus(false);

		Vastausvaihtoehto v2 = new Vastausvaihtoehto();
		v2.setVaihtoehto("2");
		v2.setTenttikysymys(k1);
		v2.setOikeaVastaus(false);

		Vastausvaihtoehto v3 = new Vastausvaihtoehto();
		v3.setVaihtoehto("3");
		v3.setTenttikysymys(k1);
		v3.setOikeaVastaus(true);

		Vastausvaihtoehto v4 = new Vastausvaihtoehto();
		v4.setVaihtoehto("4");
		v4.setTenttikysymys(k1);
		v4.setOikeaVastaus(false);

		k1.setVastausvaihtoehdot(Arrays.asList(v1, v2, v3, v4));

		Vastausvaihtoehto v21 = new Vastausvaihtoehto();
		v21.setVaihtoehto("Juustosta");
		v21.setTenttikysymys(k2);
		v21.setOikeaVastaus(false);

		Vastausvaihtoehto v22 = new Vastausvaihtoehto();
		v22.setVaihtoehto("Kuttaperkasta");
		v22.setTenttikysymys(k2);
		v22.setOikeaVastaus(false);

		Vastausvaihtoehto v23 = new Vastausvaihtoehto();
		v23.setVaihtoehto("Jostain muusta");
		v23.setTenttikysymys(k2);
		v23.setOikeaVastaus(true);

		Vastausvaihtoehto v24 = new Vastausvaihtoehto();
		v24.setVaihtoehto("Kyseessä on valeuutinen eli kuuta ei ole oikeasti edes olemassa");
		v24.setTenttikysymys(k2);
		v24.setOikeaVastaus(false);

		k2.setVastausvaihtoehdot(Arrays.asList(v21, v22, v23, v24));

		Vastausvaihtoehto v31 = new Vastausvaihtoehto();
		v31.setVaihtoehto("Englanti ja Euro");
		v31.setTenttikysymys(k3);
		v31.setOikeaVastaus(false);

		Vastausvaihtoehto v32 = new Vastausvaihtoehto();
		v32.setVaihtoehto("Suomi ja punta");
		v32.setTenttikysymys(k3);
		v32.setOikeaVastaus(false);

		Vastausvaihtoehto v33 = new Vastausvaihtoehto();
		v33.setVaihtoehto("Kanada");
		v33.setTenttikysymys(k3);
		v33.setOikeaVastaus(true);

		k3.setVastausvaihtoehdot(Arrays.asList(v31, v32, v33));

		// tentit.save(t);
		tentit.saveAll(kaikkiTentit);
		tentit.saveAll(kaikkiTentit2);

		kaikkiTentit = tentit.findByNimi("Yleistieto I");
		kaikkiTentit2 = tentit.findByNimi("Rajatieto I");

		Oppilas o = new Oppilas();
		o.setEtunimi("Jonne");
		o.setSukunimi("Juonikas");
		o.setOpe(kaikkiOpettajat.get(0));
		opiskelijat.save(o);

		Oppilas o2 = new Oppilas();
		o2.setEtunimi("Jenna");
		o2.setSukunimi("Kakkonen");
		o2.setOpe(kaikkiOpettajat.get(0));
		opiskelijat.save(o2);

		Vastaus vastaus = new Vastaus();
		vastaus.setOppilas(o);
		vastaus.setVastausvaihtoehto(v1);
		vastaus.setOppilaanVastaus(false);
		vastaus.setVastausvaihtoehto_id(v1.getId());
		vastaus.setOppilas_id(o.getId());
		vastaus.setTentti(kaikkiTentit.get(0));
		vastaus.setTentti_id(kaikkiTentit.get(0).getId());
		
		Vastaus vastaus2 = new Vastaus();
		vastaus2.setOppilas(o);
		vastaus2.setVastausvaihtoehto(v2);
		vastaus2.setOppilaanVastaus(false);
		vastaus2.setVastausvaihtoehto_id(v2.getId());
		vastaus2.setOppilas_id(o.getId());
		vastaus2.setTentti(kaikkiTentit.get(0));
		vastaus2.setTentti_id(kaikkiTentit.get(0).getId());

		Vastaus vastaus3 = new Vastaus();
		vastaus3.setOppilas(o);
		vastaus3.setVastausvaihtoehto(v3);
		vastaus3.setOppilaanVastaus(true);
		vastaus3.setVastausvaihtoehto_id(v3.getId());
		vastaus3.setOppilas_id(o.getId());
		vastaus3.setTentti(kaikkiTentit.get(0));
		vastaus3.setTentti_id(kaikkiTentit.get(0).getId());

		Vastaus vastaus4 = new Vastaus();
		vastaus4.setOppilas(o);
		vastaus4.setVastausvaihtoehto(v4);
		vastaus4.setOppilaanVastaus(false);
		vastaus4.setVastausvaihtoehto_id(v4.getId());
		vastaus4.setOppilas_id(o.getId());
		vastaus4.setTentti(kaikkiTentit.get(0));
		vastaus4.setTentti_id(kaikkiTentit.get(0).getId());

		Vastaus vastaus21 = new Vastaus();
		vastaus21.setOppilas(o);
		vastaus21.setVastausvaihtoehto(v21);
		vastaus21.setOppilaanVastaus(false);
		vastaus21.setVastausvaihtoehto_id(v21.getId());
		vastaus21.setOppilas_id(o.getId());
		vastaus21.setTentti(kaikkiTentit.get(0));
		vastaus21.setTentti_id(kaikkiTentit.get(0).getId());

		Vastaus vastaus22 = new Vastaus();
		vastaus22.setOppilas(o);
		vastaus22.setVastausvaihtoehto(v22);
		vastaus22.setOppilaanVastaus(false);
		vastaus22.setVastausvaihtoehto_id(v22.getId());
		vastaus22.setOppilas_id(o.getId());
		vastaus22.setTentti(kaikkiTentit.get(0));
		vastaus22.setTentti_id(kaikkiTentit.get(0).getId());

		Vastaus vastaus23 = new Vastaus();
		vastaus23.setOppilas(o);
		vastaus23.setVastausvaihtoehto(v23);
		vastaus23.setOppilaanVastaus(false);
		vastaus23.setVastausvaihtoehto_id(v23.getId());
		vastaus23.setOppilas_id(o.getId());
		vastaus23.setTentti(kaikkiTentit.get(0));
		vastaus23.setTentti_id(kaikkiTentit.get(0).getId());

		Vastaus vastaus24 = new Vastaus();
		vastaus24.setOppilas(o);
		vastaus24.setVastausvaihtoehto(v24);
		vastaus24.setOppilaanVastaus(true);
		vastaus24.setVastausvaihtoehto_id(v24.getId());
		vastaus24.setOppilas_id(o.getId());
		vastaus24.setTentti(kaikkiTentit.get(0));
		vastaus24.setTentti_id(kaikkiTentit.get(0).getId());

		Vastaus vastaus31 = new Vastaus();
		vastaus31.setOppilas(o2);
		vastaus31.setVastausvaihtoehto(v31);
		vastaus31.setOppilaanVastaus(true);
		vastaus31.setVastausvaihtoehto_id(v31.getId());
		vastaus31.setOppilas_id(o2.getId());
		vastaus31.setTentti(kaikkiTentit2.get(0));
		vastaus31.setTentti_id(kaikkiTentit2.get(0).getId());

		Vastaus vastaus32 = new Vastaus();
		vastaus32.setOppilas(o2);
		vastaus32.setVastausvaihtoehto(v32);
		vastaus32.setOppilaanVastaus(false);
		vastaus32.setVastausvaihtoehto_id(v32.getId());
		vastaus32.setOppilas_id(o2.getId());
		vastaus32.setTentti(kaikkiTentit2.get(0));
		vastaus32.setTentti_id(kaikkiTentit2.get(0).getId());

		Vastaus vastaus33 = new Vastaus();
		vastaus33.setOppilas(o2);
		vastaus33.setVastausvaihtoehto(v33);
		vastaus33.setOppilaanVastaus(false);
		vastaus33.setVastausvaihtoehto_id(v33.getId());
		vastaus33.setOppilas_id(o2.getId());
		vastaus33.setTentti(kaikkiTentit2.get(0));
		vastaus33.setTentti_id(kaikkiTentit2.get(0).getId());

		vastaukset.save(vastaus);
		vastaukset.save(vastaus2);
		vastaukset.save(vastaus3);
		vastaukset.save(vastaus4);
		vastaukset.save(vastaus21);
		vastaukset.save(vastaus22);
		vastaukset.save(vastaus23);
		vastaukset.save(vastaus24);
		vastaukset.save(vastaus31);
		vastaukset.save(vastaus32);
		vastaukset.save(vastaus33);

	}

	public void testaaHakua() {

		Long jonnenId = 19L;
		
		Iterable<Vastaus> haetutVastaukset = vastaukset.findByOppilasId(jonnenId);
		List<Vastaus> jonnenVastaukset = new ArrayList<>();
		List<Vastausvaihtoehto> jonnenVastausvaihtoehdot = new ArrayList<>();
		HashSet<Tenttikysymys> vastatutTenttikysymykset = new HashSet<>();

		for (Vastaus uusiVastaus : haetutVastaukset) {
			jonnenVastaukset.add(uusiVastaus);
			vastatutTenttikysymykset.add(uusiVastaus.getVastausvaihtoehto().getTenttikysymys());
			jonnenVastausvaihtoehdot.add(uusiVastaus.getVastausvaihtoehto());
		}

		int opiskelijanPisteet = 0;

		for (Tenttikysymys tenttikysymys : vastatutTenttikysymykset) {
			boolean vastasikoJonneOikein = true;
			System.out.println("Kysymys: " + tenttikysymys.getKysymys());
			for (Vastausvaihtoehto vastausvaihtoehto : tenttikysymys.getVastausvaihtoehdot()) {
				System.out.println("Vastausvaihtoehto: " + vastausvaihtoehto.getVaihtoehto());
				System.out.print("        Oppilaan vastaus: ");
				List<Vastaus> jonnenVastaus = vastaukset.findByOppilasIdAndVastausvaihtoehtoId(jonnenId,
						vastausvaihtoehto.getId());
				if (jonnenVastaus.size() > 0) {

					if (jonnenVastaus.get(0).getOppilaanVastaus()) {
						System.out.println("Tosi");
					} else {
						System.out.println("Epätosi");
					}
					if (jonnenVastaus.get(0).getOppilaanVastaus() != vastausvaihtoehto.getOikeaVastaus()) {
						vastasikoJonneOikein = false;
					}
				}
			}
			System.out.print("Oppilas vastasi kysymykseen ");
			if (vastasikoJonneOikein) {
				System.out.println("oikein.");
				opiskelijanPisteet++;
			} else {
				System.out.println("väärin.");
			}
			System.out.println("-------------------------------------------------");
			System.out.println();
		}
		System.out.println("Oppilaan yhteispistemäärä: " + opiskelijanPisteet);
	}
	
	public void testaaHakua(Oppilas oppilas) {
		
	}
}
