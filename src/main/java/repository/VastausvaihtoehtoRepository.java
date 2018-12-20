package repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import entity.Vastausvaihtoehto;

@RepositoryRestResource(collectionResourceRel = "vastausvaihtoehdot", path = "vastausvaihtoehdot")
public interface VastausvaihtoehtoRepository extends PagingAndSortingRepository<Vastausvaihtoehto, Long> {

}
