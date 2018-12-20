package repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import entity.Tenttikysymys;

@RepositoryRestResource(collectionResourceRel = "kysymykset", path = "kysymykset")
public interface TenttikysymysRepository extends PagingAndSortingRepository<Tenttikysymys, Long> {

	List<Tenttikysymys> findById(String id);

}
