package net.ict.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor

public class TodoController {

    private  final TodoService todoService;  //사용자에게 받은 정보를 처리하는 Service 객체를 가져옴.

    @RequestMapping("/list")   //  최종 경로 /todo/list
    public void list(Model model) {
        log.info("todo list...........");
        // dtoList라는 이름의 목록에 todoService 클래스의 getAll()을통해 가져온 리스트를 담음.
        model.addAttribute("dtoList",todoService.getAll());
    }

    @RequestMapping(value = "/register",method= RequestMethod.GET)
    public void register(){
        log.info("todo register...............");
    }

    @PostMapping("/register")    //요청 페이지 분기, Post 방식
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("Post todo register..........");   //TodoDTO는 검증대상, BindingResult는 에러의 결과를 담아내는 객체.

        if (bindingResult.hasErrors()){

            log.error("has error");   //만약 DTO에 정해놓은 @NotEmpty와 @Future에 위배되는 에러가 있다면
            redirectAttributes.addFlashAttribute("error",bindingResult.getAllErrors());
            return "redirect:/todo/register";     //등록페이지로 다시 돌려보냄.
        }
        log.info(todoDTO);
        todoService.register(todoDTO);   //Service 객체를 통해 처리.

        return "redirect:/todo/list";
    }
    @GetMapping({"/read", "/modify"})
    public void read(Long tno,Model model){
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto", todoDTO);   //view에 보여질 내용을 model에 담기.
    }
    @PostMapping("modify")
    public String modify(@Valid TodoDTO todoDTO, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            log.info("has errors......");
            redirectAttributes.addFlashAttribute("error",bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }
        log.info(todoDTO);
        todoService.modify(todoDTO);
        return "redirect:/todo/list";
    }

    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes){ //글번호로선택된 글의 모든 정보를 담아오는 RedirectAttributes

        log.info("remove..........");
        log.info("tno :"+ tno);
        todoService.remove(tno);
        return "redirect:/todo/list";   //삭제후에 list로 이동.

    }


}
