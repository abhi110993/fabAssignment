package com.fabHotels.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ResourceLoader {

	 private ResourceLoader() {
	 }

    public static URL getResourceUrl(String resourceName) throws IOException {
        ClassLoader classLoader = ResourceLoader.class.getClassLoader();

        URL resourceUrl = null;

        if (classLoader != null) {
        	resourceUrl = classLoader.getResource(resourceName);
        }

        if (resourceUrl == null) {
            classLoader = ClassLoader.getSystemClassLoader();
            if (classLoader != null) {
            	resourceUrl = classLoader.getResource(resourceName);
            }
        }

        return resourceUrl;
    }
    
	    public static InputStream loadResource(String resourceName) throws IOException {
	        ClassLoader classLoader = ResourceLoader.class.getClassLoader();

	        InputStream inputStream = null;

	        if (classLoader != null) {
	            inputStream = classLoader.getResourceAsStream(resourceName);
	        }

	        if (inputStream == null) {
	            classLoader = ClassLoader.getSystemClassLoader();
	            if (classLoader != null) {
	                inputStream = classLoader.getResourceAsStream(resourceName);
	            }
	        }

	        if (inputStream == null) {
	            File file = new File(resourceName);
	            if (file.exists()) {
	                inputStream = new FileInputStream(file);
	            }
	        }

	        return inputStream;
	    }

	    public static Properties loadProperties(String resourceName) throws IOException {
	        Properties properties = null;
	        InputStream inputStream = null;
	        try {
	            inputStream = loadResource(resourceName);
	            if (inputStream != null) {
	                properties = new Properties();
	                properties.load(inputStream);
	            }
	        } finally {
	            if (inputStream != null) {
	                inputStream.close();
	            }
	        }
	        return properties;
	    }

}
