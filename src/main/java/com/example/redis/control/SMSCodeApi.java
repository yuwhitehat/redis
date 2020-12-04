package com.example.redis.control;

import com.example.redis.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/email/code")
public class SMSCodeApi {

    private static final Logger LOG = LoggerFactory.getLogger(SMSCodeApi.class);
    private static String MAIL_EMPTY= "100001";
    private static String MAIL_CODE_EMPTY = "100002";
    private static String MAIL_ERR = "100004";
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private JavaMailSender mailSender;

    @Value("${sendEmailAddress}")
    private String sendEmailAddress;

    /**
     * 邮箱验证码
     * @param email
     * @return
     */
    @RequestMapping(path = "/send")
    @ResponseBody
    public Result<SimpleMailMessage> sendMail(@RequestParam("email") String email) {

        Result<SimpleMailMessage> result = new Result();
        if (StringUtils.isEmpty(email)) {
            result.setCode(MAIL_EMPTY);
            result.setMessage("邮箱不能为空");
            result.setSuccess(false);
            return result;
        }
        SimpleMailMessage message = new SimpleMailMessage();
        if (StringUtils.isEmpty(sendEmailAddress)) {
            result.setCode(MAIL_EMPTY);
            result.setMessage("发件人不能为空");
            result.setSuccess(false);
            return result;
        }
        String code = String.valueOf((int)((Math.random()*9 + 1) * 100000));
        message.setFrom(sendEmailAddress);
        message.setTo(email);
        message.setSubject("发送验证码");
        message.setText(code);
        mailSender.send(message);
        result.setSuccess(true);
        result.setData(message);
        stringRedisTemplate.opsForValue().set(email,code);
        return result;
    }
    @GetMapping("/verificate")
    @ResponseBody
    public Result verificateCode(@RequestParam("email") String email, @RequestParam("code") String code) {
        Result result = new Result();
        result.setSuccess(true);
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(code)) {
            result.setSuccess(false);
            result.setMessage("邮箱和验证码不能为空");
            result.setCode(MAIL_EMPTY);
            return result;
        }
        String existCode = stringRedisTemplate.opsForValue().get(email);

        if (StringUtils.isEmpty(existCode)) {
            result.setSuccess(false);
            result.setMessage("从Redis里未取出对应验证码");
            result.setCode(MAIL_CODE_EMPTY);
            return result;
        }
        if (!existCode.equals(code)) {
            result.setSuccess(false);
            result.setMessage("邮箱验证码校验失败！");
            result.setCode(MAIL_ERR);
            return result;
        }
        return result;
    }

    /**
     * 给手机发送验证码
     * @param mobile
     * @return
     */
    @RequestMapping("/sendCode")
    @ResponseBody
    public Result send(@RequestParam("mobile") String mobile) {
        Result result = new Result<>();
        result.setSuccess(true);
        //第一步先判断mobile是否为空
        if (StringUtils.isEmpty(mobile)) {
            result.setSuccess(false);
            result.setMessage("手机号信息不能为空");
            return result;
        }
        //生成4位验证码
        String code = String.valueOf((int)((Math.random()*9+1)*1000));
        //第二步 调用第三方发送服务
        Boolean sendResult = sendMobileCode(mobile, code);
        if(!sendResult) {
            result.setSuccess(false);
            result.setMessage("发送验证码失败！");
            return result;
        }
        //第三步 将手机号对应的验证码存储到redis中
        stringRedisTemplate.opsForValue().set(mobile, code);
        return result;
    }

    private Boolean sendMobileCode(String mobile, String code) {
        LOG.info("mobile is:" + mobile + "code is:" + code);
        return true;
    }
}
