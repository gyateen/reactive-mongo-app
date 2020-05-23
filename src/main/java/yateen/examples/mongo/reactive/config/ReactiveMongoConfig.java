package yateen.examples.mongo.reactive.config;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.Arrays;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;


@Configuration
public class ReactiveMongoConfig {

	@Bean
	public  MongoClient mongoClient()
	{
		MongoClientSettings settings = MongoClientSettings.builder()
//		        .codecRegistry(getPojoCodecRegistry())
		        .applyToClusterSettings(builder ->
                builder.hosts(Arrays.asList(new ServerAddress("localhost"))))
		        .build();
		return MongoClients.create(settings);
		
	}
	
/*	@Bean
	@Primary
	public  ReactiveMongoTemplate mongoTemplate	()
	{
		
		return new ReactiveMongoTemplate(mongoClient(), "hello-world");
		
		
	}
	
	*/
	
	
	
	protected CodecRegistry getPojoCodecRegistry()
	{
		return fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
	}
}
