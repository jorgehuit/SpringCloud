package springboot.servicio.gateway.filters;




import java.util.Optional;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
//import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered{
	
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		log.info("Execute pre filter");
		exchange.getRequest().mutate().headers( h -> {
			h.add("token", "12345");
		});
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			log.info("Execute post filter");
			Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(valor -> {
				exchange.getResponse().getHeaders().add("token", valor);
			});
			exchange.getResponse().getCookies().add("apellido", ResponseCookie.from("apellido", "Gomez").build());
//			exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
		}));
	}

	@Override
	public int getOrder() {
		// -1 es para ordenar filtros, pero no permite modificar el request
		return 1;
	}

}
