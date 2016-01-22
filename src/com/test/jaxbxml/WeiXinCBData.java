package com.test.jaxbxml;

import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
public class WeiXinCBData{

	private Integer id; //
	private String return_code; //
	private String return_msg; //
	private String appid; //公众账号ID
	private String mch_id; //商户号
	private String device_info; //设备号
	private String nonce_str; //随机字符串
	private String sign; //签名
	private String result_code; //业务结果SUCCESS/FAIL
	private String err_code; //错误代码
	private String err_code_des; //错误代码描述
	private String trade_type; //交易类型
	private String bank_type; //付款银行
	private Integer total_fee; //总金额
	private String fee_type; //货币种类
	private Integer cash_fee; //现金支付金额
	private String cash_fee_type; //现金支付货币类型
	private String transaction_id; //微信支付订单号
	private String out_trade_no; //商户订单号
	private String attach; //商家数据包
	private String time_end; //支付完成时间
	private Timestamp create_time; //
	private String msg; //处理结果
	private String openid;
	private String is_subscribe;
	
	

	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	public Integer getId() {
		return id;
	}
	public void setId (Integer id) {
		this.id = id;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code (String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg (String return_msg) {
		this.return_msg = return_msg;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid (String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id (String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info (String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str (String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign (String sign) {
		this.sign = sign;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code (String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code (String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des (String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type (String trade_type) {
		this.trade_type = trade_type;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type (String bank_type) {
		this.bank_type = bank_type;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee (Integer total_fee) {
		this.total_fee = total_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type (String fee_type) {
		this.fee_type = fee_type;
	}
	public Integer getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee (Integer cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	public void setCash_fee_type (String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id (String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no (String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach (String attach) {
		this.attach = attach;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end (String time_end) {
		this.time_end = time_end;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time (Timestamp create_time) {
		this.create_time = create_time;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg (String msg) {
		this.msg = msg;
	}

    @Override
    public String toString() {
        return "Weixin_cb_data{" +
            "\nid=" + id +
            "\nreturn_code=" + return_code +
            "\nreturn_msg=" + return_msg +
            "\nappid=" + appid +
            "\nmch_id=" + mch_id +
            "\ndevice_info=" + device_info +
            "\nnonce_str=" + nonce_str +
            "\nsign=" + sign +
            "\nresult_code=" + result_code +
            "\nerr_code=" + err_code +
            "\nerr_code_des=" + err_code_des +
            "\ntrade_type=" + trade_type +
            "\nbank_type=" + bank_type +
            "\ntotal_fee=" + total_fee +
            "\nfee_type=" + fee_type +
            "\ncash_fee=" + cash_fee +
            "\ncash_fee_type=" + cash_fee_type +
            "\ntransaction_id=" + transaction_id +
            "\nout_trade_no=" + out_trade_no +
            "\nattach=" + attach +
            "\ntime_end=" + time_end +
            "\ncreate_time=" + create_time +
            "\nmsg=" + msg +
            "\nis_subscribe=" + is_subscribe +
            "\nopenid=" + openid +

            '}';
    }
}