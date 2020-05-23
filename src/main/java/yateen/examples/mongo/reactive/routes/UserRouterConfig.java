package yateen.examples.mongo.reactive.routes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import yateen.examples.mongo.reactive.handlers.UserHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;


@Configuration
public class UserRouterConfig {

	public static final String END_POINT = "/functional/users";
	
	@Bean
	public RouterFunction<ServerResponse> routes(UserHandler handler)
	{
		return RouterFunctions.route(GET(END_POINT), handler::getAll)
							  .andRoute(GET(END_POINT + "/{user_name}"), handler::get)
							  .andRoute(POST(END_POINT), handler::post)
							  .andRoute(PUT(END_POINT + "/{user_name}"), handler::put)
							  .andRoute(DELETE(END_POINT + "/{user_name}"), handler::delete)
							  .andRoute(GET("/functional/tail"), handler::getAndTail);
	
	}
}
