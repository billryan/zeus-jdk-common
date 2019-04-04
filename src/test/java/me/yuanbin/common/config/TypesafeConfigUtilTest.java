package me.yuanbin.common.config;

import com.google.common.collect.ImmutableList;
import com.typesafe.config.Config;
import org.junit.Test;

import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TypesafeConfigUtilTest {

    @Test
    public void testGetStringList() {
        Config config = AppConfigFactory.load();
        List<String> taskBRegexList = TypesafeConfigUtil.getStringList(config, "task.taskB.regex");
        List<String> expectedList = ImmutableList.of("maxwell\\.jdk");
        assertEquals(expectedList, taskBRegexList);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testGetRegexException() {
        Config config = AppConfigFactory.load();
        String taskARegex = TypesafeConfigUtil.getRegex(config, "task.taskA.regex");
    }

    @Test
    public void testGetRegex() {
        Config config = AppConfigFactory.load();
        String taskBRegex = TypesafeConfigUtil.getRegex(config, "task.taskB.regex");
        String taskCRegex = TypesafeConfigUtil.getRegex(config, "task.taskC.regex");
        assertEquals("maxwell\\.jdk", taskBRegex);
        assertEquals("maxwell\\.jdk|zeus\\..*", taskCRegex);

        Pattern patternB = Pattern.compile(taskBRegex);
        String strB1 = "maxwell.jdk";
        assertTrue(patternB.matcher(strB1).matches());
        String strB2 = "maxwellXjdk";
        assertFalse(patternB.matcher(strB2).matches());

        String strC1 = "zeus.table";
        String strC2 = "zeusXtable";
        Pattern patternC = Pattern.compile(taskCRegex);
        assertTrue(patternC.matcher(strC1).matches());
        assertTrue(patternC.matcher(strB1).matches());
        assertFalse(patternC.matcher(strC2).matches());
    }
}
