package com.project.util;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.project.domain.FileDTO;

public class FileUtil {

	public FileDTO fileUpload(MultipartFile file) throws Exception {
		
	  //String saveDir = "D:\\javaStudy\\upload";
	  //String saveDir = "C:\\uploadFile";
	  String saveDir = getFileDir();
//	  String saveDir = "C:\\Users\\moonj\\Documents\\GitHub\\KOSTA_Project\\src\\main\\resources\\static\\productImgs";
	                    // C:\\Users\moonj\Documents\GitHub\KOSTA_Project\src\main\resources\static\productImgs
	  //String saveDir = "D:/javastudy/workspace/jblog/src/main/resources/upload";

		// 원파일이름
		String orgName = file.getOriginalFilename();

		// 확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));

		// 저장파일이름
		String saveName = System.currentTimeMillis() + UUID.randomUUID().toString() + exName;

		//파일패스 생성
		String filePath = saveDir + "\\" + saveName;
		
		//파일 사이즈
		long fileSize = file.getSize();
		
		
		// 파일업로드
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(saveDir + "/" + saveName);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			bout.write(fileData);

			if (bout != null) {
				bout.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		FileDTO fileDto = new FileDTO(orgName, exName, saveName, filePath, fileSize);
		
		return fileDto;
		
	}
	
	// 운영체제에 따라 파일경로 설정
	public String getFileDir() {
	    String os = System.getProperty("os.name").toLowerCase();
	    System.out.println("Using System Property: " + os);
	    if(os.contains("win")) {
	    	return "C:\\uploadFile";
	    	
	    } else if(os.contains("mac")){
			System.out.print("os 확인");
			return "/Users/jihyeonjeong/uploadFile";
		}
		else {
	    	return "/home/uploadFile/";
	    }
	    
	}
}