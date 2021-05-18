package com.sample;

import java.util.List;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Message.Level;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

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
			
			KieContainer container = services.newKieContainer(
					services.getRepository().getDefaultReleaseId());
			
			KieBase base = container.getKieBase();
			
			KieSession session = base.newKieSession();

			session.insert(new ClassImplementingInterfaceWithDefaults());
			session.insert(new ClassImplementingInterfaceWithDefaultsAndOverwritingDefault());
			
			session.fireAllRules();
		}
	}
}

