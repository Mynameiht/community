package com.newcoder.community.controller;

import com.newcoder.community.annotation.LoginRequired;
import com.newcoder.community.entity.User;
import com.newcoder.community.service.UserService;
import com.newcoder.community.util.CommunityUtil;
import com.newcoder.community.util.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Value("${community.path.upload}")
    private String uploadPath;
    @Value("${community.path}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private HostHolder hostHolder;
    @LoginRequired
    @RequestMapping(path = "/setting",method = RequestMethod.GET)
    public String getSetting(){

        return "/site/setting";
    }
    @LoginRequired
    @RequestMapping(path = "/upload",method = RequestMethod.POST)
    public String uploadHeader(MultipartFile headerImage, Model model){
        if(headerImage == null){
            model.addAttribute("error","您还没有选择图片");
            return "/site/setting";
        }
        String fileName = headerImage.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if(StringUtils.isBlank(suffix)){
            model.addAttribute("error","文件格式不正确");
            return "/site/setting";
        }
        //生成随机文件名
        fileName = CommunityUtil.generateUUID() + suffix;
        //确定文件存放路径
        File dest = new File(uploadPath);
        if (!dest.exists()){
            dest.mkdirs();
        }
        dest = new File(uploadPath + "/" +fileName);
        try {
            //存储文件
            headerImage.transferTo(dest);
        } catch (IOException e) {
            logger.error("上传文件失败" + e.getMessage());
            throw new RuntimeException("上传文件失败，服务器异常"+ e);
        }
        //更新当前用户头像路径
        User user = hostHolder.getUser();
        String headerUrl = domain + contextPath + "/user/header" + fileName;
        userService.updateHeader(user.getId(), headerUrl);
        return "redirect:/index";
    }

    @RequestMapping(path = "/header/{fileName}",method = RequestMethod.GET)
    public void getHeader(@PathVariable("fileName") String fileName , HttpServletResponse response){
       //服务器存放头像路径
        fileName = uploadPath + "/" + fileName;
        //获取图片类型
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        //响应图片
        response.setContentType(suffix);

            try ( FileInputStream fileInputStream = new FileInputStream(fileName) ){
                ServletOutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[1024];
                int b = 0;
                while ((b = fileInputStream.read(buffer)) != -1){
                    outputStream.write(buffer,0,b);
                }
            } catch (IOException e) {
                logger.error("读取头像失败"+e.getMessage());
                throw new RuntimeException(e);
            }

    }
    @LoginRequired
    @RequestMapping(path = "/updatePassword",method = RequestMethod.POST)
    public String updatePassword(String oldPassword,String newPassword,Model model){
        User user = hostHolder.getUser();
        if(StringUtils.isBlank(oldPassword)){
            model.addAttribute("passwordMsg","密码不能为空");
            return "/site/setting";
        }
        oldPassword = CommunityUtil.md5(oldPassword + user.getSalt());
        if(!user.getPassword().equals(oldPassword)){
            model.addAttribute("passwordMsg","原密码不对，请重新输入");
            return "/site/setting";
        }
        userService.updatePassword(user.getId(),newPassword);
        model.addAttribute("passwordMsg","密码修改成功");
        return "redirect:/login";
    }

}
