package yateen.examples.mongo.reactive.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import yateen.examples.mongo.models.Employee;
import yateen.examples.mongo.models.User;

@Service
public class MongoServiceFromTemplate {

	
	ReactiveMongoTemplate template;
	
	@Autowired
	public MongoServiceFromTemplate(ReactiveMongoTemplate template)
	{
		this.template = template;
	}
	
	public void createCappedCollection(String collection)
	{
		template.dropCollection(collection)
				.block();
		template.createCollection(collection, CollectionOptions.empty().maxDocuments(20).size(50000).capped())
				.subscribe(x->System.out.println("created capped collection"));
	}
	
	public void insert(String collection, List<Employee> employees)
	{
		template.insert(employees, collection)
				.subscribe(System.out::println, e->System.out.println("completed with error:" + e), ()->System.out.println("completed without error"));
	}
	
	
	public void find(String collection, String key, Object value)
	{
		
		template.find(new Query(new Criteria(key).is(value)), User.class, collection)
				
					.subscribe(this::acceptAndSleep, e->System.out.println("completed with error:" + e), ()->System.out.println("completed without error"));
		System.out.println("exiting find method");

		
	}
	
	public void findAndTail(String collection, String key, Object value)
	{
		
		template.tail(new Query(new Criteria(key).is(value)), User.class, collection)
					.subscribe(this::acceptAndSleep, e->System.out.println("completed with error:" + e), ()->System.out.println("completed without error"));
		System.out.println("exiting find method");

		
	}
	
	public void acceptAndSleep(User e) 
	{
		
		System.out.println("Received user:" + e);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
} 
