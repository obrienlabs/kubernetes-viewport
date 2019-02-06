
package org.onap.kubernetes.viewport;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("read")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        // http://127.0.0.1:8180/logging-demo/rest/health/health
        // http://127.0.0.1:8180/logging-demo/rest/read/test
        classes.add(RestServiceImpl.class);
        classes.add(RestHealthServiceImpl.class);
        return classes;
    }
}
