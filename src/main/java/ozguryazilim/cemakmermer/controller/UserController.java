package ozguryazilim.cemakmermer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ozguryazilim.cemakmermer.model.Movie;
import ozguryazilim.cemakmermer.model.User;
import ozguryazilim.cemakmermer.repository.MovieRepository;
import ozguryazilim.cemakmermer.repository.UserRepository;
import ozguryazilim.cemakmermer.service.MovieService;

import java.util.*;


@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MovieService movieService;
	@Autowired
	private MovieRepository movieRepository;

	@GetMapping("/users")
	public String viewUserList(Model model,String keyword) {
		List<User> listUser = userRepository.findAll();
		model.addAttribute("listUsers", listUser);
		List<Movie> listMovie = movieRepository.findAll();
		model.addAttribute("listMovies", listMovie);
		if (keyword != null) {
			// model.addAttribute("users",movieService.findByKeyword(keyword));
			model.addAttribute("listMovies", movieService.findByNameLikeOrTypeLike(keyword, keyword));

		} else {
			model.addAttribute("listMovies", listMovie);

		}
		return "users";
	}
	@GetMapping("/showNewMovieForm")
	public String showNewMovieForm(Model model) {
		Movie movie = new Movie();
		model.addAttribute("movie", movie);
		return "new_movie";
	}

	@PostMapping("/saveMovie")
	public String saveMovie(@ModelAttribute("movie") Movie movie) {

		movieService.saveMovie(movie);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") Integer id, Model model) {

		Movie movie = movieService.getMovieById(id);

		model.addAttribute("movie", movie);
		return "update_movie";
	}

	@GetMapping("/deleteMovie/{id}")
	public String deleteMovie(@PathVariable(value = "id") Integer id) {

		this.movieService.deleteMovieById(id);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model) {
		int pageSize = 5;

		Page<Movie> page = movieService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Movie> listMovies = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listMovies", listMovies);
		return "index";
	}
}
