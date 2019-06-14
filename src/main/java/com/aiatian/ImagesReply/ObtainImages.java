package com.aiatian.ImagesReply;


import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.bean.result.WxMediaUploadResult;
import com.soecode.wxtools.exception.WxErrorException;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ObtainImages {
    WxService wxService = new WxService();

    public static void  main(String[] args) throws IOException {
        ObtainImages obtainImages = new ObtainImages();
        obtainImages.getImagesNum();

    }

    public WxXmlOutMessage  WriteBackImages (WxXmlMessage wxMessage){
        ObtainImages obtainImages = new ObtainImages();
            int imagesNum = obtainImages.getImagesNum();
            int random = new Random().nextInt(imagesNum+1);

            //创建file对象
            File file = new File("E:\\IDEA  Workspace\\MyWechat\\src\\main\\resources\\images\\"+random+".jpg");

            WxMediaUploadResult result=null;
            try {
                result=wxService.uploadTempMedia (WxConsts.MEDIA_IMAGE, file);
            } catch (WxErrorException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            if(result!=null) {
                String mediaId = result.getMedia_id();
                WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.IMAGE().mediaId(mediaId).toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
                return xmlOutMsg;
            }else{
                WxXmlOutMessage xmlOutMsg = WxXmlOutMessage.TEXT().content("").toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
                return xmlOutMsg;
            }


    }

    public  int getImagesNum() {
        int fileCount = 0;
        File file = new File("E:\\IDEA  Workspace\\MyWechat\\src\\main\\resources\\images");
        System.out.println(file.getAbsolutePath());

        File list[] = file.listFiles();
        for(int i = 0; i < list.length; i++){
            if(list[i].isFile()){
                fileCount++;
            }
        }
        System.out.println("文件个数："+fileCount);
        
        return  fileCount;

    }

    public ObtainImages() {

    }
}
