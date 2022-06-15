package com.project.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;

// 파일 업로드 용도로 만들었습니다.
@Data
@AllArgsConstructor
public class UploadResultDTO {

	private String fileName;

	private String uuid;

	private String folderPath;

	public String getImageURL() {
		try {
			return URLEncoder.encode(folderPath + "/" + uuid + fileName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	// 추가
    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(folderPath + "/s_" +uuid + "_" +fileName,"UTF-8");
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        return "";
    }
}
