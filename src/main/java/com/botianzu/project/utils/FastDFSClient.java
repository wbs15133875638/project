package com.botianzu.project.utils;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.exception.FdfsUnsupportStorePathException;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

@Component
public class FastDFSClient {

	@Autowired
	private FastFileStorageClient storageClient;

	@Autowired
	private Environment env;

	/**
	 * 上传文件
	 * 
	 * @param file
	 *            文件对象
	 * @return 文件访问地址
	 * @throws IOException
	 */
	public String uploadFile(MultipartFile file) throws IOException {
		StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
				FilenameUtils.getExtension(file.getOriginalFilename()), null);

		return storePath.getGroup() + "/" + storePath.getPath();
	}

	public String uploadFile(File file) throws IOException {
		StorePath storePath = storageClient.uploadFile(new FileInputStream(file), FileUtils.sizeOf(file),
				FilenameUtils.getExtension(file.getName()), null);

		return storePath.getGroup() + "/" + storePath.getPath();
	}

	/**
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String uploadFile2(MultipartFile file) throws IOException {
		StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
				FilenameUtils.getExtension(file.getOriginalFilename()), null);

		return storePath.getGroup() + "/" + storePath.getPath();
	}
	
	public String uploadQRCode(MultipartFile file) throws IOException {
		StorePath storePath = storageClient.uploadFile(file.getInputStream(), file.getSize(),
				"png", null);
		
		return storePath.getGroup() + "/" + storePath.getPath();
	}

	/**
	 * 缩略图图片,不带  http://192.168.49.128:8410  ...
	 * StorePath [group=group1, path=M00/00/00/wKgxgF3JHx6AE7P3AADl9D1Ew-E897.png]
	 * @param file
	 * @return 例:group1/M00/00/00/wKgxgF3JLeuAWeyeAADl9D1Ew-E373.png
	 * @throws IOException
	 */
	public String uploadFace(MultipartFile file) throws IOException {
		StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
				"png", null);
		
		return storePath.getGroup() + "/" + storePath.getPath();
	}

	/**
	 * 上传图片
	 * @param file 图片 MultipartFile类型
	 * @return
	 * @throws IOException
	 */
	public String uploadBase64(MultipartFile file) throws IOException {
		StorePath storePath = storageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(),
				"png", null);
		
		return storePath.getGroup() + "/" + storePath.getPath();
	}
	
	/**
	 * 将一段字符串生成一个文件上传
	 * 
	 * @param content
	 *            文件内容
	 * @param fileExtension
	 * @return
	 */
	public String uploadFile(String content, String fileExtension) {
		byte[] buff = content.getBytes(Charset.forName("UTF-8"));
		ByteArrayInputStream stream = new ByteArrayInputStream(buff);
		StorePath storePath = storageClient.uploadFile(stream, buff.length, fileExtension, null);
		return storePath.getGroup() + "/" + storePath.getPath();
	}

	/**
	 * 删除文件
	 * 
	 * @param fileUrl
	 *            文件访问地址
	 * @return
	 */
	public void deleteFile(String fileUrl) {
		if (StringUtils.isEmpty(fileUrl)) {
			return;
		}
		try {
			StorePath storePath = StorePath.praseFromUrl(fileUrl);
			storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
		} catch (FdfsUnsupportStorePathException e) {
			e.getMessage();
		}
	}
}
