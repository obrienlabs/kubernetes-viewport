
package org.onap.kubernetes.viewport;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Qualifier;

@Path("/health")
public class RestHealthServiceImpl extends Application {
    
    @Context private HttpServletRequest servletRequest;
    
	@Inject
	@Qualifier("daoFacade")
    private ApplicationServiceLocal applicationServiceLocal;
	
	@GET
	@Path("/health")
	@Produces(MediaType.TEXT_HTML)
	public String getHealth() {

	    return applicationServiceLocal.health(servletRequest).toString();
	}
	
	/**
	 * Use only for testing
	 * @param aService
	 */
	public void setApplicationService(ApplicationServiceLocal aService) {
	    applicationServiceLocal = aService;
	}
	
}

