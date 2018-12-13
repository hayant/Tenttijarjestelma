package tenttijarjestelma;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource(collectionResourceRel = "oppilaat", path = "oppilaat")
@PreAuthorize("hasRole('ROLE_OPETTAJA')")
public interface OppilasRepository extends CrudRepository<Oppilas, Long> {

	@Override
	@PreAuthorize("#oppilas?.ope == null or #oppilas?.ope?.name == authentication?.name")
	Oppilas save(@Param("oppilas") Oppilas oppilas);

	@Override
	@PreAuthorize("@oppilasRepository.findById(#id)?.ope?.name == authentication?.name")
	void deleteById(@Param("id") Long id);
	
	@Override
	@PreAuthorize("denyAll")
	void delete(@Param("oppilas") Oppilas oppilas);
	
	@Override							
	@Query(value ="select * from oppilas where oppilas.id=:id and oppilas.ope_id=(select opettaja.id from opettaja where opettaja.name=?#{principal.username})", nativeQuery = true)
	Optional<Oppilas> findById(@Param("id") Long id);
	
	@Override
	@Query(value="select * from oppilas where oppilas.ope_id=(select opettaja.id from opettaja where opettaja.name = ?#{ principal.username })",
			nativeQuery = true)
	Iterable<Oppilas> findAll();
}
