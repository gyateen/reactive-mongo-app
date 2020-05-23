package yateen.examples.mongo.reactive.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import yateen.examples.mongo.models.Employee;
import yateen.examples.mongo.models.User;
import yateen.examples.mongo.models.UserCapped;
import yateen.examples.mongo.reactive.service.UserService;

@Component
public class UserHandler {

	UserService service;
	
	@Autowired
	public UserHandler(UserService service)
	{
		this.service = service;
	}
	public Mono<ServerResponse> getAll(ServerRequest request)
	{
		return ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(service.getAllUsers(), User.class);
							
	}
	
	public Mono<ServerResponse> get(ServerRequest request)
	{
		return ServerResponse.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(service.getUser(request.pathVariable("user_name")), User.class);
							
	}
	
	public Mono<ServerResponse> getAndTail(ServerRequest request)
	{
		return ServerResponse.ok()
							.contentType(MediaType.APPLICATION_STREAM_JSON)
							.body(service.getAllUsersAndTail(), UserCapped.class);
							
	}
	
	public Mono<ServerResponse> post(ServerRequest request)
	{
		Mono<User> userMono = request.bodyToMono(User.class);
		return userMono.flatMap(user-> ServerResponse.status(HttpStatus.CREATED)
							.contentType(MediaType.APPLICATION_JSON)
							.body(service.postUser(user), User.class));
							
	}
	
	public Mono<ServerResponse> put(ServerRequest request)
	{
		Mono<User> userMono = request.bodyToMono(User.class);
		return userMono.flatMap(user-> ServerResponse.status(HttpStatus.OK)
							.contentType(MediaType.APPLICATION_JSON)
							.body(service.findAndUpdateUser(request.pathVariable("user_name"), user), User.class));
							
	}
	public Mono<ServerResponse> delete(ServerRequest request)
	{
		return ServerResponse.status(HttpStatus.OK)
							.contentType(MediaType.APPLICATION_JSON)
							.body(service.findAndDeleteUser(request.pathVariable("user_name")), Void.class);
							
	}
}
