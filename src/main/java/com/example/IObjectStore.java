package com.example;

import java.io.Serializable;

public interface IObjectStore {

	void put(String key, Serializable obj);
	
	Serializable get(String key);

	/**
	 * Save the internal storage to the backend.
	 * Whether this is necessary and *when* it is necessary
	 * depends on the implementation.
	 * @throws SerializationException
	 */
	void flush() throws SerializationException;
	
	/**
	 * Load the internal storage from the backend.
	 * Whether this is necessary and *when* it is necessary
	 * depends on the implementation.
	 * @throws SerializationException
	 */
	void load() throws SerializationException;
	
	public class SerializationException extends Exception {

		private static final long serialVersionUID = 6336594159498855163L;
		
		public SerializationException(Throwable throwable) {
			super(throwable);
		}
		
	}

}
