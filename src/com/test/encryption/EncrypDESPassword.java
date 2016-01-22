package com.test.encryption;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

/**
 * DES加密介绍
 * DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
 * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
 * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现
 * 。
 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class EncrypDESPassword {
    public EncrypDESPassword() {
    }

    //测试
    public static void main(String args[]) throws Exception {
        //待加密内容
        String str = "测试内容";

        String partner = "2088121343239530";//商户ID
        String seller = "luozy@isayigo.com";//商户支付宝账号
        String privateKey = "MIICXQIBAAKBgQDPwZyH1yEZ+uFoEC1tE9MuBKR3w/7LMiUPvYTdflQrYhLdO3tdCvY0NB8jE4tgHzyV3QrO39BmMERDtzqWBQOYL9yXxFyLtmv"
                + "tOQ0FRSMReO+1iesflsgFr7/NaZTh1RQf25v4ddXEMoxf4L/5QLPZzpgIToATMXuCYuwdQV+qYwIDAQABAoGBAL23+UBXJQQ7hPFprIHqU7O3S5WO5rMZ5Jw+Hg"
                + "HCJgzD76QVeG8b3n8HhdObOnCZtdLI2uDVo9Jdi0knHpn2lDMVIkMDIY8EejZLHBGTdQM2+Hc0jfQuww0tZ71lj3xH3ZuLXPxEj5DSBnxJ3FHeI9PmesHsE09G9"
                + "3le7S7qusSZAkEA7+mn4v+dodTDL5mAwMPCLES26ODPgjY8+qP3BDgM84J98RAchKiJlvXH1GobZ1QG/skxtQtYJtfUr8GLBd9abwJBAN2v9UkWZGTKLN4HLTSL"
                + "xJuWFuIAzPTwqS8eTEWTk572lw0g1N+Ke5Kyuehxp6Z49D1brPq21bK+rVfWdBE9eU0CQQCLsIG3c1xGEQsBGSDVnGXfvra9i8BnMct5qKS4SSaJVq6cAOSJ/VP"
                + "3mO4caRfBpzRXf7A4mP1fyIWnPEsFRVJzAkAE8EqSmkEaTba6K4bCSOrUXH+eAU6Pllzug8pHMoZzYCL1U+nqIPenyPuDRiTp/H/Ubhpoa3JPTRgngMuNJsVxAk"
                + "BtWQhRh4Ea8G3TLEATJf8TY0fD0gCf/tOa2FNPFKTepoZnA9vYaHm3HItR37Ib0HXVkx3aAwwxhTLXDhvlykog";//商户私钥
        String notifyURL = "http://175.25.25.131:8100/letsgo.busi/rest/zfb/cb";
        String notifyOnlineUrlZfb = "http://101.201.146.35/letsgo/rest/zfb/cb";


        //密码，长度要是8的倍数
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            sb.append(UUID.randomUUID().toString().replaceAll("-", ""));
        }
//        String password = sb.toString();
        String password = "3ac651b2a1e14c00bd0ff96d3c3ea9ad6703a66b4960438597880930ebfd8e1b0006f5" +
                "7e195d4ebd91c158ad7933ecbd4e83b21685624488988a2d8cfa4aaaa4";


        byte[] result = EncrypDESPassword.encrypt(str.getBytes(), password);
        byte[] partnerc = EncrypDESPassword.encrypt(partner.getBytes(), password);
        byte[] sellerc = EncrypDESPassword.encrypt(seller.getBytes(), password);
        byte[] privateKeyc = EncrypDESPassword.encrypt(privateKey.getBytes(), password);
        byte[] notifyURLc = EncrypDESPassword.encrypt(notifyURL.getBytes(), password);
        byte[] notifyOnlineZfb = EncrypDESPassword.encrypt(notifyOnlineUrlZfb.getBytes(), password);

        System.out.println("加密后16进制：" + str2HexStr(result));
        System.out.println("商户Id：" + str2HexStr(partnerc));
        System.out.println("商户支付宝号：" + str2HexStr(sellerc));
        System.out.println("商户私钥：" + str2HexStr(privateKeyc));
        System.out.println("回调函数：" + str2HexStr(notifyURLc));
        System.out.println("支付宝线上回调函数：" + str2HexStr(notifyOnlineZfb));


//        System.out.println("加密后：" + new String(result));
//        System.out.println("加密base64: " + new String(Base64.getEncoder().encode(result)));


        //直接将如上内容解密
        try {
            byte[] bb = hexStringToBytes(str2HexStr(partnerc));
            byte[] decryResult = EncrypDESPassword.decrypt(bb, password);
            System.out.println("解密后：" + new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 加密R
     *
     * @param datasource byte[]
     * @param password   String
     * @return byte[]
     */
    public static byte[] encrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param src      byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String password) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }


    public static String str2HexStr(byte[] bs) {
        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
        }
        return sb.toString();
    }


    public static byte[] parseHexToByteArray(String str)
    {
        int j=0;
        byte[] bytes = new byte[str.length()];
        for(int i=0;i<str.length();i++)
        {
            int int_ch;  /// 两位16进制数转化后的10进制数
            char hex_char1 = str.charAt(i); ////两位16进制数中的第一位(高位*16)
            int int_ch1;
            if(hex_char1 >= '0' && hex_char1 <='9')
                int_ch1 = (hex_char1-48)*16;   //// 0 的Ascll - 48
            else if(hex_char1 >= 'A' && hex_char1 <='F')
                int_ch1 = (hex_char1-55)*16; //// A 的Ascll - 65
            else
                int_ch1 = (hex_char1-87)*16; //// a 的Ascll - 97
            i++;
            char hex_char2 = str.charAt(i); ///两位16进制数中的第二位(低位)
            int int_ch2;
            if(hex_char2 >= '0' && hex_char2 <='9')
                int_ch2 = (hex_char2-48); //// 0 的Ascll - 48
            else if(hex_char2 >= 'A' && hex_char1 <='F')
                int_ch2 = hex_char2-55; //// A 的Ascll - 65
            else
                int_ch2 = hex_char2-87; //// a 的Ascll - 97

            int_ch = int_ch1+int_ch2;
            bytes[j] = (byte)int_ch;  ///将转化后的数放入Byte数组里
            j++;
        }

        return bytes;
    }



    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }


    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

}


