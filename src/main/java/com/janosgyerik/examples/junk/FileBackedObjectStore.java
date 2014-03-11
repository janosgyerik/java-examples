package com.janosgyerik.examples.junk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

public class FileBackedObjectStore extends ObjectStoreBase {

	private File file;
	private FileInputStream input;
	private FileOutputStream output;

	public FileBackedObjectStore(File file) {
		this.file = file;
	}

	public FileBackedObjectStore(FileInputStream input) {
		this.input = input;
	}

	public FileBackedObjectStore(FileOutputStream output) {
		this.output = output;
	}

	private void flush(FileOutputStream fout) throws SerializationException {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(getStore());
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new SerializationException(e);
		}
	}

	@Override
	public void flush() throws SerializationException {
		if (output != null) {
			flush(output);
		}
		else {
			try {
				flush(new FileOutputStream(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new SerializationException(e);
			}
		}
	}

	public void load(FileInputStream fin) throws SerializationException {
		try {
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

	@Override
	public void load() throws SerializationException {
		if (input != null) {
			load(input);
		}
		else {
			try {
				load(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new SerializationException(e);
			}
		}
	}

}
