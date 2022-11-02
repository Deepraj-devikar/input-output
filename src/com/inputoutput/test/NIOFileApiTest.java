package com.inputoutput.test;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.IntStream;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;

public class NIOFileApiTest {
	private static String HOME_DIRECTORY_PATH = "E:\\bridgelabz\\practice";
	private static String PLAY_DIRECTORY_NAME = "nio_directory";

	@Test
	public void givenPathWhenCheckedThenConfirm() throws IOException {
		// Check file exists
		Path homePath = Paths.get(HOME_DIRECTORY_PATH);
		Assert.assertTrue(Files.exists(homePath));
		
		// Delete file and check not exist
		Path playPath = Paths.get(HOME_DIRECTORY_PATH+"\\"+PLAY_DIRECTORY_NAME);
		if(Files.exists(playPath)) FileUtils.deleteDirectory(playPath.toFile());
		Assert.assertTrue(Files.notExists(playPath));
		
		// Create directory
		Files.createDirectories(playPath);
		Assert.assertTrue(Files.exists(playPath));
		
		// Create file
		IntStream.range(1, 10).forEach(count -> {
			Path tempFile = Paths.get(playPath + "\\temp" + count + ".txt");
			Assert.assertTrue(Files.notExists(tempFile));
			try {
				Files.createFile(tempFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Assert.assertTrue(Files.exists(tempFile));
		});
		
		// List files, directories, as well as Files with extension
		Files.list(playPath)
		.filter(Files::isRegularFile)
		.forEach(System.out::println);
		
		Files.newDirectoryStream(playPath)
		.forEach(System.out::println);
		
		Files.newDirectoryStream(
				playPath, 
				path -> path.toFile().isFile() && path.toString().endsWith(".txt"))
		.forEach(System.out::println);
	}

}
