package com.cortek.solutions.teleportercodechallenge;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeleporterCodeChallengeApplicationTests {

	private String TEST_INPUT = "Fortuna - Hemingway\n" +
			"Fortuna - Atlantis\n" +
			"Hemingway - Chesterfield\n" +
			"Chesterfield - Springton\n" +
			"Los Amigos - Paristown\n" +
			"Paristown - Oaktown\n" +
			"Los Amigos - Oaktown\n" +
			"Summerton - Springton\n" +
			"Summerton - Hemingway\n" +
			"cities from Summerton in 1 jumps\n" +
			"cities from Summerton in 2 jumps\n" +
			"can I teleport from Springton to Atlantis\n" +
			"can I teleport from Oaktown to Atlantis\n" +
			"loop possible from Oaktown\n" +
			"loop possible from Fortuna";

	@Autowired
	ApplicationContext ctx;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void testRun() throws Exception {

		String expecteResult = "cities from Summerton in 1 jumps: Springton, Hemingway\n" +
				"cities from Summerton in 2 jumps: Springton, Hemingway, Chesterfield, Fortuna\n" +
				"can I teleport from Springton to Atlantis: yes\n" +
				"can I teleport from Oaktown to Atlantis: no\n" +
				"loop possible from Oaktown: yes\n" +
				"loop possible from Fortuna: no\n";

//		CommandLineRunner runner = ctx.getBean(CommandLineRunner.class);
//		runner.run("input", TEST_INPUT);
//
//		assertEquals(expecteResult, outContent.toString().trim());
	}
}
