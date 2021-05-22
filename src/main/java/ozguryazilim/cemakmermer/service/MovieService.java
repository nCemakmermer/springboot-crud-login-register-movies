package ozguryazilim.cemakmermer.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import ozguryazilim.cemakmermer.model.Movie;


public interface MovieService {

	List<Movie> getAllMovies();

	void saveMovie(Movie movie);

	Movie getMovieById(Integer id);

	void deleteMovieById(Integer id);

	

	Page<Movie> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	
	
	@Query(value = "SELECT * FROM Movie m WHERE m.name like %:keyword% or m.type like  %:keyword%",nativeQuery = true)
	 List<Movie> findByKeyword(@Param("keyword") String keyword);
	
	List<Movie> findByNameLikeOrTypeLike(String name, String type);

}
