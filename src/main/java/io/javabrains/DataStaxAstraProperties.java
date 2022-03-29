package io.javabrains;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;

@ConfigurationProperties(prefix = "datastax.astra")
public class DataStaxAstraProperties {
    private File securityConnectBundle;

    public DataStaxAstraProperties() {
    }

    public DataStaxAstraProperties(File securityConnectBundle) {
        this.securityConnectBundle = securityConnectBundle;
    }

    public File getSecurityConnectBundle() {
        return securityConnectBundle;
    }

    public void setSecurityConnectBundle(File securityConnectBundle) {
        this.securityConnectBundle = securityConnectBundle;
    }
}
