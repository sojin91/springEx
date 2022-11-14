package net.ict.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.springex.domain.TodoVO;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.mapper.TodoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

//데이터베이스를 처리하는  TodoMapper, DTO와 VO변환을 처리하는 ModelMapper 주입
@Service
@Log4j2
@RequiredArgsConstructor   //생성자 주입방식
public class TodoServiceImpl implements TodoService{

    private final TodoMapper todoMapper;   // getTime(), insert()
    private final ModelMapper modelMapper;

    @Override
    public void register(TodoDTO todoDTO) {
        log.info(modelMapper);
        TodoVO todoVO= modelMapper.map(todoDTO,TodoVO.class);
        log.info(todoVO);
        todoMapper.insert(todoVO);
    }
}
