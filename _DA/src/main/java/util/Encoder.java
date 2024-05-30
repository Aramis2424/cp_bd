package util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class Encoder {
    public static String toUTF8(String str1251) {
        byte[] bytes = str1251.getBytes(StandardCharsets.ISO_8859_1);
        String strUTF;
        strUTF = new String(bytes, StandardCharsets.UTF_8);
        return strUTF;
    }

    public static String to1251(String strUTF) {
        byte[] bytes = strUTF.getBytes(StandardCharsets.UTF_8); // Преобразование из UTF-8 в Windows-1251
        String str1251;
        try {
            str1251 = new String(bytes, "windows-1251");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        return str1251;
    }
}
