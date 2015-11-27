import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhangfan on 2015/2/10.
 */
public class TestMain {
    public static void main(String[] args) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYMMddHHmmss");

        System.out.println(simpleDateFormat.format(new Date()));

    }
}
