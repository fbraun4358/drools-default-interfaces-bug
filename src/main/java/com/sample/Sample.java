package com.sample;

import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Message.Level;
import org.kie.api.io.Resource;

public class Sample {
	
	public static final String DRL_FILE = "sample.drl";
	
	public static void main(String[] args) throws Exception {
		KieServices services = KieServices.Factory.get();
		KieFileSystem kfs = services.newKieFileSystem();
		
		Resource drl = services.getResources()
				.newFileSystemResource("src/main/resources/"+DRL_FILE);
		
		kfs.write(drl);

		KieBuilder kieBuilder = services.newKieBuilder(kfs).buildAll();
		
		if (kieBuilder.getResults().hasMessages(Level.ERROR)) {
			
			List<Message> messages = kieBuilder.getResults().getMessages(Level.ERROR);
			
			for(Message m: messages) {
				System.out.println(m);
			}
			
		} else {
			System.out.println("Success!");	
		}
	}
}

