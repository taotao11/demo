/**
 * 
 * 管理员服务层
 */
package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.AdminBean;
import com.example.demo.entity.PageBean;
import org.apache.ibatis.annotations.Param;


public interface AdminService {
			//插入多条
			public int insertUser(List<AdminBean> list) throws Exception;
			//更新操作
			public int updatetUser(AdminBean admin) throws Exception;
			//删除操作
			public int deletUser(int id) throws Exception;
			//查询单个
			public AdminBean selectUser(@Param("id") String id) throws Exception;
			//查询所有
			public List<AdminBean> selectAllUser() throws Exception;
			//动态sql
			public List<AdminBean> dtsqtest(AdminBean admin) throws Exception;
			//分页
			public PageBean findUserByPage(int currentPage, int pageSize) throws Exception;
			//总记录数
			public int countUser()throws Exception;
}
