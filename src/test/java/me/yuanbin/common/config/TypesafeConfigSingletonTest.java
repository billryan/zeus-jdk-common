package me.yuanbin.common.config;

import com.typesafe.config.Config;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypesafeConfigSingletonTest {

    @Test
    public void testSingleton() {
        Config config1 = TypesafeConfigSingleton.INSTANCE.getConfig();
        Config config2 = TypesafeConfigSingleton.INSTANCE.getConfig();
        assertEquals(config1, config2);
        assertEquals(config1.getString("app.profiles.active"), "ci");
    }
}
