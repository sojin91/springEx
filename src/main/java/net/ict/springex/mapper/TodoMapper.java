package net.ict.springex.mapper;

import net.ict.springex.domain.TodoVO;

public interface TodoMapper {   //TodoVO 와 매핑할 인터페이스
    String getTime();
    void insert(TodoVO todoVO);
}