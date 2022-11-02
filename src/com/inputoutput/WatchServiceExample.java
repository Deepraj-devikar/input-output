package com.inputoutput;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class WatchServiceExample {
	private final WatchService watchService;
	private final Map<WatchKey, Path> dirWatcher;
	
	/*
	 * creates watch service and registers given directory
	 */
	public WatchServiceExample(Path dir) throws IOException {
		this.watchService = FileSystems.getDefault().newWatchService();
		this.dirWatcher = new HashMap<WatchKey, Path>();
		ScanAndRegisterDirectories(dir);
	}
	
	/*
	 * Register the given directory, and all its sub-directories,
	 * with the watch service
	 */
	private void ScanAndRegisterDirectories(final Path start) throws IOException {
		// register directory and sub-directory
		Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir, 
					BasicFileAttributes attrs) 
							throws IOException {
				registerDirWatchers(dir);
				return FileVisitResult.CONTINUE;
			}			
		});
	}

	/*
	 * Register the given directory with the watch service
	 */
	protected void registerDirWatchers(Path dir) throws IOException {
		WatchKey watchKey = dir.register(watchService, 
				ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		dirWatcher.put(watchKey, dir);
	}
	
	public void processEvents() {
		while(true) {
			WatchKey watchKey;
			try {
				watchKey = watchService.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
			Path dir = dirWatcher.get(watchKey);
			if(dir == null) continue;
			for(WatchEvent<?> event : watchKey.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				Path pathName = ((WatchEvent<Path>) event).context();
				Path childPath = dir.resolve(pathName);
				// print out event
				System.out.format("%s: %s\n", event.kind().name(), childPath);
				
				// if directory is created, then register it and its sub-directories
				if(kind == ENTRY_CREATE) {
					if(Files.isDirectory(childPath)) {
						try {
							ScanAndRegisterDirectories(childPath);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else if(kind == ENTRY_DELETE) {
					if(Files.isDirectory(childPath)) {
						dirWatcher.remove(watchKey);
					}
				}
			}
			// rest key and remove from set if directory no longer accessible
			boolean valid = watchKey.reset();
			if(!valid) {
				dirWatcher.remove(watchKey);
				// all directories are inaccessible
				if(dirWatcher.isEmpty()) break;
			}
		}
	}

}
