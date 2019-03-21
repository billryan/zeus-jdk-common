package me.yuanbin.common.config;

import com.google.common.base.Strings;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class TypesafeConfigBuilder {
    private static final String PROFILES = "APP_PROFILES_ACTIVE";
    private static final String DEFAULT_PROFILES = "dev";

    private static String getConfigFile(String env) {
        String configEnv = env;
        if (Strings.isNullOrEmpty(env)) {
            String envVar = System.getenv(PROFILES);
            String propVar = System.getProperty(PROFILES);
            // read from -D first, then OS environment.
            // Set with default profiles if env and prop is empty.
            configEnv = Strings.isNullOrEmpty(propVar) ? envVar : propVar;
        }

        if (Strings.isNullOrEmpty(configEnv)) configEnv = DEFAULT_PROFILES;
        return String.format("application-%s.conf", configEnv);
    }

    public static Config build() {
        return build(null);
    }

    public static Config build(String env) {
        String configFile = getConfigFile(env);
        return ConfigFactory.load(configFile);
    }
}
