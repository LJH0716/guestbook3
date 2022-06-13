package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {
	
	
	@RequestMapping(value="/addList", method= {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("Controller > addList");
		
		//dao만들기
		GuestbookDao dao = new GuestbookDao();
		List<GuestbookVo> guestList = dao.getList();
		
		model.addAttribute("guestList", guestList);
		
		return "/WEB-INF/views/addList.jsp";
	}

	@RequestMapping(value="/add", method= {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		System.out.println("Controller > add");
		
		//dao만들기
		GuestbookDao guestbookDao = new GuestbookDao();
		guestbookDao.insert(guestbookVo);
		
		return "redirect:/addList";
	}
	
	@RequestMapping(value="/deleteForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm() {
		System.out.println("guestController>deleteForm()");
		
		return "/WEB-INF/views/deleteForm.jsp";
	}
	
	@RequestMapping(value="/delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no,
						 @RequestParam("password") String password) {
		System.out.println("Controller > delete");
		
		//dao만들기
		GuestbookDao dao = new GuestbookDao();
		GuestbookVo guestbookVo = dao.getbookList(no);
		
		if(password.equals(guestbookVo.getPassword())) {
			
			dao.delete(guestbookVo);
			System.out.println("삭제 완료");
			return "redirect:/addList";	
		}else {
			System.out.println("패스워드 오류");
			return "redirect:/addList";
		}
		
		
	}
	
	
	
	
	
	
}