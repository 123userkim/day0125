package com.example.demo.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BoardDAO;
import com.example.demo.vo.BoardVO;

import lombok.Setter;

@Controller
@Setter
public class BoardController {
	@Autowired
	private BoardDAO dao;

	 //-----------------목록-------------------
	@RequestMapping("/listBoard")
	public void list(Model model) {
		model.addAttribute("list", dao.findAll());
	}
	
	 //-----------------추가------------------- 
	@RequestMapping(value = "/insertBoard", method = RequestMethod.GET)
	public void insertForm() {
	}
	
	
	@RequestMapping(value = "/insertBoard", method = RequestMethod.POST)
	public ModelAndView insertSubmit(HttpServletRequest request ,BoardVO b) {
		 String path = request.getRealPath("upload");	
		 System.out.println("path:"+path);
		 MultipartFile uploadFile =b.getUploadFile();
		 String fname = uploadFile.getOriginalFilename();
		 b.setFname(fname);
		 
		 try {
			 byte []data= uploadFile.getBytes();
			 FileOutputStream fos = 
					 new FileOutputStream(path + "/" + fname);
			 fos.write(data);
			 fos.close();
		 }catch(Exception e) {
			 
		 }
		 int re = dao.insert(b);
		 ModelAndView mav= new ModelAndView("redirect:/listBoard");
		 
		 if(re!=1) {
			 mav.setViewName("error");
			 mav.addObject("msg", "등록에 실패하였습니다.");	
		 }
		 return mav;
	}
	
	 //-----------------자세히------------------- 
	@RequestMapping("/detailBoard")
	public void detail(int no,Model model) {		
		model.addAttribute("b",dao.findByNo(no));		 
	}
	
	 //-----------------수정------------------- 
	@RequestMapping(value = "/updateBoard", method = RequestMethod.GET)
	public void updateForm(int no, Model model) {	 
		model.addAttribute("b", dao.findByNo(no));	 
	}
	
	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
	public ModelAndView updateSubmit(HttpServletRequest request, BoardVO b) {
		String path = request.getRealPath("upload");
		String oldFname = b.getFname();
		MultipartFile uploadFile = b.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		byte []data;
		try {
			data = uploadFile.getBytes();
			if(fname != null && !fname.equals("")) {
				FileOutputStream fos = new FileOutputStream(path +"/"+fname);
				fos.write(data);
				fos.close();
				b.setFname(fname);				
			}
		}catch(Exception e) {
			
		}
		
		ModelAndView mav= new ModelAndView("redirect:/listBoard");
		int re = dao.update(b);
		if(re !=1 ) {
			mav.setViewName("error");
			mav.addObject("msg","수정에 실패");
		}else {
			if(fname !=null && !fname.equals("")) {
				File file = new File(path +"/"+oldFname);
				file.delete();
			}
		}		
		return mav;
	}
}
