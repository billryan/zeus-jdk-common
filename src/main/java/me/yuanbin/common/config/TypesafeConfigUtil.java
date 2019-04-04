package me.yuanbin.common.config;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;

import java.util.List;
import java.util.stream.Collectors;

public class TypesafeConfigUtil {

    /**
     * get string list from given path
     * @param config TypesafeConfig instance
     * @param path relative path in config
     * @return list of string
     */
    public static List<String> getStringList(Config config, String path) {
        List<String> sourceTopics = ImmutableList.of();
        if (!Strings.isNullOrEmpty(path) && config.hasPath(path)) {
            try {
                sourceTopics = ImmutableList.copyOf(config.getStringList(path));
            } catch (ConfigException.WrongType ex) {
                sourceTopics = ImmutableList.of(config.getString(path));
            }
        }
        return sourceTopics;
    }

    /**
     * get regex string from given path,
     * concatenate list of strings
     * @param config TypesafeConfig instance
     * @param path relative path in config
     * @return regex string
     */
    public static String getRegex(Config config, String path) {
        List<String> regexList = getStringList(config, path);
        if (regexList.size() == 0) {
            throw new IllegalArgumentException(String.format("path %s should not be empty!", path));
        } else if (regexList.size() == 1) {
            return regexList.get(0);
        } else {
            return String.join("|", regexList);
        }
    }
}
