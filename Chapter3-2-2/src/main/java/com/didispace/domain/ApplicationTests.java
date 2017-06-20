package com.didispace.domain;

import com.didispace.Application;
import com.didispace.domain.entity.TestProperties;
import com.didispace.domain.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Iterator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	TestProperties t;
	@Autowired
	JavaMailSender jms;
	@Value("${spring.mail.username}")
	private String username;


	// @Before

	public void before() {
		userRepository.save(new User("AAA", 10));
	}

	@Test
	public void test() throws Exception {
		Sort sort = new Sort(Sort.Direction.ASC, "id");
		Pageable pageable = new PageRequest(2, 2, sort);
		Page<User> u1 = userRepository.load(pageable);
		System.out.println(u1.getTotalElements());
		System.out.println(u1.getTotalPages());
		System.out.println(u1.getSize());
		System.out.println(u1.getNumber());
		Iterator<User> it = u1.iterator();
		for (; it.hasNext();) {
			System.out.println("第一次查询：" + it.next());
		}
		User u2 = userRepository.findByName("BBB");
		System.out.println("第二次查询：" + u2.getAge());
		userRepository.update("AAA");
		User u3 = userRepository.findByName("CCC");
		System.out.println("第三次查询：" + u3.getAge());
	}

	@Test
	public void test1() {
		System.out.println(t.getName());
		System.out.println(t.getName1());
		System.out.println(t.getL());
	}

	@Test
	public void sendSimpleEmail() {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(username);// 发送者.
		message.setTo("1065241654@qq.com");// 接收者.
		message.setSubject("测试邮件（邮件主题）");// 邮件主题.
		message.setText("这是邮件内容");// 邮件内容.

		jms.send(message);// 发送邮件
	}
	
	 @Test
	public void sendAttachmentsEmail() throws MessagingException{
	       //这个是javax.mail.internet.MimeMessage下的，不要搞错了。
	       MimeMessage mimeMessage =  jms.createMimeMessage();
	       MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	      
	       //基本设置.
	       helper.setFrom(username);//发送者.
	       helper.setTo("1065241654@qq.com");//接收者.
	       helper.setSubject("测试附件（邮件主题）");//邮件主题.
//	       helper.setText("这是邮件内容（有附件哦.）");//邮件内容.
	       helper.setText("<body>这是图片：<img src='cid:head' /></body>", true);
	      
	       //org.springframework.core.io.FileSystemResource下的:
	       //附件1,获取文件对象.
	       FileSystemResource file1 = new FileSystemResource(new File("C:/Users/david/Pictures/qq/IMG_20170215_183027.jpg"));
	       //添加附件，这里第一个参数是在邮件中显示的名称，也可以直接是head.jpg，但是一定要有文件后缀，不然就无法显示图片了。
	       helper.addAttachment("头像1.jpg", file1); 
	       //附件2
//	       FileSystemResource file2 = new FileSystemResource(new File("C:/Users/david/Pictures/qq/IMG_20170215_183029.jpg"));
//	       helper.addAttachment("头像2.jpg", file2);
	       helper.addInline("head", file1);
	       jms.send(mimeMessage);
	    }
}
