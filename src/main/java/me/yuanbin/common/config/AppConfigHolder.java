package me.yuanbin.common.config;

import com.typesafe.config.Config;

public enum AppConfigHolder {
    INSTANCE;

    private final Config config = AppConfigFactory.load();

    public Config getConfig() {
        return config;
    }
}
