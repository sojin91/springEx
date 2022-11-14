package net.ict.springex.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
    @Select("select now()")  //mapper Interface
    String getTime();
}
