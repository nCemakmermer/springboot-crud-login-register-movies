package ozguryazilim.cemakmermer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ozguryazilim.cemakmermer.model.User;
import ozguryazilim.cemakmermer.repository.UserRepository;


public class UserDetailManager implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User Not Found !");
		}
		return new UserManager(user);
	}

}
