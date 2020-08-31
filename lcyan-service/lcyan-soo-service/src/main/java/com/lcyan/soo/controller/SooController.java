package com.lcyan.soo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lcyan.soo.model.Miniofiles;
import com.lcyan.soo.utils.MinioUtil;

import io.minio.messages.Bucket;

@RestController
@RequestMapping("soo")
public class SooController {

	@Autowired
	private MinioUtil minioUtil;
	
	@GetMapping("exists")
	public Boolean bucketExists() {

		return minioUtil.bucketExists("images");
	}
	
	@GetMapping("list")
	public List<Miniofiles> listObject() {

		return minioUtil.listObjects("images");
	}
	
	@GetMapping("put")
	public String putFile() throws IOException {
		File f = new File("C:\\Users\\ayan2070\\Desktop\\springcloud微服务架构图.jpg");   
	    InputStream in = new FileInputStream(f); 
	    minioUtil.putObject("images", "aaa.jpg", in, "image/jpeg");
	    minioUtil.putObject("images", "bbb.jpg", in, "image/jpeg");
	    minioUtil.putObject("images", "ccc.jpg", in, "image/jpeg");
	    
	    minioUtil.makeBucket("text");
	    minioUtil.makeBucket("video");
	    
	    List<Bucket> listBucket = minioUtil.getListBucket();
	    listBucket.forEach(bucket ->{
	    	System.out.println("储存同名称：" + bucket.name());
	    });
	    
	    String objectUrl = minioUtil.getObjectUrl("images", "aaa.jpg");
	    System.out.println("请求URL：" + objectUrl);
	    
	    String objectUrl2 = minioUtil.getObjectUrl("images", "aaa.jpg", 100);
	    System.out.println("请求URL：" + objectUrl2);
	    
	    minioUtil.download("images", "aaa.jpg", "bbb.jpg");
	    
	    minioUtil.removeBucket("video");
	    
	    minioUtil.removeObject("images", "ccc.jpg");
	    
	    InputStream inputStream = minioUtil.getObject("images", "aaa.jpg");
	    byte[] bytes = new byte[0];
	    bytes = new byte[inputStream.available()];
	    inputStream.read(bytes);
	    String str = new String(bytes);
	    System.out.println(str);
	    
	    
	    
		return "SUCCESS";
	}
	
	@GetMapping("dow")
	public ResponseEntity<InputStreamResource> download(HttpServletResponse response) throws IOException {
		
		InputStream in = minioUtil.getObject("images", "aaa.jpg");
		HttpHeaders headers = new HttpHeaders();  
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");  
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", "aaa.jpg"));  
        headers.add("Pragma", "no-cache");  
        headers.add("Expires", "0");  
  
        return ResponseEntity  
                .ok()  
                .headers(headers)  
                //.contentLength(in.)  
                .contentType(MediaType.parseMediaType("application/octet-stream"))  
                .body(new InputStreamResource(in));  
	    //minioUtil.download("images", "aaa.jpg", "bbb.jpg");
	}
}
