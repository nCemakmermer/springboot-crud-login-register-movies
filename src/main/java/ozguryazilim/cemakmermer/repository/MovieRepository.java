package ozguryazilim.cemakmermer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ozguryazilim.cemakmermer.model.Movie;



@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	@Query(value = "SELECT m FROM Movie m WHERE m.name like %:keyword% or m.type like  %:keyword%", nativeQuery = true)
	List<Movie> findByKeyword(@Param("keyword") String keyword);

	List<Movie> findMovieByNameContainingIgnoreCaseOrTypeContainingIgnoreCase(@Param("name") String name,
			@Param("type") String type);

}
