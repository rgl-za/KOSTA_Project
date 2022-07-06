package com.project.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.project.domain.CerDTO;
import com.project.domain.CerFileDTO;
import com.project.service.CerService;

@Controller
public class CerController {
	
	@Autowired
	private CerService cerService; 
	
	@RequestMapping("/openCerList.do")
	public ModelAndView openCerList() throws Exception{
		ModelAndView mv = new ModelAndView("/cermainfile");
		
		List<CerDTO> list = cerService.selectCerList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping("/openCerWrite.do")
	public String openCerWrite() throws Exception{
		return "/cerWrite";
	}
	
	@RequestMapping("/insertCer.do")
	public String insertCer(CerDTO cer, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{
		cerService.insertCer(cer, multipartHttpServletRequest);
		return "redirect:/openCerList.do";
	}
	
	@RequestMapping("/openCerDetail.do")
	public ModelAndView openCerDetail(@RequestParam int cerIdx) throws Exception{
		ModelAndView mv = new ModelAndView("/cerDetail");
		
		CerDTO cer = cerService.selectCerDetail(cerIdx);
		mv.addObject("cer", cer);
		
		return mv;
	}
	
	@RequestMapping("/updateCer.do")
	public String updateCer(CerDTO cer) throws Exception{
		cerService.updateCer(cer);
		return "redirect:/openCerList.do";
	}
	
	@RequestMapping("/deleteCer.do")
	public String deleteCer(int cerIdx) throws Exception{
		cerService.deleteCer(cerIdx);
		return "redirect:/openCerList.do";
	}
	
	@RequestMapping("/downloadCerFile.do")
	public void downloadCerFile(@RequestParam int fidx, @RequestParam int cernum, HttpServletResponse response) throws Exception{
		CerFileDTO cerFile = cerService.selectCerFileInformation(fidx, cernum);
		if(ObjectUtils.isEmpty(cerFile) == false) {
			String fileName = cerFile.getOriginalFileName();
			
			byte[] files = FileUtils.readFileToByteArray(new File(cerFile.getStoredFilePath()));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName,"UTF-8")+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
}
