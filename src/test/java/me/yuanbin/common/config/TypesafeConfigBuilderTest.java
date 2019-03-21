package me.yuanbin.common.config;

import com.typesafe.config.Config;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypesafeConfigBuilderTest {
    private Config config = TypesafeConfigBuilder.build("ci");

    @Test
    public void testConfig() {
        assertEquals("ci.yuanbin.me", config.getString("common.host"));
        assertEquals(8080, config.getInt("common.port"));
        assertEquals("ci", config.getString("app.profiles.active"));
    }
}
