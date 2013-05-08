package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public class FileBackedObjectStore extends ObjectStoreBase {

	private final File file;

	public FileBackedObjectStore(File file) {
		this.file = file;
	}

	@Override
	public void flush() throws SerializationException {
		try {
			FileOutputStream fout = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(getStore());
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new SerializationException(e);
		}
	}

	@Override
	public void load() throws SerializationException {
		try {
			FileInputStream fin = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fin);
			@SuppressWarnings("unchecked")
			Map<? extends String, ? extends Serializable> readObject =
					(Map<? extends String, ? extends Serializable>) ois.readObject();
			getStore().putAll(readObject);
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
