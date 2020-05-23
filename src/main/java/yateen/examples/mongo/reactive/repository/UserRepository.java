package yateen.examples.mongo.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import yateen.examples.mongo.models.User;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String>{

	Mono<User> findByUserName(String userName);

	Mono<User> deleteByUserName(String userName);

	
}
