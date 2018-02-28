/**
 * 管理员控制层
 */
package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.entity.AdminBean;
import com.example.demo.entity.PageBean;
import com.example.demo.service.AdminService;
import com.example.demo.util.RandomValidateCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller

public class AdminController {
	
	@Autowired
	AdminService service;

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);


	/**
	 *
	 * 自定以异常
	 * @param
	 * @param
	 */
	@RequestMapping("/zException")
	public String zException (){
		int i = 3/0;
		return "";
	}
	//获得验证码
	@RequestMapping(value = "/getVerify")
	public void getVerify(HttpServletRequest request, HttpServletResponse response) {

		try {
			response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
			response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expire", 0);
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			randomValidateCode.getRandCode(request, response);//输出验证码图片方法
		} catch (Exception e) {
			logger.error("获取验证码失败>>>>   ", e);
		}
	}
	/**
	 * 忘记密码页面校验验证码
	 */
	public boolean checkVerify(String yzm, HttpSession session) {
		try{
			//从session中获取随机数
			String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
			if (random == null) {
				return false;
			}
			if (random.equals(yzm)) {
				return true;
			} else {
				return false;
			}
		}catch (Exception e){
			logger.error("验证码校验失败", e);
			return false;
		}
	}
	@RequestMapping("/loginOut")
	public String loginOut(HttpSession session){
		session.setAttribute("admin",null);
		return "index";
	}
	/**
	 * 删除
	 */
	@RequestMapping("/delet/{id}")
	public  String deletAdmin(@PathVariable("id") Integer id){
		int index = 0;
		try {
			index = service.deletUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (index > 0){
			System.out.println("删除成功");
		}
		return "redirect:/selectAllAdmin/1";
	}
	/**
	 * cehs
	 * 返回要修改的值
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/xiugai/{id}")
	public String xiugai(@PathVariable("id") String id,Model model){
		AdminBean adminBean = null;
		try {
//			查询结果
			adminBean = service.selectUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (adminBean == null){
			adminBean = new AdminBean();
		}
		model.addAttribute("admin",adminBean);
		return "xiugai";
	}

	@RequestMapping("/zuce")
	public String zuce(Model map){
		map.addAttribute("adminBean",new AdminBean());
		return "zuce";
	}
//	测试
	@RequestMapping(value = {"/","/index"})
	public String index(){
		System.out.println("进入程序");
		return "index";
		
	}
//	修改管理员info
	@RequestMapping("/updateAdmin")
	public String updateAdmin(AdminBean admin){
		System.out.println(admin);
		int isUpdate = 0;
		try {
			isUpdate = service.updatetUser(admin);
			if (isUpdate > 0) {
				System.out.println("更新成功");
				return "redirect:/selectAllAdmin/1";
			}else {
				System.out.println("更新失败");
				return "xiugai";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "xiugai";
		
	}
	
	
//查询所有的管理员
	@RequestMapping("/selectAllAdmin/{id}")
	public String selectAllAdmin(@PathVariable("id") int id,Model map){
		//传空值 防止模板出错
		map.addAttribute("admin",new AdminBean());
//		接收列表 分页信息
		PageBean<AdminBean> pageInfo = null;
		try {
		pageInfo = service.findUserByPage(id,2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		传值入网页
		map.addAttribute("page", pageInfo);
		return "item";
	}
	
//	登录管理员
	@RequestMapping("/dengluAdmin")
	public String dengluAdmin(Model map, AdminBean admin,@RequestParam Map<String,Object> param,HttpSession session){

		String yzm = param.get("yzm").toString();
		System.out.println(yzm);
		if (!checkVerify(yzm,session)){
			map.addAttribute("message","验证码错误，请重试");
			return "index";
		}
		//		定义接收实体
		AdminBean adminBean;
//		判断是否为空
		if (admin.getUname() == null || admin.getUname().equals("")) {
			map.addAttribute("message","用户名为空！");
			return "index";
		}else{
			try {
				adminBean = service.selectUser(admin.getUname());
	//			判断密码是否正确
				if (adminBean.getUpass().equals(admin.getUpass())) {
					System.out.println("登录成功");
					session.setAttribute("admin",adminBean);
					return "redirect:/selectAllAdmin/1";
				}else {
					map.addAttribute("message","密码错误！");
					return "index";
				}
			} catch (Exception e) {
				System.out.println(e.toString());
				map.addAttribute("message","用户名不存在！");
				return "index";
			}
		}
	}

	/**
	 * 增加管理员
	 *  @Valid 开启数据验证
	 * @param
	 * @param admin
	 * @return
	 */
	@RequestMapping("/addAdmin")
	public String addAdmin( @Valid AdminBean admin, BindingResult result,Model model){
		System.out.println(admin);
		if(result.hasErrors()){
			List<FieldError> fieldErrors = result.getFieldErrors();
			//result.rejectValue("","",""); //自定义错误信息(属性名 错误信息 错误类型)
			for (FieldError error : fieldErrors){
				System.out.println(error.getField() + ":" +error.getDefaultMessage() + ":" + error.getCode());

			}
			return "zuce";
		}
//		接收表单值
		List<AdminBean> list = new ArrayList<>();
		list.add(admin);
		int index = 0;
		try {
		index = service.insertUser(list);
		if (index > 0) {
			model.addAttribute("message","注册成功！");
			return "index";
		}else {
			model.addAttribute("message","请按规则填写！");
			return "zuce";
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			model.addAttribute("message","未知错误！");
			return "index";
		}

	}
}
