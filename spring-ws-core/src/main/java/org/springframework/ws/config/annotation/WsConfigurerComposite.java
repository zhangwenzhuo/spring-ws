package org.springframework.ws.config.annotation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arjen Poutsma
 */
public class WsConfigurerComposite implements WsConfigurer {

	private List<WsConfigurer> delegates = new ArrayList<WsConfigurer>();

	public void addWsConfigurers(List<WsConfigurer> configurers) {
		if (configurers != null) {
			this.delegates.addAll(configurers);
		}
	}

}
