package com.demomvc.curso.boot;

import java.util.Locale;

import com.demomvc.curso.boot.listeners.MatchListener;
import com.demomvc.curso.boot.listeners.TierListener;
import com.demomvc.curso.boot.publishers.EventManager;
import com.demomvc.curso.boot.service.NivelService;
import com.demomvc.curso.boot.service.PartidaService;
import com.demomvc.curso.boot.service.RiotApiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class DaleGgApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DaleGgApplication.class, args);

		EventManager eventManager = ctx.getBean(EventManager.class);
		RiotApiService riotApiService = ctx.getBean(RiotApiService.class);

		TierListener tierListener = new TierListener(ctx.getBean(NivelService.class), riotApiService);
		MatchListener matchListener = new MatchListener(ctx.getBean(PartidaService.class), riotApiService);

		eventManager.subscribe("createSummoner", tierListener);
		eventManager.subscribe("createSummoner", matchListener);

		eventManager.subscribe("updateSummoner", tierListener);
		eventManager.subscribe("updateSummoner", matchListener);

		eventManager.subscribe("deleteSummoner", tierListener);
		eventManager.subscribe("deleteSummoner", matchListener);
	}

	@Bean
	public LocaleResolver localeResolver(){
	  return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public EventManager eventManager() {
		return new EventManager("createSummoner", "updateSummoner", "deleteSummoner");
	}
}
