package com.sc.util;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

     public class ConfigurationUtil {
        static  Configuration configuration;
        public static Configuration getCommonsPropertis() {
            try {
                configuration = new PropertiesConfiguration("emailConfig.properties");
               // configuration = new PropertiesConfiguration(fileName);
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
            return configuration;
        }
}
