package com.demo.web;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.model.User;
import com.demo.model.UserRepository;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import redis.clients.jedis.Jedis;

@Controller
public class LoginControler {

	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private UserRepository userRep;
	
	@RequestMapping(value="/login")
	public String login(User temp,HttpServletRequest req,HttpSession session){
		logger.info("reqsessionid"+req.getRequestedSessionId());
		logger.info("sessionid"+session.getId());
		User user = null;
		Jedis jedis = new Jedis("192.168.5.128",6379);
		Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
		serializer.setObjectMapper(new ObjectMapper().setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY).enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL));
		String sessionId = req.getRequestedSessionId()!=null?req.getRequestedSessionId():session.getId();
		logger.info("sessionID="+sessionId);
		String redisUser = jedis.hget("loginUsers",sessionId);
		if(redisUser != null){
			user = (User) serializer.deserialize(redisUser.getBytes());
			logger.info("redis查询到"+user);
			if(!req.getRequestedSessionId().equals(req.getSession().getId())){
				jedis.hdel("loginUsers", req.getRequestedSessionId());
			}
		}else{
			user = userRep.findByUserCodeAndPassWord(temp.getUserCode(),temp.getPassWord());
			if(user == null){
				logger.info("用户名密码错误");
				return "login";
			}
			logger.info("redis未查询到"+user);
			
		}
		jedis.hset("loginUsers".getBytes(), req.getSession().getId().getBytes(), serializer.serialize(user));
		jedis.close();
		session.setAttribute("user", user);
		return "redirect:welcome";
	}
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest req,HttpSession session){
		Jedis jedis = new Jedis("192.168.5.128",6379);
		jedis.hdel("loginUsers", req.getRequestedSessionId());
		jedis.hdel("loginUsers", req.getSession().getId());
		session.invalidate();
		return "redirect:login";
	}
	
	@RequestMapping(value="/welcome")
	public String welcome(HttpServletRequest req,HttpSession session){
		return "redisTest";
	}

	@PostConstruct
	public void init(){
		System.out.println(getClass()+"PostConstruct");
	}
	@PreDestroy
	public void destory(){
		System.out.println(getClass()+"@PreDestroy");
	}
	
}
