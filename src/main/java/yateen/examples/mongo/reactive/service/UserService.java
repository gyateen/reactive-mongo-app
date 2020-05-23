package yateen.examples.mongo.reactive.service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import yateen.examples.mongo.models.User;
import yateen.examples.mongo.models.UserCapped;

public interface UserService {

	Flux<User> getAllUsers();

	Mono<User> postUser(User user);

	Mono<User> getUser(String userName);

	Mono<User> deleteUser(String userName);

	Mono<Void> findAndDeleteUser(String userName);

	Mono<User> findAndUpdateUser(String userName, User user);

	Flux<UserCapped> getAllUsersAndTail();

	
}
