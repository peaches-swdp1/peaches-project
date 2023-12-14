package fi.haagahelia.coolreads.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.haagahelia.coolreads.model.*;
import fi.haagahelia.coolreads.repository.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private AppUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepository.findOneByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPasswordHash(),
				AuthorityUtils.createAuthorityList(user.getRole()));
	}
}