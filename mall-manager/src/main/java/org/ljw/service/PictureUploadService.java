package org.ljw.service;

import org.ljw.utils.PictureResult;
import org.springframework.web.multipart.MultipartFile;

public interface PictureUploadService {

	
		public PictureResult pictureUpload(MultipartFile uploadFile);
	}

	

