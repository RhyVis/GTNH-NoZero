package com.rhynia.gtnh_no_zero;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static String greeting = "No more zero in recipe time!";
    public static int timeLimit = 30;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        greeting = configuration.getString("greeting", Configuration.CATEGORY_GENERAL, greeting, "How shall I greet?");
        timeLimit = configuration.getInt(
            "timeLimit",
            Configuration.CATEGORY_GENERAL,
            timeLimit,
            1,
            Integer.MAX_VALUE,
            "Those time that larger than this will be halved until less than this value, unit: seconds");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
