import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.*;

/**
 * Created by zhangfan on 2015/8/24.
 */
public class FileSql {
    public static void main(String[] args) {
        String str = "E:\\javalib\\jeesite2\\thinkgem-jeesite-6c5ea82\\db";
        StringBuffer stringBuffer = new StringBuffer();
        addSql(new File(str), stringBuffer);
        writeFile("sqltemp.sql", stringBuffer, "E:\\javalib\\jeesite2\\thinkgem-jeesite-6c5ea82\\");

    }

    public static void addSql(File file, StringBuffer stringBuffer) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                addSql(files[i], stringBuffer);
            }
            return;
        }

        if (file.getName().contains("sql")) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String tem = "";
                while ((tem = reader.readLine()) != null) {
                    stringBuffer.append(tem);
                    stringBuffer.append("\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
}
