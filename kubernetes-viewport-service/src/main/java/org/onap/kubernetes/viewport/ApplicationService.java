
package org.onap.kubernetes.viewport;

import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.onap.kubernetes.viewport.sbi.Configuration;
import org.onap.kubernetes.viewport.sbi.ExternalProcessEndpoint;
import org.onap.kubernetes.viewport.sbi.JAXRSClient;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service("daoFacade")
/**
 * Run with http://localhost:8080/logging-demo/rest/health/health
 *
 */
public class ApplicationService implements ApplicationServiceLocal {
    private final AtomicLong counter = new AtomicLong();
	private static final CharSequence pass = "PASS";
	private static final CharSequence fail = "FAIL";
	
    @Override
    public String view(HttpServletRequest servletRequest) {
      	String message = null;
    	// external call
      	String action = null;
    	message = externalAPICall(action);
    	LoggerFactory.getLogger(this.getClass()).info("Running /view");
       	LoggerFactory.getLogger(this.getClass()).info(message);
        
    	return message;
    }
	
	
    @Override
    public Boolean health(HttpServletRequest servletRequest) {
    	Boolean health = true;
    	// TODO: check database
    	// Log outside the AOP framework - to simulate existing component logs between the ENTRY/EXIT markers
    	LoggerFactory.getLogger(this.getClass()).info("Running /health");
    	return health;
    }
  
    
    private String externalAPICall(String action) {
    	String content = null;
    	JAXRSClient client = new JAXRSClient();

    	
    	// todo
    	// get https://{{aai_ip}}:8443/aai/v8/business/customers/customer/Demonstration/service-subscriptions/service-subscription/vFW/service-instances/
    	// {"service-instance": [ { "service-instance-id": "cd2eb659-2463-461b-9c3b-3bf03619c167",
    	switch(action) {
    	case "sec":
    		content = client.run(false, Configuration.get(Configuration.DC, "coll-ip"), "3904", "events/unauthenticated.SEC_MEASUREMENT_OUTPUT/group3/sub1?timeout=9000", null, null, null);
    	   break;
    	case "tca":
    		content = client.run(false, Configuration.get(Configuration.DC, "coll-ip"), "3904", "events/unauthenticated.TCA_EVENT_OUTPUT/group3/sub1?timeout=9000", null, null, null);
    	   break;
    	case "customer-read":
    		// will get a 4000 on customers/customer but not customers at demo init state
    		content = client.run(true, Configuration.get(Configuration.DC, "aai-ip"), "8443", "aai/v8/business/customers/customer", "AAI", "AAI", "AAI");
    		//content = client.run(true, Configuration.get(Configuration.DC, "aai-ip"), "8443", "aai/v8/service-design-and-creation/models", "AAI", "AAI", "AAI");
    		break;
    	case "init-config": // 1 demo.sh or rest
    		ExternalProcessEndpoint ep_ic = new ExternalProcessEndpoint();
    		content = verifyScript(ep_ic.runExternal("demo.sh","init"));
    		break;
    	case "service-deploy-read": // 2 vid
    		// check endpoints
    		content = "service-deploy-read-return";
    		break;
    	case "service-creation-read": // 2 vid
    		// check endpoints
    		
    		// https://{{aai_ip}}:8443/aai/v8/business/customers/customer/Demonstration/service-subscriptions/service-subscription/vFW/service-instances/
    		// {  	    "service-instance": [    	                         {    	                             "service-instance-id": "9f801217-8cbd-4419-af06-640801422563",    	                             "service-instance-name": "DemoInstance",
    		break;
    	case "vnf-creation-read": // 3 vid
    		// check endpoints
    		ExternalProcessEndpoint ep_vcr = new ExternalProcessEndpoint();
    		content = ep_vcr.runExternalAbsolute("/Users/michaelobrien/wse_onap/onap/extract.sh");
    		System.out.println(content);
    		// https://{{aai_ip}}:8443/aai/v8/network/generic-vnfs
    		//{    	    "generic-vnf": [    	                    {
    	    //                    "vnf-id": "6e81b7e3-3a93-4b6f-8790-be65580a12ce",
    	    //                    "vnf-name": "DemoVNF",
    	    //                    "vnf-type": "fwservice/vsp 1",
    	    //                    "service-id": "0a3bb60f-2cf0-4982-a819-853cb088e916",

    		
    		break;
    	case "vfm-preload": // 4 demo.sh
    		ExternalProcessEndpoint ep_mp = new ExternalProcessEndpoint();
    		content = verifyScript(ep_mp.runExternal("demo.sh","preload", "DemoVNF", "DemoModule"));
    		// verify http://{{sdnc_ip}}:8282/restconf/config/VNF-API:preload-vnfs
    		// network on 
            //"vnf-parameter-name": "ecomp_private_net_id",
           // "vnf-parameter-value": "oam_ecomp_d037"
    		// ips 0= 1= 2=
            //"vnf-parameter-name": "vfw_private_ip_2",
            //"vnf-parameter-value": "10.1.0.11"


    		
    		break;
    	case "vfm-creation-read": // 5 vid or rest
    		// check endpoints
    		break;	
    	case "closed-loop": // 6 demo.sh or rest
    		ExternalProcessEndpoint ep_cl = new ExternalProcessEndpoint();
    		content = verifyScript(ep_cl.runExternal("demo.sh","appc", "DemoModule"));
    		break;	
    	case "ping": // ping -c 1
    		break;
    	case "dc-read":
    		break;
    	case "dc-switch":
    		content = Configuration.switchDC();
    		break;
    	}
    	return content;
    }
    
    private String verifyScript(String content) {
    	// assume failure
    	String passed = "fail";
		// check for FAIL
		if(content.contains(pass)) {
			System.out.println("passed");
			passed = "pass";
		}
		if(content.contains(fail)) {
			System.out.println("failed");
			passed = "fail";
		}
		return passed;
    }

}
