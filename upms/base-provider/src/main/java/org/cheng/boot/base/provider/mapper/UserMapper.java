package org.cheng.boot.base.provider.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.cheng.boot.base.provider.entity.User;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
