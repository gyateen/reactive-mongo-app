import java.time.Duration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;

public class MicrometerRegistry {

	
	public static void main(String[] args)
	{
		CompositeMeterRegistry mr = new CompositeMeterRegistry();
		mr.add(new PrometheusMeterRegistry(null));
		mr.counter("orders").increment(2);
		mr.gauge("speed",55);
		mr.timer("sample-timer").record(Duration.ofMillis(1000));
		mr.timer("sample-timer").record(()->System.out.println("hello timer"));
	}
}
