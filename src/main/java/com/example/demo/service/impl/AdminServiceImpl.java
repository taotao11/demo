/**
 * 
 * 管理员服务层实现类
 */
package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.entity.AdminBean;
import com.example.demo.entity.PageBean;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.service.AdminService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("adminService")
public class AdminServiceImpl implements AdminService {
//	注入
	@Autowired
	private AdminMapper adminMapper;
//	增加管理员

	@Override
	@Transactional
	public int insertUser(List<AdminBean> list) throws Exception {
		// TODO Auto-generated method stub
		
		return adminMapper.insertUser(list);
	}
//修改
	@Override
	@Transactional
	public int updatetUser(AdminBean admin) throws Exception {
		// TODO Auto-generated method stub
		return adminMapper.updateUser(admin);
	}

	/**
	 * 删除Admin
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public int deletUser(int id) throws Exception {
		// TODO Auto-generated method stub
		return adminMapper.deletUser(id);
	}
//登录管理员
	@Override
	public AdminBean selectUser(String id) throws Exception {
		// TODO Auto-generated method stub
		return adminMapper.selectUser(id);
	}
//查询所有管理员
	@Override
	public List<AdminBean> selectAllUser() throws Exception {
		// TODO Auto-generated method stub
		return adminMapper.selectAllUser();
	}

	/**
	 * 动态sql
	 * @param admin
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<AdminBean> dtsqtest(AdminBean admin) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 分页操作
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean findUserByPage(int currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		//设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
		PageHelper.startPage(currentPage, pageSize);
		List<AdminBean> allUser = adminMapper.selectAllUser();//查询到的信息
		int countNums = adminMapper.countUser();//总记录数
		PageBean<AdminBean> pageData = new PageBean<>(currentPage,pageSize,countNums);
		pageData.setItems(allUser);
		System.out.println(pageData);
		return pageData;
	}

	/**
	 * 总记录数
	 * @return
	 * @throws Exception
	 */
	@Override
	public int countUser() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
