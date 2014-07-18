package com.janosgyerik.examples.junk;

import java.io.Serializable;

public interface Serializer {
	
	void save(Serializable object) throws SerializationException;
	
	Serializable load() throws SerializationException;
	
	public class SerializationException extends Exception {

		private static final long serialVersionUID = 6336594159498855163L;
		
		public SerializationException(Throwable throwable) {
			super(throwable);
		}
		
	}

}
