package com.inputoutput.test;

import java.io.IOException;
import java.nio.file.*;

import org.junit.Test;

import com.inputoutput.WatchServiceExample;

public class WatchServiceExampleTest {

	@Test
	public void givenADirectoryWhenWatchedListsAllTheActivities() throws IOException {
		Path directory = Paths.get("E:\\bridgelabz\\practice\\nio_directory");
		Files.list(directory)
		.filter(Files::isRegularFile)
		.forEach(System.out::println);
		new WatchServiceExample(directory).processEvents();
	}

}
