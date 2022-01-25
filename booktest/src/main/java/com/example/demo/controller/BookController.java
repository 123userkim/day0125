package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BookDao;
import com.example.demo.vo.BookVO;

@Controller
public class BookController {
	@Autowired
	private BookDao dao;
	

	public void setDao(BookDao dao) {
		this.dao = dao;
	}


//	@RequestMapping("/listBook.do")
//	public ModelAndView list() {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("list", dao.findAll());
//		return mav;
//	}
	
	@RequestMapping("/listBook")
	public  void list2(Model model) {
		model.addAttribute("list", dao.findAll());
	}
	
	@RequestMapping("/list.do")
	@ResponseBody
	public String list1(){
		return "hello";
	}
}
