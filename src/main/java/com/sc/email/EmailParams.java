package com.sc.email;

import com.sc.util.ConfigurationUtil;
import org.apache.commons.configuration.Configuration;

public class EmailParams {
   // public static String filePath ="./src/main/java/com/test/properties/emailConfig.properties";
    public static final Configuration file = ConfigurationUtil.getCommonsPropertis();
    public static final String host = file.getString("mail.host");
    public static final String username = file.getString("mail.username");
    public static final String password = file.getString("mail.password");
    public static final String title = file.getString("mail.title");
    public static final String[] tousers = file.getStringArray("mail.touser");
    public static final String[] ccaddress = file.getStringArray("mail.ccaddress");

}
