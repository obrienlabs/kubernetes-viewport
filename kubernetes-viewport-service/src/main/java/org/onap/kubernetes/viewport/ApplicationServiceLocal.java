
package org.onap.kubernetes.viewport;

import javax.servlet.http.HttpServletRequest;

public interface ApplicationServiceLocal {
	Boolean health(HttpServletRequest request);
}
