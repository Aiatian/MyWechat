package com.aiatian.Edit;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.exception.WxErrorException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CreateMenu {

    public static void main(String[] args) {
        IService iService = new WxService();
        WxMenu menu = new WxMenu();
        List<WxMenu.WxMenuButton> btnList = new ArrayList<>();
        //飙升功能
        WxMenu.WxMenuButton btn1 = new WxMenu.WxMenuButton();
        btn1.setName("热门动漫电影");
        btn1.setType(WxConsts.MENU_BUTTON_CLICK);
        btn1.setKey("dongmandianying");

        WxMenu.WxMenuButton btn2 = new WxMenu.WxMenuButton();
        btn2.setName("打开百度");
        btn2.setType(WxConsts.MENU_BUTTON_VIEW);
        btn2.setKey("baidu");
        btn2.setUrl("http://www.baidu.com");

        WxMenu.WxMenuButton btn3 = new WxMenu.WxMenuButton();
        btn3.setName("分类");


        List<WxMenu.WxMenuButton> subList = new ArrayList<>();
        WxMenu.WxMenuButton btn3_1 = new WxMenu.WxMenuButton();
        btn3_1.setType(WxConsts.MENU_BUTTON_CLICK);
        btn3_1.setKey("biaoshengbang");
        btn3_1.setName("没用的按钮");

        WxMenu.WxMenuButton btn3_2 = new WxMenu.WxMenuButton();
        btn3_2.setType(WxConsts.MENU_BUTTON_CLICK);
        btn3_2.setKey("huifu");
        btn3_2.setName("让我回复你好啊");

        WxMenu.WxMenuButton btn3_3 = new WxMenu.WxMenuButton();
        btn3_3.setType(WxConsts.MENU_PIC_PHOTO_OR_ALBUM);
        btn3_3.setKey("sendImage");
        btn3_3.setName("发送图片");

        subList.addAll(Arrays.asList(btn3_1, btn3_2,btn3_3));
        btn3.setSub_button(subList);
        //将三个按钮设置进btnList
        btnList.add(btn1);
        btnList.add(btn2);
        btnList.add(btn3);
        //设置进菜单类
        menu.setButton(btnList);
        //调用API即可
        try {
            //参数1--menu  ，参数2--是否是个性化定制。如果是个性化菜单栏，需要设置MenuRule
            iService.createMenu(menu, false);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
