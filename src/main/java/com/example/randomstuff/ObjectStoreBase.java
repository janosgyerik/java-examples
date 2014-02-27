package com.example.randomstuff;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class ObjectStoreBase implements IObjectStore {
	
	Map<String, Serializable> store;
	
	public Map<String, Serializable> getStore() {
		return store;
	}
	
	public ObjectStoreBase() {
		store = new HashMap<String, Serializable>();
	}

	public void put(String key, Serializable obj) {
		store.put(key,  obj);
	}
	
	public Serializable get(String key) {
		return store.get(key);
	}
	
}
