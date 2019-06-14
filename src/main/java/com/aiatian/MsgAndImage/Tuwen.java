package com.aiatian.MsgAndImage;


import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.bean.WxNewsInfo;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Tuwen {
    private IService iService;
    public WxXmlOutMessage tuWenTuiSong(WxXmlMessage wxMessage) throws ParseException {
       WxNewsInfo news2 = new WxNewsInfo();
        news2.setTitle("图文测试");
        news2.setThumb_media_id("0_fJTUQFQHIMWL3kB0LeVrcCQvXobqgUCUS8Imoh234Whja5RTVSOnOioQthbAiy");
        news2.setContent("这是我的第一个图文消息");

        List<WxNewsInfo> newsList = new ArrayList<>();

        newsList.add(news2);
        try {
            String mediaId = iService.addNewsMedia(newsList);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content("").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
        return xmlOutMsg;
    }

    public Tuwen() {

    }
}

