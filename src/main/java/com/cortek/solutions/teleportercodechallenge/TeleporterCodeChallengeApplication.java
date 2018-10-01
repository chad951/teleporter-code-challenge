package com.cortek.solutions.teleportercodechallenge;

import com.cortek.solutions.teleportercodechallenge.input.TeleporterInputProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeleporterCodeChallengeApplication implements CommandLineRunner {

	@Autowired
	private TeleporterInputProcessor teleporterInputProcessor;

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(TeleporterCodeChallengeApplication.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Running Teleporter Application.");
		if (args.length > 0) {
			System.out.println("Program Arguments: " + args[0]);
			teleporterInputProcessor.processInput(args[0]);
		}
	}
}
