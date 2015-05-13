package com.test.https;


import javax.security.cert.X509Certificate;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by zhangfan on 2015/5/12.
 */
public class X509CertificateTest {

    public static void main(String[] args) throws Exception {

//        InputStream inputStream = new FileInputStream(new File("D://home/tomcat.cer"));
        InputStream inputStream = new FileInputStream(new File("D://home/mykey.cer"));

        X509Certificate x509Certificate = X509Certificate.getInstance(inputStream);

        System.out.println(x509Certificate.getSigAlgName());
        System.out.println(x509Certificate.getVersion());
        System.out.println(x509Certificate.getPublicKey());
    }
}
