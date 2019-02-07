package org.onap.kubernetes.viewport.sbi.oss;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

import javax.xml.bind.DatatypeConverter;

public class Authenticator implements ClientRequestFilter {

	private final String user;
	private final String pass;

	public Authenticator(String user, String password) {
		this.user = user;
		this.pass = password;
	}

	public void filter(ClientRequestContext requestContext) throws IOException {
		MultivaluedMap<String, Object> headMap = requestContext.getHeaders();
		String basicAuth = null;
		try {
			String aToken = user + ":" + pass;
			basicAuth = "BASIC " + DatatypeConverter.printBase64Binary(aToken.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException uee) {
			throw new IllegalStateException("Encoding with UTF-8 failed", uee);
		}
		headMap.add("Authorization", basicAuth);
	}
}