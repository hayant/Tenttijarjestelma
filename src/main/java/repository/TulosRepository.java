package repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import entity.Tulos;

@RepositoryRestResource(exported = true)
public interface TulosRepository extends PagingAndSortingRepository<Tulos, Long> {

	List<Tulos> findByOppilasIdAndKokoKysymysOikein(Long oppilasId, Boolean kokoKysymysOikein);
	List<Tulos> findByOppilasIdAndTenttiIdAndKokoKysymysOikein(Long opiskelija_id,
			Long tentti_id, Boolean arvo);
}