package com.example;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.example.IObjectStore.SerializationException;


public class FileBackedObjectStoreTest {

	private File createNewFile() throws IOException {
		File newFile = File.createTempFile(this.getClass().getSimpleName(), null);
		newFile.deleteOnExit();
		return newFile;
	}

	@Test
	public void testBasicSerialization() throws IOException, SerializationException {
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

	@Test
	public void testStoreCollection() throws IOException, SerializationException {
		File file = createNewFile();
		IObjectStore objectStore = new FileBackedObjectStore(file);

		String key = "names";
		HashSet<String> names = new HashSet<String>();
		names.add("Jack");
		names.add("Mike");
		objectStore.put(key, names);
		objectStore.flush();

		IObjectStore objectStore2 = new FileBackedObjectStore(file);
		objectStore2.load();
		Object back1 = objectStore2.get(key);
		assertEquals(names, back1);
		@SuppressWarnings("unchecked")
		Set<String> set = (Set<String>)back1;
		assertEquals(names.size(), set.size());
		assertTrue(set.contains("Jack"));
		assertTrue(set.contains("Mike"));
	}

	@Test
	public void testInitUsingStream() throws IOException, SerializationException {
		File file = createNewFile();
		IObjectStore objectStore = new FileBackedObjectStore(new FileOutputStream(file));

		String key = "greeting";
		String hello = "hello";
		objectStore.put(key, hello);
		objectStore.flush();

		IObjectStore objectStore2 = new FileBackedObjectStore(new FileInputStream(file));
		objectStore2.load();
		Object back1 = objectStore2.get(key);
		assertEquals(hello, back1);
	}
}
