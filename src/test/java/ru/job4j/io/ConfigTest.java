package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), ("Petr Arsentev"));
    }

    @Test
    public void whenFileContainsPoundOrSpace() {
        String path = "./data/file_contains_pound_or_space.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("firstName"), ("Petr Arsentev"));
        assertEquals(config.value("secondName"), ("Marat Bashirov"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairIsNotFull() {
        String path = "./data/pair_is_not_full.properties";
        Config config = new Config(path);
        config.load();
        config.value("name");
    }
}