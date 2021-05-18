package com.sample;

public interface InterfaceWithDefaults {

	default String getDefaultString() {
		return "DEFAULT";
	}
	
	String getNotDefaultString();
}
