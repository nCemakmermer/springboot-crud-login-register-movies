package ozguryazilim.cemakmermer.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;

import ozguryazilim.cemakmermer.model.Movie;
import ozguryazilim.cemakmermer.repository.MovieRepository;


@Service
public class MovieManager implements MovieService {

	
	private MovieRepository movieRepository;
	
	@Autowired
	public MovieManager(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public List<Movie> getAllMovies() {

		return movieRepository.findAll();
	}

	@Override
	public void saveMovie(Movie movie) {
		this.movieRepository.save(movie);

	}

	@Override
	public Movie getMovieById(Integer id) {
		Optional<Movie> optional = movieRepository.findById(id);
		Movie movie = null;
		if (optional.isPresent()) {
			movie = optional.get();
		} else {
			throw new RuntimeException(" Movie not found for id :: " + id);
		}
		return movie;
	}

	@Override
	public void deleteMovieById(Integer id) {
		this.movieRepository.deleteById(id);

	}

	@Override
	public Page<Movie> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.movieRepository.findAll(pageable);
	}

	@Override
	public List<Movie> findByKeyword(String keyword) {
		return movieRepository.findByKeyword(keyword);
	}

	@Override
	public List<Movie> findByNameLikeOrTypeLike(String name, String type) {
		return this.movieRepository.findMovieByNameContainingIgnoreCaseOrTypeContainingIgnoreCase(name,type);
	}

	
	

}
