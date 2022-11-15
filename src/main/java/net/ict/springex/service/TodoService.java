package net.ict.springex.service;

import net.ict.springex.domain.TodoVO;
import net.ict.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {
    void register(TodoDTO todoDTO);   // setter가 필요하기 때문에
    List<TodoDTO> getAll();

}
