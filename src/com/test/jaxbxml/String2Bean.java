package com.test.jaxbxml;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * Created by zhangfan on 16/1/16.
 */
public class String2Bean {
    public static void main(String[] args) throws Exception {

        String xml = "<xml>\n" +
                "   <appid>wx2421b1c4370ec43b</appid>\n" +
                "   <mch_id>10000100</mch_id>\n" +
                "   <nonce_str>ec2316275641faa3aacf3cc599e8730f</nonce_str>\n" +
                "   <transaction_id>1008450740201411110005820873</transaction_id>\n" +
                "   <sign>FDD167FAA73459FD921B144BAF4F4CA2</sign>\n" +
                "</xml> ";
        String xml1 = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg>" +
                "<appid><![CDATA[wxdfe7743986030fcc]]></appid><mch_id><![CDATA[1287883601]]></mch_id><nonce_str><![CDATA[535XHkiZwWeOolQM]]></nonce_str>" +
                "<sign><![CDATA[60A7C1CA483B2A17A4BABB9662128855]]></sign><result_code><![CDATA[SUCCESS]]></result_code>" +
                "<openid><![CDATA[oZS5dwVVxUg3HNll7vi7u5RVIBKk]]></openid><is_subscribe><![CDATA[N]]></is_subscribe>" +
                "<trade_type><![CDATA[APP]]></trade_type><bank_type><![CDATA[CFT]]></bank_type><total_fee>1</total_fee>" +
                "<fee_type><![CDATA[CNY]]></fee_type><transaction_id><![CDATA[1005850210201601172748315122]]></transaction_id>" +
                "<out_trade_no><![CDATA[1194830142_0]]></out_trade_no><attach><![CDATA[]]></attach><time_end><![CDATA[20160117145853]]></time_end>" +
                "<trade_state><![CDATA[SUCCESS]]></trade_state><cash_fee>1</cash_fee></xml>";


        JAXBContext jaxbContext = JAXBContext.newInstance(ReceiveWeiXingAppDataReq.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        ReceiveWeiXingAppDataReq receiveWeiXingAppDataReq = (ReceiveWeiXingAppDataReq) unmarshaller.unmarshal(new StringReader(xml));
        System.out.println(receiveWeiXingAppDataReq.toString());


        ByteArrayInputStream bais = new ByteArrayInputStream(xml1.getBytes());
        WeiXinCBData weiXinCBData = JaxbUtil.converyToJavaBean(bais, WeiXinCBData.class);
        System.out.println(weiXinCBData);
        System.out.println(convertToXml(receiveWeiXingAppDataReq, "utf-8"));

    }


    /**
     * JavaBean转换成xml
     *
     * @param obj
     * @param encoding
     * @return
     */
    public static String convertToXml(Object obj, String encoding) {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
