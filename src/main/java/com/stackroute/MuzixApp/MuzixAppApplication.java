package com.stackroute.MuzixApp;

import com.stackroute.MuzixApp.domain.Track;
import com.stackroute.MuzixApp.repository.TrackRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/*
In this class Application Listener,Command Line Runner,@Value ,@PropertySource is used.
 */

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class MuzixAppApplication /*implements  ApplicationListener<ContextRefreshedEvent> , CommandLineRunner*/
{
//	@Value("4")
//	private int id;
//
//	@Value("Despacito")
//	private String trackName;
//
//	@Value("JustinBieber")
//	private String trackComment;
//
//	@Autowired
//	TrackRepository trackRepository;
//
//	@Autowired
//	Environment env;
	public static void main(String[] args) {
		SpringApplication.run(MuzixAppApplication.class, args);
	}
/*
	//Appliction Event method
	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		trackRepository.save(new Track(Integer.parseInt(env.getProperty("trackId")),env.getProperty("trackName"),env.getProperty("trackComments")));
	}

	//Command Line Run method
	@Override
	public void run(String... args) throws Exception {
		trackRepository.save(new Track(id,trackName,trackComment));
	}*/
}

