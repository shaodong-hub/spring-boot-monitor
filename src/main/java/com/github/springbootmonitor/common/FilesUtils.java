package com.github.springbootmonitor.common;

import com.google.common.base.Charsets;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.CharsetDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 创建时间为 14:06 2019-06-10
 * 项目名称 spring-boot-monitor
 * </p>
 *
 * @author 石少东
 * @version 0.0.1
 * @since 0.0.1
 */

public class FilesUtils {

    @SneakyThrows(IOException.class)
    public static List<String> readAllLines(InputStream inputStream) {
        try (BufferedReader reader = newBufferedReader(inputStream)) {
            List<String> result = new ArrayList<>();
            for (; ; ) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                result.add(line);
            }
            return result;
        }
    }

    private static BufferedReader newBufferedReader(InputStream inputStream) {
        CharsetDecoder decoder = Charsets.UTF_8.newDecoder();
        Reader reader = new InputStreamReader(inputStream, decoder);
        return new BufferedReader(reader);
    }

}
