package yateen.examples.mongo.reactive.service;

import java.util.List;

import org.bson.Document;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.Success;

import reactor.core.publisher.Flux;
import yateen.examples.mongo.models.Employee;

@Service
public class MongoService {

	MongoClient client;
	
	
	@Autowired
	public MongoService(MongoClient client)
	{
		this.client = client;
	}
	
	public void insert(List<Employee> emp)
	{
		MongoCollection<Employee> coll = client.getDatabase("hello-world1").getCollection("collection1", Employee.class);
		System.out.println("collection obtained");
		coll.insertMany(emp)
			.subscribe(new Subscriber<Success>() {

				@Override
				public void onSubscribe(Subscription s) {
					
					s.request(emp.size());
				}

				@Override
				public void onNext(Success t) {
					System.out.println("inserted element");
					
				}

				@Override
				public void onError(Throwable t) {
					System.out.println("Error while inserting elements: "+t);
					
				}

				@Override
				public void onComplete() {
					System.out.println("insertion complete");
					
				}
				
			});
	}
}
