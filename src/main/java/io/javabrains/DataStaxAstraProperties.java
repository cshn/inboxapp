package io.javabrains;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@ConfigurationProperties(prefix = "datastax.astra")
public class DataStaxAstraProperties {
    private File securityConnectBundle;

    public File getSecurityConnectBundle() {
        return securityConnectBundle;
    }

    public void setSecurityConnectBundle(File securityConnectBundle) {
        this.securityConnectBundle = securityConnectBundle;
    }
}
