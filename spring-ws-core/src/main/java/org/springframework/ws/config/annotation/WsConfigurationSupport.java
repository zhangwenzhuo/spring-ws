/*
 * Copyright 2005-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.ws.config.annotation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.adapter.DefaultMethodEndpointAdapter;
import org.springframework.ws.server.endpoint.adapter.method.MethodArgumentResolver;
import org.springframework.ws.server.endpoint.adapter.method.MethodReturnValueHandler;
import org.springframework.ws.server.endpoint.mapping.PayloadRootAnnotationMethodEndpointMapping;
import org.springframework.ws.soap.addressing.server.AnnotationActionEndpointMapping;
import org.springframework.ws.soap.server.endpoint.SimpleSoapExceptionResolver;
import org.springframework.ws.soap.server.endpoint.SoapFaultAnnotationExceptionResolver;
import org.springframework.ws.soap.server.endpoint.mapping.SoapActionAnnotationMethodEndpointMapping;

/**
 * @author Arjen Poutsma
 */
public class WsConfigurationSupport {

	private List<EndpointInterceptor> interceptors;

	@Bean
	public PayloadRootAnnotationMethodEndpointMapping payloadRootAnnotationMethodEndpointMapping() {
		PayloadRootAnnotationMethodEndpointMapping endpointMapping =
				new PayloadRootAnnotationMethodEndpointMapping();
		endpointMapping.setOrder(0);
		endpointMapping.setInterceptors(getInterceptors());
		return endpointMapping;
	}

	@Bean
	public SoapActionAnnotationMethodEndpointMapping soapActionAnnotationMethodEndpointMapping() {
		SoapActionAnnotationMethodEndpointMapping endpointMapping =
				new SoapActionAnnotationMethodEndpointMapping();
		endpointMapping.setOrder(1);
		endpointMapping.setInterceptors(getInterceptors());
		return endpointMapping;
	}

	@Bean
	public AnnotationActionEndpointMapping annotationActionEndpointMapping() {
		AnnotationActionEndpointMapping endpointMapping =
				new AnnotationActionEndpointMapping();
		endpointMapping.setOrder(2);
		endpointMapping.setPostInterceptors(getInterceptors());
		return endpointMapping;
	}

	protected final EndpointInterceptor[] getInterceptors() {
		if (interceptors == null) {
			interceptors = new ArrayList<EndpointInterceptor>();
			addInterceptors(interceptors);
		}
		return interceptors.toArray(new EndpointInterceptor[interceptors.size()]);
	}

	protected void addInterceptors(List<EndpointInterceptor> interceptors) {

	}

	@Bean
	public DefaultMethodEndpointAdapter defaultMethodEndpointAdapter() {
		List<MethodArgumentResolver> argumentResolvers =
				new ArrayList<MethodArgumentResolver>();
		addArgumentResolvers(argumentResolvers);

		List<MethodReturnValueHandler> returnValueHandlers =
				new ArrayList<MethodReturnValueHandler>();
		addReturnValueHandlers(returnValueHandlers);

		DefaultMethodEndpointAdapter adapter = new DefaultMethodEndpointAdapter();
		adapter.setMethodArgumentResolvers(argumentResolvers);
		adapter.setMethodReturnValueHandlers(returnValueHandlers);

		return adapter;
	}

	protected void addArgumentResolvers(List<MethodArgumentResolver> argumentResolvers) {
	}

	protected void addReturnValueHandlers(
			List<MethodReturnValueHandler> returnValueHandlers) {
	}

	@Bean
	public SoapFaultAnnotationExceptionResolver soapFaultAnnotationExceptionResolver() {
		SoapFaultAnnotationExceptionResolver exceptionResolver = new SoapFaultAnnotationExceptionResolver();
		exceptionResolver.setOrder(0);

		return exceptionResolver;
	}

	@Bean
	public SimpleSoapExceptionResolver simpleSoapExceptionResolver() {
		SimpleSoapExceptionResolver exceptionResolver = new SimpleSoapExceptionResolver();
		exceptionResolver.setOrder(Ordered.LOWEST_PRECEDENCE);

		return exceptionResolver;
	}

	//TODO: exception resolvers


}
