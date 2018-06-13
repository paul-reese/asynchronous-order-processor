package io.pivotal.sample.messageprocessor;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("vcap.services.legacyorderservice.credentials")
public class LegacyOrderManagementServiceProperties {
    
    //This will be automatically set by having a user-provided service
    //called 'legacyorderservice' with a 'url' parameter.
    private String url;

    public void setUrl(String url) {this.url = url;}
    public String getUrl() {
        if (this.url != null && !this.url.isEmpty()) {
            return this.url;
        } else {
            return "http://localhost:8082";
        }
    }
}