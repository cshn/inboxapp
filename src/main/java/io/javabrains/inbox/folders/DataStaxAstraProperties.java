package io.javabrains.inbox.folders;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
@ConfigurationProperties(prefix = "datastax.astra")
public class DataStaxAstraProperties {
    private File securityConnectBundle;
//
//    public DataStaxAstraProperties() {
//    }
//
//    public DataStaxAstraProperties(File securityConnectBundle) {
//        this.securityConnectBundle = securityConnectBundle;
//    }

    public File getSecurityConnectBundle() {
        System.out.println("getting security connect bundle ");
        //+ securityConnectBundle.toString());
        return securityConnectBundle;
    }

    public void setSecurityConnectBundle(File securityConnectBundle) {
        this.securityConnectBundle = securityConnectBundle;
    }
}
