package com.sample;

public class ClassImplementingInterfaceWithDefaultsAndOverwritingDefault implements InterfaceWithDefaults {

	@Override
	public String getDefaultString() {
		return "DEFAULT";
	}
	
	@Override
	public String getNotDefaultString() {
		return this.getClass().getSimpleName();
	}

}
