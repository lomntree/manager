package org.ljw.controller;

import org.ljw.service.PictureUploadService;
import org.ljw.utils.PictureResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PictureUploadController {
   @Autowired
	private PictureUploadService pictureUploadService;
   
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PictureResult PictureUpload(MultipartFile uploadFile) {
		PictureResult pictureUpload = pictureUploadService.pictureUpload(uploadFile);
		return pictureUpload;

	}
}
