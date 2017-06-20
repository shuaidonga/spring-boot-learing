package com.didispace.domain.controller;

import com.didispace.domain.p.Demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class ValidateContr {
	// @RequestMapping("/demo")
	// public String demo(Model model){
	// model.addAttribute("demo",new Demo());
	// return "/demo";
	// }

	@RequestMapping("/demoAdd")
	public String demoAdd(@Valid Demo demo, BindingResult result, Model model) {
		// 有错误信息.
		model.addAttribute("demo", demo);
		if (result.hasErrors()) {
			List<ObjectError> list = result.getAllErrors();
			for (ObjectError error : list) {
				System.out.println(error.getCode() + "---" + error.getArguments() + "---" + error.getDefaultMessage());
			}

		}
		return "/validate";
	}

	@RequestMapping("/getCaptcha")
	@ResponseBody
	public byte[] getCaptcha(HttpServletResponse response) throws IOException {
		byte[] b = FileCopyUtils.copyToByteArray(new File("C:/Users/david/Pictures/qq/1.jpg"));
		return b;
	}

}
