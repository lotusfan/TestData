package com.test.dbmysql.makeproject;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by zhangfan on 2015/5/25.
 */
public class MainUtil {

    /**
     * “_小”转换为大写字母
     *
     * @param str
     * @return
     */
    public static String LineToUpper(String str) {

        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {

            if (chars[i] == '_' && i < chars.length - 1) {
                i++;
                chars[i] = Character.toUpperCase(chars[i]);
            }
        }

        return new String(chars).replaceAll("_", "");
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String UpperToFirst(String str) {
        if (Character.isLowerCase(str.charAt(0))) {
            return str.replaceFirst(str.charAt(0) + "", Character.toUpperCase(str.charAt(0)) + "");
        }
        return str;
    }

    /**
     * 写入文件
     *
     * @param name
     * @param stringBuffer
     */

    public static void writeFile(String name, StringBuffer stringBuffer, String path) {

        FileOutputStream fileOutputStream = null;
        try {

            File file = new File(path + name);

            byte[] bytes = stringBuffer.toString().getBytes();
            ByteInputStream byteInputStream = new ByteInputStream(bytes, bytes.length);
            fileOutputStream = new FileOutputStream(file);

            int i = 0;
            while ((i = byteInputStream.read()) > 0) {
                fileOutputStream.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 路径名转为包名
     *
     * @param path
     * @return
     */
    public static String pathToPackageName(String path) {
        path = path.substring(14, path.length()-1);
        path = path.replaceAll("/", ".");
        return path;
    }
}
