package yateen.examples.mongo.reactive;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import yateen.examples.mongo.models.Employee;
import yateen.examples.mongo.reactive.service.MongoService;
import yateen.examples.mongo.reactive.service.MongoServiceFromTemplate;

@SpringBootApplication
public class MongoReactorApp {

	public static void main(String[] args)
	{
		ApplicationContext context = SpringApplication.run(MongoReactorApp.class, args);
	//	MongoService service = context.getBean(MongoService.class);
	//	service.insert(Arrays.asList(new Employee(), new Employee(), new Employee()));
		MongoServiceFromTemplate template = context.getBean(MongoServiceFromTemplate.class);
	//	template.createCappedCollection("userCapped");
//		template.insert("collection1", Arrays.asList(new Employee(4,27), new Employee(5,27), new Employee(6,28)));
		template.findAndTail("userCapped", "age", 27);
		
		
	}
}
