package net.codejava.controller;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.codejava.apik.RoleRepository;
import net.codejava.apik.UserRepository;
import net.codejava.apik.UserService;
import net.codejava.user.ERole;
import net.codejava.user.Role;
import net.codejava.user.User;
import net.peter.Dto.UserDTO;

@RestController
public class UserApi {

	  @Autowired
	UserRepository userRepository;
	  @Autowired
	  RoleRepository roleRepository;

	@Autowired 
	private UserService service;
	
	 @Autowired
	  PasswordEncoder encoder;

	
//	@PutMapping("/users")
//	public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
//		User createdUser = service.save(user);
//		URI uri = URI.create("/users/" + createdUser.getId());
//		UserDTO userDto = new UserDTO(createdUser.getId(), createdUser.getEmail() );
//		return ResponseEntity.created(uri).body(userDto);
//	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signupUser(@RequestBody @Valid UserDTO userDto) {

	    // Create new user's account
	    User user = new User(userDto.getEmail(), 
	               encoder.encode(encoder.encode(userDto.getPassword())));

		Set<String> strRoles = userDto.getRole();
		Set<Role> roles = new HashSet<>();


	    if (strRoles == null) {
	      Role userRole = roleRepository.findByName("ROLE_USER");
	      roles.add(userRole);
	    } else {
	      strRoles.forEach(role -> {
	        switch (role) {
	        case "admin":
	          Role adminRole = roleRepository.findByName("ROLE_ADMIN");
	          roles.add(adminRole);

	          break;
	        case "customer":
	          Role modRole = roleRepository.findByName("ROLE_CUSTOMER");
	    
	          roles.add(modRole);

	          break;
	        default:
	          Role userRole = roleRepository.findByName("ROLE_USER");
	          roles.add(userRole);
	        }
	      });
	    }
		
//	    Role userRole = roleRepository.findByName("ROLE_ADMIN");
//	    roles.add(userRole);
	    
	    System.out.println(user.toString());
   
	//    System.out.println(userRole.toString());
	    
	    user.setRoles(roles);
	    
	    userRepository.save(user);

	    return ResponseEntity.ok("User registered successfully!");

	}
}	
	

	


