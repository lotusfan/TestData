import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhangfan on 2015/8/11.
 */
public class StringNotRepeatFirst {

    public static void main(String[] args) {

        String str = "lllllllllllllllldlllllll9llllldlllllllllllllllldlllllldllllsooosssoopeoooooooooooooooooooowppppppppppppp";

        char[] chars = str.toCharArray();
        Set<Character> characterSet = new HashSet<>();


        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if (characterSet.contains(c)) {
                continue;
            }
            for (int j = i+1; j < chars.length; j++) {
                if (c == chars[j]) {
                    characterSet.add(c);
                    break;
                }
                if (j == chars.length-1) {
                    System.out.println(c);
                    return;
                }
            }

        }
    }
}
