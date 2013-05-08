package com.example;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.example.IObjectStore.SerializationException;


public class FileBackedObjectStoreTest {

	private File createNewFile() throws IOException {
		File newFile = File.createTempFile(this.getClass().getSimpleName(), null);
		newFile.deleteOnExit();
		return newFile;
	}

	@Test
	public void testBasicSerialization() throws IOException, IObjectStore.SerializationException {
		File file = createNewFile();
		IObjectStore objectStore = new FileBackedObjectStore(file);

		String key = "greeting";
		String hello = "hello";
		objectStore.put(key, hello);
		objectStore.flush();

		IObjectStore objectStore2 = new FileBackedObjectStore(file);
		objectStore2.load();
		Object back1 = objectStore2.get(key);
		assertEquals(hello, back1);
	}

	@Test
	public void testStoreMultiple() throws IOException, SerializationException {
		File file = createNewFile();
		IObjectStore objectStore = new FileBackedObjectStore(file);

		String key1 = "greeting";
		String hello = "hello";
		objectStore.put(key1, hello);
		String key2 = "num";
		int num = 3;
		objectStore.put(key2, num);
		objectStore.flush();

		IObjectStore objectStore2 = new FileBackedObjectStore(file);
		objectStore2.load();
		Object back1 = objectStore2.get(key1);
		assertEquals(hello, back1);
		Object back2 = objectStore2.get(key2);
		assertEquals(num, back2);
	}

}
