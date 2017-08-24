package com.xingxue.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xingxue.entity.Order;
import com.xingxue.entity.User;
import com.xingxue.service.OrderService;

@Controller
public class DemoController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("/system/email")
	public void m1() {
	}

	@RequestMapping("/system/sms")
	public void m2() {
	}

	@RequestMapping("/order/search")
	public void m5() {
	}

	@RequestMapping("/order/search/findAll")
	public String m51(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		Integer[] orgIds = user.getOrgIds();
		List<Order> list = orderService.findAll(orgIds);
		model.addAttribute("list", list);
		return "/order/search";
	}

	@RequestMapping("/order/search/findBy")
	public String m52(Integer customerId, HttpSession session, Model model) {
		if (customerId == null) {
			model.addAttribute("error", "顾客id不能为空");
			return "/order/search";
		}
		User user = (User) session.getAttribute("user");
		Integer[] orgIds = user.getOrgIds();
		List<Order> list = orderService.findBy(customerId, orgIds);
		model.addAttribute("list", list);
		return "/order/search";
	}

	@RequestMapping("/order/refund")
	public void m6() {
	}

	@RequestMapping("/order/stat")
	public void m7() {
	}

	@RequestMapping("/product/search")
	public void m8() {
	}

	@RequestMapping("/product/onoff")
	public void m9() {
	}

	@RequestMapping("/product/inventory")
	public void m10() {
	}

	@RequestMapping("/product/stat")
	public void m11() {
	}

}
