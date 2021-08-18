package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.community.Community;
import com.example.demo.security.UserInfo;
import com.example.demo.security.service.MyUserDetails;
import com.example.demo.service.community.CommunityService;

@Controller
public class CommunityController {
	@Autowired
  private CommunityService communityService;

	//@RequestMapping(value = "**/community", method = RequestMethod.GET)
	@RequestMapping(value = "/community")
	public ModelAndView community(@ModelAttribute("params") Community params, Principal principal) {

		ModelAndView modelAndView = new ModelAndView();

		try{
			Authentication authentication = (Authentication) principal;
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			UserInfo userInfo = userDetails.getUserInfo();
			modelAndView.addObject("userName", userInfo.getUserName());
//			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");

		}catch(Exception e) {
		}

		List<Community> communityList = communityService.selectAllCommunity(params);
		modelAndView.addObject("communityList", communityList);

		modelAndView.setViewName("community");


		System.out.println("community controller start");
		return modelAndView;
	}

	@GetMapping(value = "/community/insert")
	public ModelAndView communityInsertGet(@Valid Community community, BindingResult bindingResult, Principal principal) {

		ModelAndView modelAndView = new ModelAndView();

		try{
			Authentication authentication = (Authentication) principal;
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			UserInfo userInfo = userDetails.getUserInfo();
			modelAndView.addObject("userName", userInfo.getUserName());
//			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");

		}catch(Exception e) {
		}

//		List<Community> communityList = communityService.selectAllCommunity(params);
//		modelAndView.addObject("communityList", communityList);
//
//		modelAndView.setViewName("community");

//		modelAndView.addObject("community", new Community());
		modelAndView.setViewName("communityWrite");


		System.out.println("community controller start");
		return modelAndView;
	}

	@PostMapping(value = "/community/insert")
	public ModelAndView communityInsertPost(@ModelAttribute("params") @Valid Community params, BindingResult bindingResult, Principal principal) {

		ModelAndView modelAndView = new ModelAndView();

		try{
			Authentication authentication = (Authentication) principal;
			MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
			UserInfo userInfo = userDetails.getUserInfo();
			modelAndView.addObject("userName", userInfo.getUserName());
//			modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");

		}catch(Exception e) {
		}

		communityService.insertCommunity(params);



//		List<Community> communityList = communityService.selectAllCommunity(params);
//		modelAndView.addObject("communityList", communityList);
//
//		modelAndView.setViewName("community");
		List<Community> communityList = communityService.selectAllCommunity(params);
		modelAndView.addObject("communityList", communityList);
		modelAndView.setViewName("community");


		return modelAndView;
	}



}
