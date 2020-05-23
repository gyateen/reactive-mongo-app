package yateen.examples.mongo.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;

@SpringBootApplication(exclude= {MongoDataAutoConfiguration.class})
public class ReactiveMongoApp {

	public static void main(String[] args)
	{
		SpringApplication.run(ReactiveMongoApp.class, args);
	}
}
