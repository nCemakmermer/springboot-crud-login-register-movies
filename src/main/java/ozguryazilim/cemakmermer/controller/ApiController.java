package ozguryazilim.cemakmermer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ozguryazilim.cemakmermer.model.Movie;
import ozguryazilim.cemakmermer.repository.MovieRepository;



@Controller
public class ApiController {
	@Autowired
	private MovieRepository movieRepository;
	@GetMapping("")
	public String viewHomePage(Model model) {
		List<Movie> listMovie = movieRepository.findAll();
		model.addAttribute("listMovies", listMovie);
		return "index";
	}

}
