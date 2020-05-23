package yateen.examples.mongo.reactive.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import yateen.examples.mongo.models.User;
import yateen.examples.mongo.models.UserCapped;
import yateen.examples.mongo.reactive.repository.UserCappedRepository;
import yateen.examples.mongo.reactive.repository.UserRepository;
import yateen.examples.mongo.reactive.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	UserRepository userRepo;
	UserCappedRepository userCappedRepo;
	ReactiveMongoTemplate template;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo, UserCappedRepository userCappedRepo, ReactiveMongoTemplate template)
	{
		this.userRepo = userRepo;
		this.template = template;
		this.userCappedRepo = userCappedRepo;
	}
	
	@Override
	public Flux<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	@Override
	public Flux<UserCapped> getAllUsersAndTail() {
		return userCappedRepo.findBy();
	}
	
	@Override
	public Mono<User> getUser(String userName) {
		
		return userRepo.findByUserName(userName)
				.switchIfEmpty(Mono.error(()-> {return new IllegalArgumentException("user not found");}));
	}
	
	@Override
	public Mono<User> postUser(User user) {
		
		return userRepo.insert(user);
	}

	@Override
	public Mono<User> deleteUser(String userName) {
		
		
		return userRepo.deleteByUserName(userName);
	}
	
	@Override
	public Mono<Void> findAndDeleteUser(String userName) {
		
		return userRepo.findByUserName(userName)
						.switchIfEmpty(Mono.error(()-> {return new IllegalArgumentException("user not found");}))
						.flatMap(userRepo::delete);
			
	}
	
	@Override
	public Mono<User> findAndUpdateUser(String userName, User user) {
		
		return userRepo.findByUserName(userName)
						.switchIfEmpty(Mono.error(()-> {return new IllegalArgumentException("user not found");}))
						.map(x -> {return this.update(x, user);})
						.flatMap(userRepo::save);
			
	}
	
	
	private User update(User user, User newUser)
	{
		user.setAge(newUser.getAge());
		return user;
	}
	private String getId(User user)
	{
		if(user == null)
			throw new IllegalArgumentException("user not found");
		return user.getId();
	}

	
}
