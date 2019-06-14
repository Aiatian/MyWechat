package com.aiatian.SignIn;

import com.aiatian.Po.Client;
import com.aiatian.Util.MyBatisUtil;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import org.apache.ibatis.session.SqlSession;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class SignInperson {

    private Client clientele = new Client();
    private SqlSession session = MyBatisUtil.getSqlSession();
    private HashMap<String, Object> paramMap = new HashMap<String, Object>();
    private  int jifen = 10;
    private Calendar cal = Calendar.getInstance();
    private  IntegralQuery integralQuery = new IntegralQuery();


    public  WxXmlOutMessage signInDispose(WxXmlMessage wxMessage) throws ParseException {

            //创建一个实例
            SignInperson signInperson = new SignInperson();

            //设置日期规范
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            //添加日期
            clientele.toDay = sdf.parse(sdf.format(new Date()));

            //查看该用户是否第一次签到
            // 0代表这用户未签到过  1代表签到过  2代表查询出现了异常
            int flag =  signInperson.queryOneUser(wxMessage.getFromUserName());

            if (flag==0){
                paramMap.put("fromUserName",wxMessage.getFromUserName());
                paramMap.put("numberOfDays",1);
                paramMap.put("toDay",clientele.getToDay());
                paramMap.put("integral",100);
                paramMap.put("nowTheIntegral",100);
                int client = session.insert("client.addfromUserName",paramMap);

                session.commit();
                session.close();

                if (client > 0){
                    WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content("签到成功！首次签到  100 积分").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
                    return xmlOutMsg;
                }else{
                    WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content("很抱歉！签到失败。").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
                    return xmlOutMsg;

                }



            }else if (flag==1){

                 Client client =  signInperson.qeryUser(wxMessage.getFromUserName());

                //查询用户签到过的天数 以往签到记录
                clientele.setNumberOfDays(client.getNumberOfDays());

                //将天数 +1
                paramMap.put("numberOfDays",clientele.getNumberOfDays()+1);

                // 赋予今天的 日期 防止重复签到
                paramMap.put("toDay",clientele.getToDay());





                //把今天的日期放进去
                cal.setTime(client.toDay);

                int riqi = 0;

                //判断今天 是不是周末
                if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {

                    client.setIntegral(client.getIntegral()+20);

                    riqi = 2;
                }else{
                    client.setIntegral(client.getIntegral()+10);
                    riqi = 1;
                }

                // 每日签到 +10积分 周末双倍
                paramMap.put("integral",client.getIntegral());

                paramMap.put("fromUserName",client.getFromUserName());

                int client1 = 0;


                String strDate =  sdf.format(client.getToDay());

                String strDate2 =  sdf.format(new Date());

                if (strDate.equals(strDate2)){
                    WxXmlOutMessage   xmlOutMsg = WxXmlOutMessage.TEXT().content("真棒！今天已经签到过了 明天在来吧。").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();

                    return xmlOutMsg;

                }else{
                    client1 = session.update("client.updatefromUserName",paramMap);
                    session.commit();
                    if (client1>=1){
                        WxXmlOutMessage   xmlOutMsg  = WxXmlOutMessage.TEXT().content((riqi==2)?"签到成功今天周末 双倍积分！":"签到成功!  积分 +10 ").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();

                        integralQuery.Updatejifen(wxMessage);

                        return xmlOutMsg;

                    }else{
                        WxXmlOutMessage   xmlOutMsg = WxXmlOutMessage.TEXT().content("很抱歉！签到失败。").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
                        return xmlOutMsg;

                    }

                }

            }else{
                WxXmlOutMessage   xmlOutMsg = WxXmlOutMessage.TEXT().content("什么情况 什么情况？ 出故障了？").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
                return xmlOutMsg;
            }

    }

    public static void main(String[] args) {

        Date date = new Date();

        //设置日期规范
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        //添加日期
        try {
            date = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        SignInperson signInperson = new SignInperson();
        int flag =   signInperson.updedate("omSx_0d5yAoD0IH9BHTo_181haco",10,date,100);

        if (flag >0){

            System.out.println(flag);
        }else{
            System.out.println("0");
        }



    }

    /**
     *  查询用户是否存在
     *      0 代表不存在
     *      1 代表存在
     *      2 代表查找异常
     * @param msgName
     * @return
     */
    public  int queryOneUser(String msgName){

        String fromUserName  = msgName;
        paramMap.put("fromUserName", fromUserName);
        Client client = session.selectOne("client.querynumberOfDays",paramMap);



        if (client==null) {

            return 0;
        }else if (client!=null){
            return 1;
        }else{
            return 2;
        }

    }


    /**
     *  返回 0 贼查询失败
     *  返回 num >0 代表已签到的天数
     * @param msgName
     * @return
     */
    public  int queryDays(String msgName){

        String fromUserName  = msgName;

        paramMap.put("fromUserName", fromUserName);

        Client client = session.selectOne("client.queryfromUserName",paramMap);

        if (client.getNumberOfDays()== null){
                return 0;
        }else{
            return client.getNumberOfDays();
        }

    }



    public  Client qeryUser(String msgName){

        String fromUserName  = msgName;

        paramMap.put("fromUserName", fromUserName);

        Client client = session.selectOne("client.queryfromUserName",paramMap);



        return  client;
    }


    public int updedate(String a,Integer b ,Date c , Integer d){

        paramMap.put("fromUserName",a);
        paramMap.put("numberOfDays",b);
        paramMap.put("toDay",c);
        paramMap.put("integral",d);
        int flag =  session.update("client.updatefromUserName",paramMap);

        session.commit();

        return flag;
    }


    public SignInperson() {

    }


}
