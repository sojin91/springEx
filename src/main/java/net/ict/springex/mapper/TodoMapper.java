package net.ict.springex.mapper;

import net.ict.springex.domain.TodoVO;
import net.ict.springex.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {   //TodoVO 와 매핑할 인터페이스
    String getTime();
    void insert(TodoVO todoVO);
    List<TodoVO> selectAll();
    TodoVO selectOne(Long tno);
    void delete(Long tno);
    void update(TodoVO todoVO);
    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);
    int getCount(PageRequestDTO pageRequestDTO);

}
