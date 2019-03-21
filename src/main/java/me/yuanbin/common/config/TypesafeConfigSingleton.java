package me.yuanbin.common.config;

import com.typesafe.config.Config;

public enum TypesafeConfigSingleton {
    INSTANCE;

    private Config config = TypesafeConfigBuilder.build();

    public Config getConfig() {
        return this.config;
    }
}
