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
            if (wxMessage.getContent().equals("签到")){


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
                item.setTitle("夏目友人帐:结缘空蝉");//夏目友人帐:结缘空蝉
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
            if (wxMessage.getEventKey().equalsIgnoreCase("dongmandianying")){
                String zhaohuaxishiText="经为流逝的日月。纬为人们的生业。住在远离尘嚣的土地，一边将每天的事情编织成名为希比奥尔的布，一边静静生活的伊奥鲁夫人民。在15岁左右外表就停止成长，拥有数百年寿命的他们，被称为“离别一族”，并被视为活着的传说。没有双亲的伊奥鲁夫少女玛奇亚，过着被伙伴包围的平稳日子，却总感觉“孤身一人”。他们的这种日常，一瞬间就崩溃消失。追求伊奥鲁夫的长寿之血，梅萨蒂军乘坐着名为雷纳特的古代兽发动了进攻。在绝望与混乱之中，伊奥鲁夫的第一美女蕾莉亚被梅萨蒂带走，而玛奇亚暗恋的少年克里姆也失踪了。玛奇亚虽然总算逃脱了，却失去了伙伴和归去之地……玛奇亚怀着空虚的心灵在黑暗的森林中徘徊。如同被召唤一般，她在那里遇到了刚刚失去双亲的“孤身一人”的婴儿。逐渐成长为少年的艾瑞尔。即使时间流逝也仍然保持少女样貌的玛奇亚。在同样的季节中，经过着不同的时间。在变化的时代当中，两人的牵绊逐渐变化着色调——。两名孤身一人之人的相遇，编织出无可替代的时间的故事。";
                String xiamuText="每天在人与妖怪之间过着忙碌日子的夏目，偶然与过去的同学・结城重逢了，以此想起了与妖怪有关的苦涩记忆。此时，夏目认识了在归还名字的妖怪记忆中出现的女性·津村容莉枝。知晓玲子的她，现在和独子椋雄一起平静地生活着。夏目通过和他们交流，心里也变得平静下来。但是这对母子所居住的城镇，似乎潜藏着神秘的妖怪。在调查此事回来的路上，寄生于猫咪老师身体上的“妖之种”，在藤原家的庭院中，一夜之间就长成树结出果实。不知为什么，吃掉了与自己形状相似果实的猫咪老师，竟然分裂成了3个？";
                String qianyuqianxunText="10岁的少女千寻与父母一起从都市搬家到了乡下。没想到在搬家的途中，一家人发生了意外。他们进入了汤屋老板魔女控制的奇特世界——在那里不劳动的人将会被变成动物。千寻的爸爸妈妈因贪吃变成了猪，千寻为了救爸爸妈妈经历了很多磨难，在期间她遇见了白龙，一个既聪明又冷酷的少年，在经历了很多事情之后，千寻最后救出了爸爸妈妈，拯救了白龙";
                String tianqizhiziText="“很想试试在那光芒之中前行！”\n" +
                        "高一的夏天，少年帆高离家出走，一个人来到东京。帆高好不容易找到一份工，为一本古怪的“神秘学杂志”写稿，生活孤单贫苦。连日的滂沱大雨，像是来映衬他的失落。在纷纭杂沓的大都会一角，帆高遇上了一个可爱少女阳菜。阳菜和弟弟相依为命，个性坚强、开朗，但她心中隐藏着一个重大秘密──每当她说：“现在开始天晴了！”雨便渐渐停下来，美丽的阳光洒落街上。原来，她拥有一股不可思议的能量，一股能让天空放晴的异力……";

                String zhaohuaxishiMinUrl="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561203690595&di=089fdb2e88688ea3802009f90416686b&imgtype=0&src=http%3A%2F%2Fimages.china.cn%2Fsite1007%2F2019-02%2F18%2Fe18d4d63-6310-4efe-8fb7-71e5e9335e27.jpg";
                String xiamuMinUrl="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561203615897&di=9be1818822c4a12edfcb21b5d3bf2edb&imgtype=0&src=http%3A%2F%2Fi0.hdslb.com%2Fbfs%2Farticle%2Fee175b7311406da8731601b332fc48688bceb995.jpg";
                String qianyuqianxunMinUrl="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1561203808787&di=1292ae151ad4feba9e19fe59c05c3cfd&imgtype=0&src=http%3A%2F%2Fpics0.baidu.com%2Ffeed%2F0df3d7ca7bcb0a46da73f1dda37ef2216a60afa7.jpeg%3Ftoken%3D7b5dd9a23aeb2c495a504fb5110cd087%26s%3D18B37F950813EED61A0C557D0300303A";
                String tianqizhiziMinUrl="http://pic.topys.cn/editor/20181215/1677886052.jpg?x-oss-process=style/article-content-pic-1x";



                String zhaohuaxishiUrl="https://baike.baidu.com/item/%E6%9C%9D%E8%8A%B1%E5%A4%95%E8%AA%93%E2%80%94%E2%80%94%E4%BA%8E%E7%A6%BB%E5%88%AB%E4%B9%8B%E6%9C%9D%E6%9D%9F%E8%B5%B7%E7%BA%A6%E5%AE%9A%E4%B9%8B%E8%8A%B1?fromtitle=%E6%9C%9D%E8%8A%B1%E5%A4%95%E8%AA%93&fromid=23292439";
                String xiamuUrl="https://baike.baidu.com/item/%E5%A4%8F%E7%9B%AE%E5%8F%8B%E4%BA%BA%E5%B8%90%20%EF%BD%9E%E7%BC%98%E7%BB%93%E7%A9%BA%E8%9D%89%EF%BD%9E";
                String qianyuqianxunUrl="https://baike.baidu.com/item/%E5%8D%83%E4%B8%8E%E5%8D%83%E5%AF%BB";
                String tianqizhiziUrl="https://baike.baidu.com/item/%E5%A4%A9%E6%B0%94%E4%B9%8B%E5%AD%90/23203347?fr=aladdin";

                WxXmlOutNewsMessage.Item item1= new WxXmlOutNewsMessage.Item();
                item1.setTitle("朝花夕誓——于离别之朝束起约定之花");
                item1.setDescription(zhaohuaxishiText);
                item1.setPicUrl(zhaohuaxishiMinUrl);
                item1.setUrl(zhaohuaxishiUrl);


                WxXmlOutNewsMessage.Item item2= new WxXmlOutNewsMessage.Item();
                item2.setTitle("夏目友人帐 ～缘结空蝉～");
                item2.setDescription(xiamuText);
                item2.setPicUrl(xiamuMinUrl);
                item2.setUrl(xiamuUrl);


                WxXmlOutNewsMessage.Item item3= new WxXmlOutNewsMessage.Item();
                item3.setTitle("千与千寻");
                item3.setDescription(qianyuqianxunText);
                item3.setPicUrl(qianyuqianxunMinUrl);
                item3.setUrl(qianyuqianxunUrl);

                WxXmlOutNewsMessage.Item item4= new WxXmlOutNewsMessage.Item();
                item4.setTitle("天气之子");
                item4.setDescription(tianqizhiziText);
                item4.setPicUrl(tianqizhiziMinUrl);
                item4.setUrl(tianqizhiziUrl);


                WxXmlOutMessage xmlOutMsg = WxXmlOutNewsMessage.NEWS().addArticle(item1).addArticle(item2).addArticle(item3).addArticle(item4).toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
                return xmlOutMsg;
            }else if (EventType.equalsIgnoreCase("subscribe")) {
                String zhaohuaxishiUrl="";
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
        }
        WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content("很抱歉,系统出现了故障！我们会加快修护的。(〒︿〒)").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();

        return xmlOutMsg;
    }
}

