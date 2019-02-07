package org.onap.kubernetes.viewport.sbi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

//@PropertySource(ignoreResourceNotFound=true, value="classpath:jdbc-${spring.profiles.active}.properties")
@PropertySource(ignoreResourceNotFound=true, value="classpath:onap-dev.properties")
public class Configuration {

	// specific to each DC (should not use
	public static final String DDC = "dfw";
	public static final String VDC = "iad";
	// running inside the DC (DDC or VDC)
	public static final String LOCAL = "local";
	// running as a developer local machine (vms are on the cloud)
	public static final String REMOTE = "remote";
	
	 @Value("${script.dir}") // TODO: use typesafe
	private String scriptDir;
	
	public String getScriptDir() {
		return scriptDir;
	}
	
	
	private static Map<String, Map<String, String>> map = new HashMap<>();
	
	//public static String DC = VDC;
	public static String DC = LOCAL;
	//public static String DC = REMOTE;
	
	static {
		map.put(DDC,  new HashMap<>());
		map.put(VDC,  new HashMap<>());	
		map.put(LOCAL,  new HashMap<>());
		map.put(REMOTE,  new HashMap<>());
		Map<String, String> dMap = map.get(DDC);
		Map<String, String> vMap = map.get(VDC);
		Map<String, String> lMap = map.get(LOCAL);
		Map<String, String> rMap = map.get(REMOTE);
		//dMap.put("robot-ip", "104.130.12.17");
		dMap.put("aai-ip", "104.239.249.213");
		//dMap.put("appc-ip", "67.192.246.90");
		//dMap.put("dcae-ip", "67.192.246.40");
		//dMap.put("message-router-ip", "104.130.159.144");
//		dMap.put("mso-ip", "162.242.219.191");
		//dMap.put("policy-ip", "172.99.115.234");
		//dMap.put("portal-ip", "104.239.241.48");
		//dMap.put("sdc-ip", "172.99.115.127");
		//dMap.put("sdnc-ip", "162.242.221.92");
		//dMap.put("vid-ip", "23.253.125.165");
		//dMap.put("cdap0-ip", "23.253.85.68");
		//dMap.put("cdap1-ip", "174.143.130.203");
		//dMap.put("cdap2-ip", "172.99.69.75");
		dMap.put("coll-ip", "172.99.67.194");
		//dMap.put("pstg-ip", "104.239.240.7");	
		dMap.put("sink-ip", "10.1.0.13");
		dMap.put("fwk-ip", "10.1.0.11");
		dMap.put("gen-ip", "10.1.0.12");
		
		//vMap.put("robot-ip", "23.253.235.131");
		vMap.put("aai-ip", "209.61.160.84");
		
		//vMap.put("appc-ip", "162.242.219.48");
		//vMap.put("dcae-ip", "172.99.67.156");
		//vMap.put("message-router-ip", "162.209.124.37");
		//vMap.put("mso-ip", "23.253.68.117");
		//vMap.put("policy-ip", "104.239.234.15");
		//vMap.put("portal-ip", "104.130.31.44");
		//vMap.put("sdc-ip", "104.239.234.11");
		//vMap.put("sdnc-ip", "23.253.68.123");
		//vMap.put("vid-ip", "104.130.169.38");
		//vMap.put("cdap0-ip", "104.239.175.196");
		//vMap.put("cdap1-ip", "104.130.239.149");
		//vMap.put("cdap2-ip", "23.253.85.59");
		vMap.put("coll-ip", "104.239.150.102");
		// dfw
		
		//vMap.put("pstg-ip", "104.239.168.49");
		vMap.put("sink-ip", "10.1.0.13");
		vMap.put("fwk-ip", "10.1.0.11");
		vMap.put("gen-ip", "10.1.0.12");
		
		// local config
		// DFW
		lMap.put("script-dir","/opt/");
		lMap.put("robot-sh", "demo.sh");
		//lMap.put("robot-ip", "23.253.235.131");
		lMap.put("aai-ip", "209.61.160.84");//10.0.1.1");
		//lMap.put("appc-ip", "162.242.219.48");
		//lMap.put("dcae-ip", "172.99.67.156");
		//lMap.put("message-router-ip", "162.209.124.37");
//		lMap.put("mso-ip", "23.253.68.117");
		//lMap.put("policy-ip", "104.239.234.15");
		//lMap.put("portal-ip", "104.130.31.44");
		//lMap.put("sdc-ip", "104.239.234.11");
		//lMap.put("sdnc-ip", "23.253.68.123");
		//lMap.put("vid-ip", "104.130.169.38");
		//lMap.put("cdap0-ip", "104.239.175.196");
		//lMap.put("cdap1-ip", "104.130.239.149");
		//lMap.put("cdap2-ip", "23.253.85.59");
		lMap.put("coll-ip", "23.253.99.173");//10.0.4.105");
		//lMap.put("pstg-ip", "104.239.168.49");
		lMap.put("sink-ip", "10.1.0.13");
		lMap.put("fwk-ip", "10.1.0.11");
		lMap.put("gen-ip", "10.1.0.12");
		
		// remote IAD
		//rMap.put("robot-ip", "23.253.235.131");
		rMap.put("aai-ip", "104.239.249.213");
		
		//lMap.put("appc-ip", "162.242.219.48");
		//lMap.put("dcae-ip", "172.99.67.156");
		//lMap.put("message-router-ip", "162.209.124.37");
//		rMap.put("mso-ip", "23.253.68.117");
		//lMap.put("policy-ip", "104.239.234.15");
		//lMap.put("portal-ip", "104.130.31.44");
		//lMap.put("sdc-ip", "104.239.234.11");
		//rMap.put("sdnc-ip", "23.253.68.123");
		//lMap.put("vid-ip", "104.130.169.38");
		//lMap.put("cdap0-ip", "104.239.175.196");
		//lMap.put("cdap1-ip", "104.130.239.149");
		//rMap.put("cdap2-ip", "23.253.85.59");
		rMap.put("coll-ip", "172.99.67.194");
		//lMap.put("pstg-ip", "104.239.168.49");
		rMap.put("sink-ip", "104.130.140.22");
		rMap.put("fwk-ip", "104.130.140.213");
		rMap.put("gen-ip", "104.130.140.38");
		
	}
	
	public static String get(String region, String key) {
		return map.get(region).get(key);
	}
	
	public static void put(String region, String key, String value) {
		map.get(region).put(key, value);
	}
	
	public static String switchDC() {
		if(DC.equalsIgnoreCase(LOCAL)) {
			DC = REMOTE;
		} else {
			DC = LOCAL;
		}
		System.out.println("switch-dc: " + DC);
		return DC.toLowerCase();
	}
	
	// curl http://healthcheck:zb\!XztG34@policy:6969/healthcheck
}
