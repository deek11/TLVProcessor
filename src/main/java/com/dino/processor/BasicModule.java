package com.dino.processor;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Guice Injector
 */
public class BasicModule extends AbstractModule {

    private final String CONFIG_FILE_PATH = "config.properties";

    protected void configure() {

        try {
            Properties props = new Properties();
            props.load(new FileInputStream(CONFIG_FILE_PATH));
            Names.bindProperties(binder(), props);
            bind(Processor.class).to(TLVProcessor.class);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }


    }
}
