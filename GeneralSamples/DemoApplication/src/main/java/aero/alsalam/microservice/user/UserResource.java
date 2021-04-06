package aero.alsalam.microservice.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class UserResource {
	@Autowired
	UserDAOService userDAOService;

	@GetMapping(path="/users")
	public List<User> retrieveAllUsers() {
		return userDAOService.findAll();
	}
	
	@GetMapping(path="/users/{userid}")
	public EntityModel<User> retrieveUser(@PathVariable int userid) {
		User user= userDAOService.findOne(userid);
		if(user==null)
			throw new UserNotFoundException("user id -- "+userid);
		
		
		EntityModel<User> resource = EntityModel.of(user);
		Link link= WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users");
        resource.add(link);
        return resource;
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
		User savedUser=userDAOService.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/users/{userid}")
	public User deleteUser(@PathVariable int userid) {
		User user= userDAOService.deleteById(userid);
		if(user==null)
			throw new UserNotFoundException("user id -- "+userid);
		return user;
	}
}
