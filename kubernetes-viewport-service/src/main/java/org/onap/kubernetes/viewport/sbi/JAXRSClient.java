package org.onap.kubernetes.viewport.sbi;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.SslConfigurator;
import org.onap.kubernetes.viewport.sbi.oss.Authenticator;

public class JAXRSClient {
 
	public String run(boolean isSSL, String ip, String port, String path, String authUser, String authPass, String appId) {
		String record = null;
		String prefix = null;
		Client client = null;
		WebTarget rootTarget = null;
		if(isSSL) {
			//SslConfigurator sslConfig = SslConfigurator.newInstance();
			SslConfigurator sslConfig = SslConfigurator.newInstance()
					.trustStoreFile("/opt/KeyStore.jks")
					.trustStorePassword("changeme")//"changeit")
					.keyStoreFile("/opt/KeyStore.jks")
					.keyPassword("changeme");//"changeit");
			SSLContext sslContext = sslConfig.createSSLContext();
			// fix java.security.cert.CertificateException: No subject alternative names present
			HostnameVerifier verifier = new HostnameVerifier() {
				public boolean verify(String hostname, SSLSession sslSession) {
					return true; // TODO: security breach
				}};

			client = ClientBuilder.newBuilder().sslContext(sslContext).hostnameVerifier(verifier).build();
			client.register(new Authenticator(authUser, authPass));
			prefix = "https://";
		} else {
			client = ClientBuilder.newClient();
			prefix = "http://";
		}
		rootTarget = client.target(prefix + ip + ":" + port + "/" + path);
		try {
				record = rootTarget.request()
						.header("X-FromAppId", appId).header("Accept", "application/json")
						.get(String.class);
				System.out.println(record);
			} catch (Exception e)  {
				e.printStackTrace();
			} finally {
				client.close();
			}
		return record;
	}

}
