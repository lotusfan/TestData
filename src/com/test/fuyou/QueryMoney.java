package com.test.fuyou;

import com.fuiou.data.QueryBalanceReqData;
import com.fuiou.data.QueryBalanceRspData;
import com.fuiou.data.QueryTxnReqData;
import com.fuiou.data.TransferBmuAndFreezeReqData;
import com.fuiou.service.FuiouService;

/**
 * Created by zhangfan on 2015/9/24.
 */
public class QueryMoney {

    public static String mchnt_cd = "0002900F0096235";
//    public static String cust_no = "7710165541";
    public static String cust_no = "15001122356";
    public static String mchnt_txn_dt = "20150915";
    public static String mchnt_txn_ssn = "2432323234324324";



    public static void query() throws Exception {

        QueryBalanceReqData queryBalanceReqData = new QueryBalanceReqData();

        queryBalanceReqData.setMchnt_cd(mchnt_cd);
        queryBalanceReqData.setCust_no(cust_no);
        queryBalanceReqData.setMchnt_txn_ssn(mchnt_txn_ssn);
        queryBalanceReqData.setMchnt_txn_dt(mchnt_txn_dt);

        QueryBalanceRspData queryBalanceRspData = FuiouService.balanceAction(queryBalanceReqData);
        System.out.println(queryBalanceRspData);
    }

    public static void transfer() throws Exception {
        QueryTxnReqData queryTxnReqData = new QueryTxnReqData();
        queryTxnReqData.setMchnt_cd(mchnt_cd);
        queryTxnReqData.setMchnt_txn_ssn(mchnt_txn_ssn);
        queryTxnReqData.setBusi_tp("PW19");
        queryTxnReqData.setStart_day("20150815");
        queryTxnReqData.setEnd_day("20150924");
        queryTxnReqData.setCust_no(cust_no);

        FuiouService.queryTxn(queryTxnReqData);
    }


    public static void main(String[] args) throws Exception{
//        query();
        transfer();
    }
}
