package me.yuanbin.common.config;

import com.typesafe.config.Config;
import org.junit.Test;

public class AppConfigHolderTest {

    @Test
    public void testSingleton() {
        Config config1 = AppConfigHolder.INSTANCE.getConfig();
        Config config2 = AppConfigHolder.INSTANCE.getConfig();

        assert config1 == config2;
    }
}
