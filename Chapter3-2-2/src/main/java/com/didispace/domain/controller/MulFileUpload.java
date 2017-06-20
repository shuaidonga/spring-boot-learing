package com.didispace.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MulFileUpload {
//	 @Value("${web.upload-path}")
//	    private String uploadPath;
	
	@RequestMapping("/mutifile")
	public String index() {
		return "/mutifile";
	}

	@RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
	public String handleFileUpload(HttpServletRequest request,@RequestParam("file") MultipartFile[] mfile) throws IOException {
		System.out.println(mfile.length);
		if (mfile != null) {
			for (MultipartFile multipartFile : mfile) {
				if (!multipartFile.isEmpty()) {
					byte[] bytes = multipartFile.getBytes();
					String filePath = request.getServletContext().getContextPath()+"src/main/resources/upload/";
					String filename = multipartFile.getOriginalFilename();
					FileCopyUtils.copy(bytes,
							new File(filePath +UUID.randomUUID()+ filename ));
				}
			}
		}
		return "/login";
		// List<MultipartFile> files = ((MultipartHttpServletRequest)
		// request).getFiles("file");
		// MultipartFile file = null;
		// BufferedOutputStream stream = null;
		// for (int i = 0; i < files.size(); ++i) {
		// file = files.get(i);
		// if (!file.isEmpty()) {
		// try {
		// byte[] bytes = file.getBytes();
		// stream = new BufferedOutputStream(new FileOutputStream(new
		// File(file.getOriginalFilename())));
		// stream.write(bytes);
		// stream.close();
		// } catch (Exception e) {
		// stream = null;
		// return "You failed to upload " + i + " => " + e.getMessage();
		// }
		// } else {
		// return "You failed to upload " + i + " because the file was empty.";
		// }
		// }
		// return "upload successful";
	}
}
