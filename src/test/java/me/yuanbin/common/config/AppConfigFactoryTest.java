package me.yuanbin.common.config;

import com.typesafe.config.Config;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppConfigFactoryTest {

    @Test
    public void testConfig() {
        Config config = AppConfigFactory.load();
        assertEquals("ci.yuanbin.me", config.getString("common.host"));
        assertEquals(8080, config.getInt("common.port"));
        assertEquals("ci", config.getString("app.profiles.active"));
    }

    @Test
    public void testSingleton() {
        Config config1 = AppConfigFactory.load();
        Config config2 = AppConfigFactory.load();
        assert config1 == config2;
    }
}
