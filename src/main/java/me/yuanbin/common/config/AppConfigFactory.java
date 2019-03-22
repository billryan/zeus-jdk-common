package me.yuanbin.common.config;

import com.google.common.base.Strings;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class AppConfigFactory {
    private static final String ACTIVE_PROFILE = "APP_PROFILES_ACTIVE";
    private static final String DEFAULT_PROFILE = "dev";

    /**
     * priority: profile > propVar > envVar > DEFAULT_PROFILE
     * read from -D first, then OS environment.
     * Set with default profiles if env and prop is empty.
     * @param profile
     * @return application profile filename
     */
    private static String getAppProfile(String profile) {
        String envVar = System.getenv(ACTIVE_PROFILE);
        String propVar = System.getProperty(ACTIVE_PROFILE);
        String propEnvProfile = Strings.isNullOrEmpty(propVar) ? envVar : propVar;
        String customProfile = Strings.isNullOrEmpty(profile) ? propEnvProfile : profile;
        String appProfile = Strings.isNullOrEmpty(customProfile) ? DEFAULT_PROFILE : customProfile;
        return String.format("application-%s.conf", appProfile);
    }

    /**
     * read System -D APP_PROFILES_ACTIVE or Environment
     * @return Application Config Singleton Instance
     */
    public static Config load() {
        return load(null);
    }

    public static Config load(String env) {
        String configFile = getAppProfile(env);
        return ConfigFactory.load(configFile);
    }
}
