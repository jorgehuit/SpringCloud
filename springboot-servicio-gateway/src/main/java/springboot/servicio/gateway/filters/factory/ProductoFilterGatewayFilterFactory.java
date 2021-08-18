package springboot.servicio.gateway.filters.factory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class ProductoFilterGatewayFilterFactory extends AbstractGatewayFilterFactory<ProductoFilterGatewayFilterFactory.ConfFilter> {

	public ProductoFilterGatewayFilterFactory() {
		super(ConfFilter.class);
	}

	@Override
	public GatewayFilter apply(ConfFilter config) {
		return new OrderedGatewayFilter((exchange, chain) -> {
			log.info("Execute pre GatewayFilterFactory: " + config.msg);
			
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				Optional.ofNullable(config.cookieValue).ifPresent(p -> {
					exchange.getResponse().addCookie(ResponseCookie.from(config.cookieNombre, p).build());
				});
				log.info("Execute post GatewayFilterFactory: " + config.msg);

			}));
		}, 2);
	}

	@Override
	public List<String> shortcutFieldOrder() {
		return Arrays.asList("msg", "cookieNombre", "cookieValue");
	}

	@Override
	public String name() {
		return "ProductFilter";
	}

	@Data
	public static class ConfFilter {
		private String msg;
		private String cookieNombre;
		private String cookieValue;
	}
}
