package ru.goohope.gameservice.controller.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Converters {

    @Component
    public class StringToList implements Converter<String, List<String>> {

        @Override
        public List<String> convert(String source) {
            return Arrays.asList(source.split(","));
        }
    }

}
