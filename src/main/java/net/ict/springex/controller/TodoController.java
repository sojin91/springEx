package net.ict.springex.controller;

import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.TodoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todo")
@Log4j2

public class TodoController {

    @RequestMapping("/list")
    public void list(Model model) {
        log.info("todo list...........");

    }

    @RequestMapping(value = "/register",method= RequestMethod.GET)
    public void register(){
        log.info("todo register...............");
    }


    @PostMapping("/register")    //요청 페이지 분기, Post 방식
    public String registerPost(TodoDTO todoDTO, RedirectAttributes redirectAttributes){  //
        log.info("Post todo register..........");
        log.info(todoDTO);

        return "redirect:/todo/list";
    }



}
