package net.ict.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.springex.domain.TodoVO;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.mapper.TodoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<TodoDTO> getAll() {
        List<TodoDTO> dtoList = todoMapper.selectAll().stream()  //stream() : 읽기전용의 일회용 병렬처리메소드.
                .map(vo-> modelMapper.map(vo,TodoDTO.class))  // vo를 dto타입으로 바꿔줌.
                .collect(Collectors.toList());   //List<TodoDTO>의 형태로 묶어줌.

        return dtoList;
    }

    @Override
    public TodoDTO getOne(Long tno) {
        TodoVO  todoVO = todoMapper.selectOne(tno);    //
        TodoDTO todoDTO = modelMapper.map(todoVO,TodoDTO.class);
        return todoDTO;
    }

    @Override
    public void remove(Long tno) {
        todoMapper.delete(tno);
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        todoMapper.update(todoVO);
    }

}
