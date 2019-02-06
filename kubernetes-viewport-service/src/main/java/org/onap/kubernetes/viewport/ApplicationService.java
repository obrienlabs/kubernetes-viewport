
package org.onap.kubernetes.viewport;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service("daoFacade")
/**
 * Run with http://localhost:8080/logging-demo/rest/health/health
 *
 */
public class ApplicationService implements ApplicationServiceLocal {
	
    @Override
    public Boolean health(HttpServletRequest servletRequest) {
    	Boolean health = true;
    	// TODO: check database
    	// Log outside the AOP framework - to simulate existing component logs between the ENTRY/EXIT markers
    	LoggerFactory.getLogger(this.getClass()).info("Running /health");
    	return health;
    }
  
}
