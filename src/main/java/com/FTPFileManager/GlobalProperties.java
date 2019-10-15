package com.FTPFileManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties
public class GlobalProperties {

    @Value("${rootDirectory}")
    @NotNull
    private String rootDirectory;

    @Value("${documentImage}")
    @NotNull
    private String documentImage;

    @Value("${directoryImage}")
    @NotNull
    private String directoryImage;

    @Value("${defaultLocale}")
    @NotNull
    private String defaultLocale;

    public String getRootDirectory() {
        return rootDirectory;
    }

    public String getDocumentImage() {
        return documentImage;
    }

    public String getDirectoryImage() {
        return directoryImage;
    }

    public String getDefaultLocale() {
        return defaultLocale;
    }
}