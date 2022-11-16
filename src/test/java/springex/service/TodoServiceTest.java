package springex.service;

import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.PageRequestDTO;
import net.ict.springex.dto.PageResponseDTO;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTest {
    @Autowired
    private TodoService todoService;


    @Test
    public void testRegister(){
        TodoDTO todoDTO= TodoDTO.builder()
                .title("Test.........")
                .dueDate(LocalDate.now())
                .writer("ict01")
                .build();
        todoService.register(todoDTO);
    }
    @Test
    public void testSelectOne() {
        TodoDTO todoDTO= todoService.getOne((long)1);
        log.info(todoDTO);
    }
    @Test
    public void testDelete(){
        todoService.remove((long)2);
    }
    @Test
    public void testUpdate(){
        TodoDTO todoDTO= TodoDTO.builder()
                .tno((long)1)
                .title("수정중.........")
                .dueDate(LocalDate.now())
                .finished(true)
                .build();
        log.info(todoDTO);
        todoService.modify(todoDTO);
    }
    @Test
    public void testPaging(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1).size(10).build();
        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);
        log.info(responseDTO);
        responseDTO.getDtoList().stream().forEach(todoDTO -> log.info(todoDTO));

    }

}
