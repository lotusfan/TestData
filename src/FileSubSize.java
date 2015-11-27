import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangfan on 2015/8/11.
 */
public class FileSubSize {
    public static void main(String[] args) throws Exception {

        String path2 = "C:\\Users\\zf\\Desktop\\2spring-core-4.1.1.RELEASE";
        String path1 = "C:\\Users\\zf\\Desktop\\spring-core-4.1.1.RELEASE";
        File file = new File(path1);
        File file1 = new File(path2);
        Map<String, Long> map = new HashMap<>();
        Map<String, Long> map1 = new HashMap<>();

        getSize(file, map);
        getSize(file1, map1);


        for (String key : map.keySet()) {
            System.out.print(key + "--" + map.get(key) + "|" + map1.get(key));
            Long aLong = map.get(key);
            Long bLong = map1.get(key);

            if (!aLong.equals(bLong)) {
                System.out.print("            *******************");
            }
            System.out.println();
        }




    }

    public static void getSize(File file,Map<String, Long> map) throws Exception {

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                getSize(file1, map);
            }
        } else {
            map.put(file.getName(), file.length());
        }
    }
}
