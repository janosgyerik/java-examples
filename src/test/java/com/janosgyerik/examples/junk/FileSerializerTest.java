package com.janosgyerik.examples.junk;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.janosgyerik.examples.junk.Serializer.SerializationException;


public class FileSerializerTest {

	private File createNewFile() throws IOException {
		File newFile = File.createTempFile(this.getClass().getSimpleName(), null);
		return newFile;
	}
	
	@Test
	public void testBasicSerialization() throws SerializationException, IOException {
		File file = createNewFile();
		Serializer serializer = new FileSerializer(file);
		
		String hello = "hello";
		serializer.save(hello);
		
		Object back1 = serializer.load();
		assertEquals(hello, back1);
	}

}
