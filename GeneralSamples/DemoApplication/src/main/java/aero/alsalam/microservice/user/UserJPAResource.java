package aero.alsalam.microservice.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	

	@GetMapping(path="/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}
	
	@GetMapping(path="/jpa/users/{userid}")
	public EntityModel<User> retrieveUser(@PathVariable int userid) {
		Optional<User> user= userRepository.findById(userid);
		if(user.isEmpty())
			throw new UserNotFoundException("user id -- "+userid);
		
		
		EntityModel<User> resource = EntityModel.of(user.get());
		Link link= WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users");
        resource.add(link);
        return resource;
	}
	
	@PostMapping(path="/jpa/users")
	public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
		User savedUser=userRepository.save(user);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/jpa/users/{userid}")
	public User deleteUser(@PathVariable int userid) {
		Optional<User> user= userRepository.findById(userid);
		if(user.isEmpty())
			throw new UserNotFoundException("user id -- "+userid);
		else userRepository.deleteById(userid);
		return user.get();
		 
	}
	
	@GetMapping(path="/jpa/users/{id}/posts")
	public List<Post> retrieveAllUserPosts(@PathVariable int id) {
		Optional<User> user= userRepository.findById(id);
		if(user.isEmpty())
			throw new UserNotFoundException("user id -- "+id);
		return user.get().getPosts();
	}
	
	@PostMapping(path="/jpa/users/{id}/posts")
	public ResponseEntity<?> savePost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> userOptional= userRepository.findById(id);
		if(userOptional.isEmpty())
			throw new UserNotFoundException("user id -- "+id);
		User user=userOptional.get();
		post.setUser(user);
		Post savedPost=postRepository.save(post);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping(path="/jpa/users/{userid}/posts/{postid}")
	public Post retrievePostOfAUser(@PathVariable int userid, @PathVariable int postid) {
		Optional<User> user= userRepository.findById(userid);
		if(user.isEmpty())
			throw new UserNotFoundException("user id -- "+userid);
		List<Post> posts=user.get().getPosts();
		for(Post post:posts) {
			if (post.getId()==postid) {
				return post;
			}
		}
		throw new PostNotFoundException("post id -- "+postid);
	}
}
