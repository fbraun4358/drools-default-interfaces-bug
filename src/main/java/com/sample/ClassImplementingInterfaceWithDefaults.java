package com.sample;

public class ClassImplementingInterfaceWithDefaults implements InterfaceWithDefaults {

	@Override
	public String getNotDefaultString() {
		return this.getClass().getSimpleName();
	}

}
