package ca.sheridancollege.security;

import java.util.*;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.beans.Role;
import ca.sheridancollege.repositories.RoleRepository;
import ca.sheridancollege.repositories.UserRepository;

//1.implement the methods. Add service annotation
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	//3. autowired the repos
	@Autowired
	private RoleRepository roleRepos;
	@Autowired
	private UserRepository userRepos;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//2.Define package before class
		// find the user in the database
		ca.sheridancollege.beans.User user = userRepos.findByUsername(username);
		//3.if the user does'nt exist then stop
		if(user == null) {
		System.out.println("User not found: " + username);
		throw new UsernameNotFoundException("User " + username + "was not found");		
		}
		
		//4. change the list of the user's roles into a list of grantedauthorities
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		for(Role role : user.getRoles()) 
		{
			//System.out.println(user.getRoles());
			grantList.add(new SimpleGrantedAuthority(role.getRolename()));
		}
		//5. Create a spring user based on the info above =>the user import must be from springframework
		UserDetails userDetails = (UserDetails) new User(user.getUsername(),user.getEncryptedpassword(), grantList); 
		
		return userDetails;
	}

}
