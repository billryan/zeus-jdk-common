package me.yuanbin.common.config;

import com.typesafe.config.Config;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypesafeConfigSingletonTest {

    @Test
    public void testSingleton() {
        Config config1 = TypesafeConfigSingleton.INSTANCE.getConfig();
        Config config2 = TypesafeConfigSingleton.INSTANCE.getConfig();
        Config ciConfig = TypesafeConfigBuilder.build("ci");
        assert config1 == config2;
        // Typesafe ConfigFactory is a cached Singleton
        assert config1 == ciConfig;
        assertEquals(config1.getString("app.profiles.active"), "ci");
    }
}
