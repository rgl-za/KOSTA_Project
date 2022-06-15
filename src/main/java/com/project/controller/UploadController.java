package com.project.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.domain.UploadResultDTO;

import net.coobird.thumbnailator.Thumbnailator;

@RestController
public class UploadController {

	@PostMapping("/uploadAjax")
	public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {

		List<UploadResultDTO> resultDTOList = new ArrayList<>();
		for (MultipartFile uploadFile : uploadFiles) {

			// 이미지 파일만 업로드 가능
			if (uploadFile.getContentType().startsWith("image") == false) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}

			// 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
			String originalName = uploadFile.getOriginalFilename();

			String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

			// 날짜 폴더 생성
			String folderPath = makeFolder();

			// UUID
			String uuid = UUID.randomUUID().toString();

			// 저장할 파일 이름
			String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + fileName;

			Path savePath = Paths.get(saveName);

			try {
				uploadFile.transferTo(savePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 추가할 부분
			try {
				uploadFile.transferTo(savePath);// 실제 이미지 저장(원본 파일)

				// 섬네일 생성 -> 섬네일 파일 이름은 중간에 s_로 시작
				String thubmnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_"
						+ fileName;

				File thumbnailFile = new File(thubmnailSaveName);
				// 섬네일 생성
				Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);

				resultDTOList.add(new UploadResultDTO(fileName, uuid, folderPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
	}

	private String makeFolder() {

		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

		String folderPath = str.replace("/", File.separator);

		// make folder----
		File uploadPatheFolder = new File(uploadPath, folderPath);

		if (uploadPatheFolder.exists() == false) {
			uploadPatheFolder.mkdir();
		}
		return folderPath;
	}

	@PostMapping("/removeFile")
	public ResponseEntity<Boolean> removeFile(String fileName) {
		String srcFileName = null;

		try {
			srcFileName = URLDecoder.decode(fileName, "UTF-8");
			File file = new File(uploadPath + File.separator + srcFileName);

			boolean result = file.delete();

			File thumbnail = new File(file.getParent(), "s_" + file.getName());

			result = thumbnail.delete();

			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
