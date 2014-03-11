package com.janosgyerik.examples.junk;
import static org.junit.Assert.*;

import org.junit.Test;


public class UniqueCharsTest {

	boolean isCharDuplicationOrig(String s) {
		boolean result = false;
		boolean controlArray[] = new boolean[256];
		for (int i = 0; i < s.length(); i++) {
			int val = s.charAt(i);
			if (controlArray[val] == true) {
				result = true;
				break;
			}
			controlArray[val] = true;
		}
		return result;
	}

	@Test
	public void testOrigImpl() {
		assertTrue(isCharDuplicationOrig("hello"));
		assertFalse(isCharDuplicationOrig("helo"));
	}

	boolean isCharDuplicationOther(String s) {   
		for (int i = 0; i < s.length(); i++) 
			for (int j = i+1; j < s.length(); j++) 
				if (s.charAt(i) == s.charAt(j))
					return true;   
		return false;
	}
	
	@Test
	public void testOtherImpl() {
		assertTrue(isCharDuplicationOther("hello"));
		assertFalse(isCharDuplicationOther("helo"));
	}

}
