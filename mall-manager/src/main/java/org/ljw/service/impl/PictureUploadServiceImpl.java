package org.ljw.service.impl;

import java.io.IOException;
import java.io.InputStream;

import org.joda.time.DateTime;
import org.ljw.service.PictureUploadService;
import org.ljw.utils.ExceptionUtil;
import org.ljw.utils.FtpUtil;
import org.ljw.utils.IDUtils;
import org.ljw.utils.PictureResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PictureUploadServiceImpl implements PictureUploadService {
	String host = "49.234.217.11";
	int port = 21;
	String username = "lllty";
	String password = "westos123";

	@Override
	public PictureResult pictureUpload(MultipartFile uploadfile) {
		PictureResult result = new PictureResult();
		try {
			if (null == uploadfile || uploadfile.isEmpty()) {
				result.setError(1);
				result.setMessage("上传图片为空");
				return result;
			}

			String originalFilename = uploadfile.getOriginalFilename();
			String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
			DateTime dateTime = new DateTime();
			dateTime.toString();
			String filePath = dateTime.toString("yyyy/MM/dd");
			String filename = IDUtils.genImageName() + ext;
			InputStream input = uploadfile.getInputStream();
			FtpUtil.uploadFile(host, port, username, password, "/", filePath, filename, input);
			String url = "http://49.234.217.11:8080/lllty/" + filePath + "/" + filename;
			result.setError(0);
			result.setUrl(url);
			return result;
		} catch (IOException e) {
			result.setError(1);
			result.setMessage(ExceptionUtil.getStackTrace(e));
		}
		return result;
	}
}
