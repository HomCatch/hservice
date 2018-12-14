//package com.xiaohe.ysjspt.controller;
//
//
//
//
//import com.xiaohe.ysjspt.config.WebAppConfig;
//import com.xiaohe.ysjspt.entity.CommonResult;
//import com.xiaohe.ysjspt.entity.Result;
//import com.xiaohe.ysjspt.entity.SysUser;
//import com.xiaohe.ysjspt.log.ControllerLog;
//import com.xiaohe.ysjspt.service.SysUserService;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import net.sf.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpSession;
//import java.io.UnsupportedEncodingException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Date;
///**
// * @author
// * Administrator
// */
//@RestController
//@RequestMapping("/mng")
//public class LoginController {
//
//    @Autowired
//    SysUserService sysUserService;
//
//    @RequestMapping(value = "/login")
//    @ControllerLog(description = "login")
//    public Result login(@RequestParam(value="username",required = true) String userName,
//                        @RequestParam(value="password",required = true) String password,
//                        @RequestParam(value="brandname",required = true) String brandName,
//                        HttpSession session){
//
//       SysUser user = sysUserService.loadUserByUsername(userName, password,brandName);
//
//        if (user != null){
//            session.setAttribute(WebAppConfig.SESSION_KEY, userName);
//
//            CommonResult result = new CommonResult();
//            result.setRet(0);
//            result.setMsg("success");
//
//            String token = Jwts.builder()
//                    .setSubject(user.getName())
//                    .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
//                    .signWith(SignatureAlgorithm.HS512, "MyJwtSecret")
//                    .compact();
//
//            sysUserService.saveToken(userName, token);
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("user_id", user.getId());
//            jsonObject.put("token", token);
//            jsonObject.put("parent_id",user.getParentId());
//            jsonObject.put("sub_id",user.getSubId());
//            result.setDatas(jsonObject);
//
//            return  result;
//
//        }else {
//            Result result = new Result();
//            result.error(6001, "用户名，密码不匹配");
//            return  result;
//        }
//    }
//
//
//    @RequestMapping(value = "/logout")
//    @ControllerLog(description = "logout")
//    public Result logout(HttpSession session){
//
//        session.removeAttribute(WebAppConfig.SESSION_KEY);
//
//        Result result = new Result();
//        result.ok();
//
//        return  result;
//    }
//
//    @RequestMapping(value = "/set_pwd")
//    @ControllerLog(description = "set_pwd")
//    public Result setPwd(@RequestParam(value="old_password",required = true) String oldPassword ,
//                         @RequestParam(value="new_password",required = true) String newPassword,
//                         @RequestParam(value="brandname",required = true) String brandname,
//                         @RequestParam(value="username",required = true) String userName) throws UnsupportedEncodingException, NoSuchAlgorithmException {
//
//        /*String userName = (String)session.getAttribute(WebAppConfig.SESSION_KEY);*/
//
//        System.out.println(userName);
//        System.out.println(oldPassword);
//        /*String  old_password= Md5Utils.EncoderByMd5(oldPassword);*/
//        SysUser user = sysUserService.loadUserByUsername(userName, oldPassword,brandname);
//
//        if (user != null){
//            Result result = new Result();
//            /*String new_password = Md5Utils.EncoderByMd5(newPassword);*/
//            sysUserService.updatePwd(userName, newPassword);
//            result.ok();
//
//            return  result;
//        }else {
//            Result result = new Result();
//            result.error(6003, "原有密码不正确");
//            return  result;
//        }
//    }
//}
