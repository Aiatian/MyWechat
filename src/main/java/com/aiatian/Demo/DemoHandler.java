package com.aiatian.Demo;

import com.aiatian.ImagesReply.ObtainImages;
import com.aiatian.MsgAndImage.Tuwen;
import com.aiatian.SignIn.IntegralQuery;
import com.aiatian.SignIn.SignInperson;
import com.aiatian.TulingRobot.WechatProcess;
import com.aiatian.Util.MyBatisUtil;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.bean.WxXmlOutNewsMessage;
import com.soecode.wxtools.exception.WxErrorException;
import org.apache.ibatis.session.SqlSession;

import java.text.ParseException;
import java.util.Map;

public class DemoHandler implements WxMessageHandler {
    public WechatProcess wcp = new WechatProcess();
    public  SignInperson signInperson = new SignInperson();
    private SqlSession session = MyBatisUtil.getSqlSession();

    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, IService iService) throws WxErrorException {
        String type = wxMessage.getMsgType();
        String EventType = wxMessage.getEvent();

        if (type.equalsIgnoreCase("text")) {
            wxMessage.setContent(wxMessage.getContent().replace(" ",""));
            if (wxMessage.getContent().equals("你妈妈是谁")) {
                //必须以build()作为结尾，否则不生效。
                WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content("小绿叶的妈妈就是美丽的开发工程师妈妈").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
                return xmlOutMsg;

            } else if (wxMessage.getContent().equals("你爸爸是谁")) {
                WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content("小绿叶的爸爸是秋叶").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
                return xmlOutMsg;
            } else if (wxMessage.getContent().equals("签到")){


                try {
                  return   signInperson.signInDispose(wxMessage);
                } catch (ParseException e) {
                    e.printStackTrace();
                }finally {
                    session.commit();
                    session.close();

                }


            }else if (wxMessage.getContent().equals("图文")){
            /* Tuwen tuwen = new Tuwen();
                WxXmlOutMessage wxXmlOutMessage  = null;
                try {
                    wxXmlOutMessage = tuwen.tuWenTuiSong(wxMessage);

                } catch (ParseException e) {
                    e.printStackTrace();
                }*/
            // WxXmlOutMessage.TEXT().content(Text).toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
               //com.soecode.wxtools.bean.WxXmlOutNewsMessage.Item
                WxXmlOutNewsMessage.Item item= new WxXmlOutNewsMessage.Item();
                String Description="每天在人与妖怪之间过着忙碌日子的夏目，偶然与过去的同学・结城重逢了，以此想起了与妖怪有关的苦涩记忆。此时，夏目认识了在归还名字的妖怪记忆中出现的女性·津村容莉枝。知晓玲子的她，现在和独子椋雄一起平静地生活着。夏目通过和他们交流，心里也变得平静下来。但是这对母子所居住的城镇，似乎潜藏着神秘的妖怪。在调查此事回来的路上，寄生于猫咪老师身体上的“妖之种”，在藤原家的庭院中，一夜之间就长成树结出果实。不知为什么，吃掉了与自己形状相似果实的猫咪老师，竟然分裂成了3个？";
                String picUrl="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558030814217&di=a5b624862f32ddb2be1f37f866ba3f34&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F68c113f42c0f1caa45977a1e0665d846a5be429c.jpg";
                String Url="https://baike.baidu.com/";
                item.setTitle("图文标题");//夏目友人帐:结缘空蝉
                item.setDescription("11111111112222222");
                item.setPicUrl("http://pic.sc.chinaz.com/files/pic/pic9/201905/bpic11698.jpg");
                item.setUrl(Url);

                WxXmlOutMessage xmlOutMsg = WxXmlOutNewsMessage.NEWS().addArticle(item).toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
                return xmlOutMsg;
            } else if (wxMessage.getContent().equals("积分查询")){

                IntegralQuery integralQuery = new IntegralQuery();

                WxXmlOutMessage wxXmlOutMessage = integralQuery.CheckIntegral(wxMessage);

                return wxXmlOutMessage;

            }else {

                String quesiton = (String) wxMessage.getContent();


                System.out.println(wcp.Chat("" + quesiton));

                String Text = wcp.Chat("" + quesiton);
                WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content(Text).toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();


                return xmlOutMsg;

            }
        } else if (type.equalsIgnoreCase("event")) {
            if (EventType.equalsIgnoreCase("subscribe")) {
                System.out.println(wxMessage.toString());
                WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content("欢迎你关注该公众号,本公众号有可爱的机器人妹妹代管。快快和她聊天吧！(≖ᴗ≖)✧").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
                return xmlOutMsg;
            } else if (EventType.equalsIgnoreCase("unsubscribe")) {
                WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content("很抱歉,因为我们的不足让你没有良好的体验！我们会加尽努力的 期待你的下一次关注！").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
                return xmlOutMsg;
            }

        }else if (type.equalsIgnoreCase("image")) {
            ObtainImages obtainImages = new ObtainImages();
            WxXmlOutMessage tupian = obtainImages.WriteBackImages(wxMessage);
            return tupian;
        }else if (type.equalsIgnoreCase("file")) {
            WxXmlOutNewsMessage.Item item= new WxXmlOutNewsMessage.Item();
            String Description="每天在人与妖怪之间过着忙碌日子的夏目，偶然与过去的同学・结城重逢了，以此想起了与妖怪有关的苦涩记忆。此时，夏目认识了在归还名字的妖怪记忆中出现的女性·津村容莉枝。知晓玲子的她，现在和独子椋雄一起平静地生活着。夏目通过和他们交流，心里也变得平静下来。但是这对母子所居住的城镇，似乎潜藏着神秘的妖怪。在调查此事回来的路上，寄生于猫咪老师身体上的“妖之种”，在藤原家的庭院中，一夜之间就长成树结出果实。不知为什么，吃掉了与自己形状相似果实的猫咪老师，竟然分裂成了3个？";
            String picUrl="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558030814217&di=a5b624862f32ddb2be1f37f866ba3f34&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2F68c113f42c0f1caa45977a1e0665d846a5be429c.jpg";
            String Url="https://baike.baidu.com/";
            item.setTitle("图文标题");//夏目友人帐:结缘空蝉
            item.setDescription("11111111112222222");
            item.setPicUrl("http://pic.sc.chinaz.com/files/pic/pic9/201905/bpic11698.jpg");
            item.setUrl(Url);

            WxXmlOutNewsMessage.Item item2= new WxXmlOutNewsMessage.Item();
            item2.setTitle("图文标题");//夏目友人帐:结缘空蝉
            item2.setDescription("11111111112222222");
            item2.setPicUrl("http://pic.sc.chinaz.com/files/pic/pic9/201905/bpic11698.jpg");
            item2.setUrl(Url);

            WxXmlOutNewsMessage.Item item3= new WxXmlOutNewsMessage.Item();
            item3.setTitle("图文标题");//夏目友人帐:结缘空蝉
            item3.setDescription("11111111112222222");
            item3.setPicUrl("http://pic.sc.chinaz.com/files/pic/pic9/201905/bpic11698.jpg");
            item3.setUrl(Url);

            WxXmlOutMessage xmlOutMsg = WxXmlOutNewsMessage.NEWS().addArticle(item).addArticle(item2).addArticle(item3).toUser("omSx_0d5yAoD0IH9BHTo_181haco").fromUser("gh_94df2d24dc7e").build();
            return xmlOutMsg;
        }
        WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content("很抱歉,系统出现了故障！我们会加快修护的。(〒︿〒)").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();

        return xmlOutMsg;
    }
}

