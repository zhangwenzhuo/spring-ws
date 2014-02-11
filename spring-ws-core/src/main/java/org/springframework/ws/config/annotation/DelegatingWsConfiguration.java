package org.springframework.ws.config.annotation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author Arjen Poutsma
 */
@Configuration
public class DelegatingWsConfiguration extends WsConfigurationSupport {

	private final WsConfigurerComposite configurers = new WsConfigurerComposite();

	@Autowired(required = false)
	public void setConfigurers(List<WsConfigurer> configurers) {
		if (configurers != null && !configurers.isEmpty()) {
			this.configurers.addWsConfigurers(configurers);
		}
	}


}
