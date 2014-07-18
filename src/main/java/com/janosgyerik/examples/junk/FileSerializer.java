package com.janosgyerik.examples.junk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Suitable for storing and loading a single Serializable object.
 * 
 * @author janos
 *
 */
public class FileSerializer implements Serializer {
	
	private final File file;

	public FileSerializer(File file) {
		this.file = file;
	}

	@Override
	public void save(Serializable object) throws SerializationException {
		try {
			FileOutputStream output = new FileOutputStream(file, true);
			ObjectOutputStream oos = new ObjectOutputStream(output);
			oos.writeObject(object);
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new SerializationException(e);
		}
	}

	@Override
	public Serializable load() throws SerializationException {
		try {
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fin);
			return (Serializable) ois.readObject();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new SerializationException(e);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SerializationException(e);
		}
	}

}
