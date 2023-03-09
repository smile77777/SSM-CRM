package com.ljc.crm.settings.mapper;

import com.ljc.crm.settings.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbggenerated Wed Feb 15 14:56:51 CST 2023
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbggenerated Wed Feb 15 14:56:51 CST 2023
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbggenerated Wed Feb 15 14:56:51 CST 2023
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbggenerated Wed Feb 15 14:56:51 CST 2023
     */
    User selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbggenerated Wed Feb 15 14:56:51 CST 2023
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_user
     *
     * @mbggenerated Wed Feb 15 14:56:51 CST 2023
     */
    int updateByPrimaryKey(User record);

    /**
     * 根据账号密码查询用户
     * @param map
     * @return
     */
    User selectUserByLoginActAndPwd(Map<String,Object> map);

    /**
     * 查询所有用户
     * @return
     */
    List<User> selectAllUser();
}
