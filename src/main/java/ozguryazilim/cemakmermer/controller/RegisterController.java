package ozguryazilim.cemakmermer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ozguryazilim.cemakmermer.model.User;
import ozguryazilim.cemakmermer.repository.UserRepository;

@Controller
public class RegisterController

{
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/register")
	public String showSingUpForm(Model model) {
		model.addAttribute("user", new User());
		return "signup_form";

	}

	@PostMapping("/process_register")
	public String processRegistration(User user) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoderPassword = encoder.encode(user.getPassword());
		user.setPassword(encoderPassword);
		userRepository.save(user);
		return "register_success";
	}
}
