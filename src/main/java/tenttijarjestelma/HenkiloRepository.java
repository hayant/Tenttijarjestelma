package tenttijarjestelma;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported=true)
public interface HenkiloRepository extends PagingAndSortingRepository<Opettaja, Long> {

	List<Opettaja> findBySukunimi(@Param("name") String name);

}
