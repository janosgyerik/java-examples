package com.example;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.example.ISerializer.SerializationException;


public class FileSerializerTest {

	private File createNewFile() throws IOException {
		File newFile = File.createTempFile(this.getClass().getSimpleName(), null);
		return newFile;
	}
	
	@Test
	public void testBasicSerialization() throws SerializationException, IOException {
		File file = createNewFile();
		ISerializer serializer = new FileSerializer(file);
		
		String hello = "hello";
		serializer.save(hello);
		
		Object back1 = serializer.load();
		assertEquals(hello, back1);
	}

}
