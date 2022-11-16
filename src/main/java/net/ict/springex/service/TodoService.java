package net.ict.springex.service;

import net.ict.springex.dto.PageRequestDTO;
import net.ict.springex.dto.PageResponseDTO;
import net.ict.springex.dto.TodoDTO;

public interface TodoService {
    void register(TodoDTO todoDTO);   // setter가 필요하기 때문에
    //List<TodoDTO> getAll();
    TodoDTO getOne(Long tno);
    void remove(Long tno);
    void modify(TodoDTO todoDTO);
    // 페이징 처리 요청을 받아 반환된 목록을 가져오는 기능.
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);
}
