import java.util.Random;

/**
 * Created by zhangfan on 2015/10/21.
 */
public class QuanRongInviteCode {


    public static void main(String[] args) {
        System.out.println(getRandomCharArray(6).toLowerCase());

    }

    public static String getRandomCharArray(int iLength) {
        String val = "";

        Random random = new Random();
        for(int i = 0; i < iLength; i++)
        {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字

            if("char".equalsIgnoreCase(charOrNum)) // 字符串
            {
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                char temp = (char) (choice + random.nextInt(26));
                if(String.valueOf(temp).equals("l")||String.valueOf(temp).equals("L")||String.valueOf(temp).equals("O")||String.valueOf(temp).equals("o")){
                    temp='a';
                }
                val += temp;
            }
            else if("num".equalsIgnoreCase(charOrNum)) // 数字
            {
                int temp = random.nextInt(10);
                //剔除0和1
                if(temp<2){
                    int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母
                    val += (char) (choice + random.nextInt(26));
                }else {
                    val += temp;
                }
            }
        }

        return val;
    }
}
