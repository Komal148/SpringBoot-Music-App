package com.stackroute.MuzixApp;

import com.stackroute.MuzixApp.domain.Track;
import com.stackroute.MuzixApp.repository.TrackRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class MuzixAppApplication implements ApplicationListener<ContextRefreshedEvent> , CommandLineRunner {

	@Autowired
	TrackRepository trackRepository;
	public static void main(String[] args) {
		SpringApplication.run(MuzixAppApplication.class, args);
	}


	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		trackRepository.save(new Track(1,"IntoYou","ArianaGrande"));
	}

	@Override
	public void run(String... args) throws Exception {
		trackRepository.save(new Track(2,"Perfect","Edshereen"));
	}
}

