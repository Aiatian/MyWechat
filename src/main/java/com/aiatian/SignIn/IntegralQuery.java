package com.aiatian.SignIn;

import com.aiatian.Po.Client;
import com.aiatian.Util.MyBatisUtil;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;

public class IntegralQuery {


    private SqlSession session = MyBatisUtil.getSqlSession();

    private HashMap<String, Object> paramMap = new HashMap<String, Object>();


    public WxXmlOutMessage  CheckIntegral(WxXmlMessage wxMessage){

        Client client = yyjifen(wxMessage.getFromUserName());

        int yiyjifen = yiyjifen(wxMessage.getFromUserName());

        int  xianyiyjifen =  client.getIntegral()  - yiyjifen;

        int flag = addxianyoujifen(wxMessage.getFromUserName(), xianyiyjifen);

        if (flag>=0){
            WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content("查询成功！"+"\n"+"您已经签到："+client.getNumberOfDays()+"天\n"+"您的总积分为："+client.getIntegral()+"\n"+"已使用积分为："+yiyjifen+"\n"+"可用的积分为："+xianyiyjifen+" ").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
            return xmlOutMsg;
        }else {
            WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content("网络繁忙！查询失败了。。。").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
            return xmlOutMsg;
        }
    }



    public  Client  yyjifen(String user){
        paramMap.put("fromUserName",user);

        Client client =  session.selectOne("client.queryfromUserName",paramMap);

        return client;
    }


    public  int  yiyjifen(String user){
        paramMap.put("fromUserName",user);

        Client client =  session.selectOne("client.queryfromUserName",paramMap);

        int jifen =0;

        if (client==null){
            jifen =0;
        }else{
            jifen=client.getHaveUsedIntegral();
        }


        return jifen;
    }



    public  int  addxianyoujifen(String user,Integer jifen){
        paramMap.put("fromUserName",user);
        paramMap.put("nowTheIntegral",jifen);

        int client =  session.update("client.updatenowTheIntegral",paramMap);

        session.commit();
        return client;
    }


    public static void main(String[] args) {

    }



    public int  Updatejifen(WxXmlMessage wxMessage){

        Client client = yyjifen(wxMessage.getFromUserName());

        int yiyjifen = yiyjifen(wxMessage.getFromUserName());

        int  xianyiyjifen =  client.getIntegral()  - yiyjifen;

        int flag = addxianyoujifen(wxMessage.getFromUserName(), xianyiyjifen);

        if (flag>=0){
            return 1;
        }else {
            return 0;
        }
    }


    public IntegralQuery() {

    }

}
