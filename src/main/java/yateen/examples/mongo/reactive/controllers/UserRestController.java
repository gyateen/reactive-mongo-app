package yateen.examples.mongo.reactive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import yateen.examples.mongo.models.User;
import yateen.examples.mongo.models.UserCapped;
import yateen.examples.mongo.reactive.service.UserService;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	UserService userService;
	
	@GetMapping(path="")
	public Flux<User> getUsers()
	{
		return userService.getAllUsers();
	}
	
	@GetMapping(path="/tail/users", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
	public Flux<UserCapped> getUsersAndTail()
	{
		return userService.getAllUsersAndTail();
	}
	

	@GetMapping(path="/{user_name}")
	public Mono<User> getUser(@PathVariable("user_name") String userName)
	{
		return userService.getUser(userName);
	}
	
	@PutMapping(path="/{user_name}")
	public Mono<User> updateUser(@PathVariable("user_name") String userName, @RequestBody User user)
	{
		return userService.findAndUpdateUser(userName, user);
	}
	
	@DeleteMapping(path="/{user_name}")
	public Mono<Void> deleteUser(@PathVariable("user_name") String userName)
	{
		return userService.findAndDeleteUser(userName);
	}
	
	@PostMapping(path="")
	public Mono<User> postUser(@RequestBody User user)
	{
		return userService.postUser(user);
	}
}
