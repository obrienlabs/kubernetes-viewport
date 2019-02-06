
package org.onap.kubernetes.viewport;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Qualifier;

@Path("/read")
public class RestServiceImpl extends Application {
    @Inject
    @Qualifier("daoFacade")
    private ApplicationServiceLocal applicationServiceLocal;

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_HTML)
    public String getTest() {
        return "testing: " + applicationServiceLocal;

    }
    
    /**
     * Use only for testing
     * @param aService
     */
    public void setApplicationService(ApplicationServiceLocal aService) {
        applicationServiceLocal = aService;
    }
}

