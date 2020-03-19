package com.lysong.friday.dao;

import com.lysong.friday.dto.UserDto;
import com.lysong.friday.model.SysUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {

	/**
	 * 根据对象保存
	 * @param user
	 * @return
	 */
	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_user(username, password, nickname, headImgUrl, phone, telephone, email, birthday, sex, status, createTime, updateTime) values(#{username}, #{password}, #{nickname}, #{headImgUrl}, #{phone}, #{telephone}, #{email}, #{birthday}, #{sex}, #{status}, now(), now())")
	int save(SysUser user);

	/**
	 * 根据用户名返回user对象
	 * @param username 用户名
	 * @return
	 */
	@Select("select * from sys_user t where t.username = #{username}")
	SysUser getUser(String username);

	/**
	 * 根据传入的值返回某页的list，根据id
	 * @param startPosition 起始搜索点
	 * @param limit 总数据条数
	 * @return
	 */
	@Select("select * from sys_user t order by t.id limit #{startPosition}, #{limit}")
	List<SysUser> getAllUsersByPage(@Param("startPosition")Integer startPosition, @Param("limit")Integer limit);

	/**
	 * 返回总的数据条数
	 * @return
	 */
	@Select("select count(*) from sys_user t")
	Long countAllUsers();

	@Select("select * from sys_user t where t.telephone = #{telephone}")
	SysUser getUserByPhone(String telephone);

	@Select("select * from sys_user t where t.id = #{id}")
    SysUser getUserById(Long id);

	int updateUser(SysUser sysUser);

	@Delete("delete from sys_user where id = #{id}")
	int deleteByUserId(int id);

	@Select("select count(*) from sys_user t where t.username like '%${username}%'")
	Long getUserByFuzzyUserName(@Param("username") String username);

	@Select("select * from sys_user t where t.username like '%${username}%' limit #{startPosition}, #{limit}")
	List<SysUser> getUserByFuzzyUserNameByPage(@Param("username")String username, @Param("startPosition")Integer startPosition, @Param("limit")Integer limit);
}
