package com.cortek.solutions.teleportercodechallenge;

import com.cortek.solutions.teleportercodechallenge.input.TeleporterInputProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TeleporterCodeChallengeApplication implements CommandLineRunner {

	public TeleporterInputProcessor teleporterInputProcessor;

	@Autowired
	public TeleporterCodeChallengeApplication(TeleporterInputProcessor teleporterInputProcessor) {
		this.teleporterInputProcessor = teleporterInputProcessor;
	}

	public static void main(String[] args) {
		SpringApplication.run(TeleporterCodeChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		teleporterInputProcessor.processInput(args[0]);
	}
}
