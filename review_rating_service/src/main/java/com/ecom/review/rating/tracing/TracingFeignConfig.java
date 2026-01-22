package com.ecom.review.rating.tracing;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import brave.Tracer;
import brave.propagation.CurrentTraceContext;
import brave.propagation.Propagation;
import brave.propagation.TraceContext;
import feign.RequestInterceptor;
import feign.RequestTemplate;

@Service
public class TracingFeignConfig {

	private static final Propagation.Setter<RequestTemplate, String> SETTER = RequestTemplate::header;

	@Bean
	public RequestInterceptor tracingInterceptor(Tracer tracer, Propagation.Factory propagationFactory,
			CurrentTraceContext currentTraceContext) {
		return template -> {
			TraceContext context = currentTraceContext.get();
			if (context != null) {
				Propagation<String> propagation = propagationFactory.get();
				propagation.injector(SETTER).inject(context, template);
			}
		};
	}
}
