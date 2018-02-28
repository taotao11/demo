/**
 * adminMapper
 * 
 * 
 */
package com.example.demo.mapper;

import java.util.List;

import com.example.demo.entity.AdminBean;
import org.apache.ibatis.annotations.Param;



public interface AdminMapper {
	//插入多条
		public int insertUser(List<AdminBean> list) throws Exception;
		//更新操作
		public int updateUser(AdminBean admin) throws Exception;
		//删除操作
		public int deletUser(int id) throws Exception;
		//查询单个
		public AdminBean selectUser(@Param("id") String id) throws Exception;
		//查询所有
		public List<AdminBean> selectAllUser() throws Exception;
		//动态sql
		public List<AdminBean> dtsqtest(AdminBean admin) throws Exception;
		//总记录数
		public int countUser()throws Exception;
}
