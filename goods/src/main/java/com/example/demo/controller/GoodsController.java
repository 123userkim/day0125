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

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

@Controller
public class GoodsController {
	
	@Autowired
	private GoodsDAO dao;

	public void setDao(GoodsDAO dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/listGoods")
	public void list(Model model) {
		model.addAttribute("list", dao.findAll());
	}
	
	@RequestMapping(value = "/insertGoods", method = RequestMethod.GET)
	public void insertForm() {
	}
	
	@RequestMapping(value = "/insertGoods", method = RequestMethod.POST)
	public ModelAndView insertSubmit(HttpServletRequest request  , GoodsVO g) {
		String path = request.getRealPath("images");
		System.out.println("path:"+path);
		MultipartFile uploadFile = g.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		g.setFname(fname);
		try {
			byte []data = uploadFile.getBytes();
			FileOutputStream fos =
					new FileOutputStream(path + "/" + fname);
			fos.write(data);
			fos.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		int re = dao.insert(g);
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		if(re != 1) {
			mav.setViewName("error");
			mav.addObject("msg", "상품등록에 실패하였습니다.");
		}
		return mav;
	}
	
	@RequestMapping("/detailGoods")
	public void detail(int no, Model model) {
		model.addAttribute("g", dao.findByNo(no));
	}
	

	@RequestMapping(value = "/updateGoods", method = RequestMethod.GET)
	public ModelAndView updateForm(int no) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("g", dao.findByNo(no));
		return mav;
	}
	
	@RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
	public ModelAndView updateSubmit(HttpServletRequest request,   GoodsVO g) {
		String path = request.getRealPath("images");
		
		//원래 사진이름을 미리 변수에 담아 둡니다.
		String oldFname = g.getFname();
		
		//업로드한 파일의 정보를 받아 옵니다.
		MultipartFile uploadFile = g.getUploadFile();
		String fname = uploadFile.getOriginalFilename();
		byte []data;
		try {
			data = uploadFile.getBytes();
			
			//만약에 사진도 수정했다면
			//업로드한 파일이 있다면 파일을 복사합니다.
			if(fname != null && !fname.equals("")) {
				FileOutputStream fos = new FileOutputStream(path + "/" + fname);
				fos.write(data);
				fos.close();
				g.setFname(fname);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		
		int re = dao.update(g);
		if(re != 1) {
			mav.setViewName("error");
			mav.addObject("msg", "상품수정에 실패하였습니다.");
		}else { //수정에 성공했고
			if(fname != null && !fname.equals("")) { //사진도 수정했다면
				File file = new File(path + "/" + oldFname);
				file.delete();//원래 사진을 삭제합니다.
			}
		}
		return mav;
	}
	
	@RequestMapping("/deleteGoods")
	public ModelAndView delete(HttpServletRequest request, int no) {
		String path = request.getRealPath("images");
		
		//지우고자 하는 상품의 사진파일이름을 미리 저장해 둡니다.
		String oldFname = dao.findByNo(no).getFname();
		
		
		ModelAndView mav = new ModelAndView("redirect:/listGoods");
		int re = dao.delete(no);
		if(re != 1) {
			mav.setViewName("error");
			mav.addObject("msg", "상품삭제에 실패하였습니다.");
		}else {
			File file = new File(path +"/" +oldFname);
			file.delete();
		}
		return mav;
	}
}