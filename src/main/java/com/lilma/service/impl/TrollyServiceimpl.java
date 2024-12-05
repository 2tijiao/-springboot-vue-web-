package com.lilma.service.impl;

import com.lilma.mapper.TrollyMapper;
import com.lilma.pojo.Result;
import com.lilma.pojo.Trolly;
import com.lilma.service.TrollyService;
import com.lilma.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class TrollyServiceimpl implements TrollyService {

    @Autowired
    private TrollyMapper trollyMapper;

    //动查找一个JavaMailSenderImpl类型的bean，
    // 并将其注入到这个字段中。
    // JavaMailSenderImpl是一个实现了JavaMailSender接口的类，用于发送电子邮件。
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    //将配置文件中的spring.mail.username属性值注入到这个字段。
    @Value("${spring.mail.username}")
    private String sendMailer;

    @Override
    //通过线程获取登录用户的id，并调用mapper层方法，获取该用户的购物车信息
    public List<Trolly> list() {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer id=(Integer) map.get("id");
        return trollyMapper.list(id);

    }

    @Override
    //通过线程获取用户id，通过购买商品id，调用mapper层方法，依次获得相关商品信息。
    // 再调用addtoorder方法加入订单表，调用finish方法将购物车商品从购物车删除
    public void finish(List<Integer> lists) {
        Map<String,Object>claim=ThreadLocalUtil.get();
        Integer userid=(Integer)claim.get("id");
        String username=(String)claim.get("name");
        for (Integer list : lists) {
            Integer goodsid=trollyMapper.findGoodsid(list);
            String goodsname=trollyMapper.findGoodsname(list);
            BigDecimal goodsprice=trollyMapper.findGoodsprice(list);
            Integer goodsnum=trollyMapper.findGoodsnum(list);
            trollyMapper.addtoorder(goodsid,userid,username,goodsname,goodsprice,goodsnum);
            trollyMapper.finish(list);
        }

    }

    //通过线程获取用户id，通过id获取用户邮箱
    //再将邮箱信息代入下面的邮件设置
    @Override
    public void semail() {
        Map<String,Object> claim= ThreadLocalUtil.get();
        Integer userid=(Integer)claim.get("id");
        String email=trollyMapper.getemail(userid);
        String title="欢迎使用乐购";
        String text="您已经成功完成购物";
        try {
            //创建一个MimeMessageHelper对象，
            // 这个对象帮助构建复杂的邮件内容（如附件、HTML内容等）。
            // true参数表示支持复杂类型。
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(),true);
            //邮件发信人
            mimeMessageHelper.setFrom(sendMailer);
            //邮件收信人
            mimeMessageHelper.setTo(email);
            //邮件主题
            mimeMessageHelper.setSubject(title);
            //邮件内容
            mimeMessageHelper.setText(text);
            //邮件发送时间
            mimeMessageHelper.setSentDate(new Date());
            //发送邮件
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            //如果邮件发送成功，打印成功信息。
            System.out.println("发送邮件成功：" +sendMailer+"===>"+email);

        } catch (MessagingException e) {
            //如果发生异常，打印异常堆栈跟踪。
            //如果发生异常，打印失败信息和异常消息。
            e.printStackTrace();
            System.out.println("发送邮件失败："+e.getMessage());
        }

    }

    @Override
    //删除购物车指定id的购物车商品
    public void delete(Integer id) {
        trollyMapper.delete(id);
    }

    @Override
    //更新指定id购物车商品的购买数量
    public void update(Integer id, Integer goodsnum) {
        trollyMapper.update(id,goodsnum);
    }

    @Override
    //搜索指定商品名的购物车商品
    public List<Trolly> search(String goodsname) {
        return trollyMapper.search(goodsname);
    }

}
