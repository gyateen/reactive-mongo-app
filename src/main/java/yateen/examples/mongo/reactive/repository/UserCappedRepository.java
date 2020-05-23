package yateen.examples.mongo.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import yateen.examples.mongo.models.UserCapped;

	@Repository
	public interface UserCappedRepository extends ReactiveMongoRepository<UserCapped, String>{
		
		@Tailable
		Flux<UserCapped> findBy();
	}

