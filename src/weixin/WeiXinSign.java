package weixin;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhangfan on 15/12/7.
 */

public class WeiXinSign {


    public static void main(String[] args) throws Exception {
        Map<String, String> attrMap = new HashMap<>();
        List<String> attrNameList = new ArrayList<String>();

        WeiXinBefor weiXinBefor = new WeiXinBefor();
        weiXinBefor.setAppid("wxdfe7743986030fcc");
        weiXinBefor.setMch_id("1287883601");
        weiXinBefor.setNonce_str(UUIDGenerator.getUUID());
//        weiXinBefor.setNonce_str("B31A21E25F1C4E97BE494FA6DCA84D58");
        weiXinBefor.setBody("letsgao");
        weiXinBefor.setOut_trade_no("20151207test");
        weiXinBefor.setTotal_fee(1);
        weiXinBefor.setSpbill_create_ip("58.132.171.184");
        weiXinBefor.setNotify_url("http://58.132.171.184/letsgo");
        weiXinBefor.setTrade_type("APP");
        weiXinBefor.setPrepay_id("376C6708D79859A22E06EC513768363B");


        JSONObject attrJson = JSONObject.parseObject(JSONObject.toJSONString(weiXinBefor));
        System.out.println(attrJson);
        Set<String> attrNameSet = attrJson.keySet();
        for (String name : attrNameSet) {
            attrNameList.add(name);
        }


        String s = signString(attrJson, attrNameList, "27D5A9EDDADD4D05B6C776B99BB49566");

        s = Md5Encrypt.md5(s, "UTF-8");
        attrJson.put("sign", s.toUpperCase());

        System.out.println(sendXMLData(attrJson));

        String returnStr = sendRequest(sendXMLData(attrJson));

        System.out.println(returnStr);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(returnStr));

        Document doc = db.parse(is);
        NodeList nodes = doc.getElementsByTagName("prepay_id");

        if(nodes.getLength() == 1){
            WeiXinAppData weiXinAppData = new WeiXinAppData();
            weiXinAppData.setAppid("wxdfe7743986030fcc");
            weiXinAppData.setNoncestr(UUIDGenerator.getUUID());
            weiXinAppData.setPartnerid("1287883601");
            weiXinAppData.setPrepayid(getCharacterDataFromElement((Element) nodes.item(0)).toUpperCase());
            weiXinAppData.setTimestamp(String.valueOf(System.currentTimeMillis() / 1000));

            List<String> attrAppNameList = new ArrayList<String>();
            JSONObject attrAppJson = JSONObject.parseObject(JSONObject.toJSONString(weiXinAppData));
            System.out.println(attrAppJson);
            Set<String> attrAppNameSet = attrAppJson.keySet();
            for (String name : attrAppNameSet) {
                attrAppNameList.add(name);
            }

            String appData = signString(attrAppJson, attrAppNameList, "27D5A9EDDADD4D05B6C776B99BB49566");
            System.out.println(appData);
            appData = Md5Encrypt.md5(s, "UTF-8");
            System.out.println(appData.toUpperCase());
            attrAppJson.put("sign", appData.toUpperCase());
            attrAppJson.put("package","Sign=WXPay");
            System.out.println(attrAppJson);

        }

    }
    public static String getCharacterDataFromElement(Element e) {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "?";
    }

    /**
     * 微信支付签名生成
     *
     * @param json
     * @param list
     * @param key
     * @return
     */
    public static String signString(JSONObject json, List<String> list, String key) {

        Collections.sort(list);

        StringBuffer stringBuffer = new StringBuffer();

        for (String str : list) {
            if ("sign".equals(str)) {
                continue;
            }
            stringBuffer.append(str);
            stringBuffer.append("=");
            stringBuffer.append(json.get(str));
            stringBuffer.append("&");
        }
        stringBuffer.append("key=");
        stringBuffer.append(key);


        return stringBuffer.toString();

    }

    /**
     * 微信支付封装请求数据
     *
     * @param json
     * @return
     */
    public static String sendXMLData(JSONObject json) {

        StringBuffer sb = new StringBuffer();

        Set<String> set = json.keySet();

        sb.append("<xml>");
        for (String str : set) {
            sb.append("<" + str + ">");
            sb.append(json.get(str));
            sb.append("</" + str + ">");
        }
        sb.append("</xml>");

        return sb.toString();
    }

    public static String sendRequest(String data) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        URI uri = new URIBuilder().setScheme("https")
                .setHost("api.mch.weixin.qq.com")
                .setPath("//pay/unifiedorder")
                .build();

        System.out.println(uri.toString());
        HttpPost httpPost = new HttpPost(uri);
        httpPost.setEntity(new StringEntity(data));
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);

            HttpEntity httpEntity = response.getEntity();
            InputStream inputStream = httpEntity.getContent();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = "";
            StringBuffer stringBuffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                stringBuffer.append(str);
            }

            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}

class WeiXinBefor {

    private String appid; //公众账号ID
    private String mch_id; //商户号
    private String device_info; //设备号
    private String nonce_str; //随机字符串
    private String body; //商品描述
    private String attach; //附加数据
    private String out_trade_no; //商户订单号
    private int total_fee; //总金额
    private String spbill_create_ip; //终端ip
    private String goods_tag; //商品标记
    private String sign; //签名
    private String detail; //商品详情
    private String fee_type; //货币类型
    private String time_start; //交易超始时间
    private String time_expire; //交易结束时间
    private String notify_url; //通知地址
    private String trade_type; //交易类型
    private String product_id; //商品Id
    private String limit_pay; //指定支付方式
    private String openid; //用户标识
    private String prepay_id;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public int getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_expire() {
        return time_expire;
    }

    public void setTime_expire(String time_expire) {
        this.time_expire = time_expire;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getLimit_pay() {
        return limit_pay;
    }

    public void setLimit_pay(String limit_pay) {
        this.limit_pay = limit_pay;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }
}

class WeiXinAppData{
    private String appid;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
