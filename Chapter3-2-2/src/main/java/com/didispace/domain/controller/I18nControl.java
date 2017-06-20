package com.didispace.domain.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.didispace.domain.p.Demo;
@Controller
public class I18nControl {
	
	@RequestMapping("/")
	public String index(){
		return "/login";
	}
	
	@RequestMapping("/changeLanauage")
	public String changeLanauage(HttpServletRequest request,HttpServletResponse response,String lang,@Valid Demo demo,BindingResult result,Model model){
	   LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
	   if("zh".equals(lang)){
		   localeResolver.setLocale(request, response, new Locale("zh", "CN"));
	   }else if("en".equals(lang)){
		   localeResolver.setLocale(request, response, new Locale("en", "US"));
	   }
	   return "/validate";
	   }
	
}
