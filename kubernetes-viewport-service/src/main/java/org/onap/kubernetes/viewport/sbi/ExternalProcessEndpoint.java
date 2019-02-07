package org.onap.kubernetes.viewport.sbi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;


public class ExternalProcessEndpoint {

	public String runExternalAbsolute(String sh) {
		ProcessBuilder processBuilder = new ProcessBuilder(sh);
		System.out.println("Running: " + sh);
		return runExternal(processBuilder);
	}

	public String runExternal(String sh, String op0) {
		ProcessBuilder processBuilder = new ProcessBuilder(
				Configuration.get(Configuration.LOCAL, "script-dir") +  sh, op0);
		System.out.println("Running: " + Configuration.get(Configuration.LOCAL, "script-dir") +  sh + " " + op0 );
		return runExternal(processBuilder);
	}
	
	public String runExternal(String sh, String op0, String op1) {
		ProcessBuilder processBuilder = new ProcessBuilder(
				Configuration.get(Configuration.LOCAL, "script-dir") +  sh, op0, op1);
		System.out.println("Running: " + Configuration.get(Configuration.LOCAL, "script-dir") +  sh + " " + op0 + " " + op1 );
		return runExternal(processBuilder);
	}
	
	public String runExternal(String sh, String op0, String op1, String op2) {
		ProcessBuilder processBuilder = new ProcessBuilder(
				Configuration.get(Configuration.LOCAL, "script-dir") +  sh, op0, op1, op2);
		System.out.println("Running: " + Configuration.get(Configuration.LOCAL, "script-dir") +  sh + " " + op0 + " " + op1 + " " + op2);
		return runExternal(processBuilder);
	}
	
	
	public String runExternal(ProcessBuilder processBuilder) {
		// https://developer.openstack.org/api-guide/quick-start/api-quick-start.html
		StringBuffer buffer = new StringBuffer();
		try {

			processBuilder.redirectErrorStream(true);
			Process process = processBuilder.start();
			InputStreamReader isr = new InputStreamReader(process.getInputStream());
			BufferedReader buff = new BufferedReader (isr);
			String line;
			//int lineCount = 0;
			while((line = buff.readLine()) != null) {
				System.out.println(line);
				buffer.append(line);
				//if(lineCount > 2) {
				//	break;
				//}
				//lineCount+=1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// simulate
		try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException ie) { ie.printStackTrace(); }
		return buffer.toString();
	}
	public static void main(String[] args) {
		ExternalProcessEndpoint ep = new ExternalProcessEndpoint();
		System.out.println(ep.runExternal("openstack_port_list.sh","network", "list", "op2"));
	
	}

}
