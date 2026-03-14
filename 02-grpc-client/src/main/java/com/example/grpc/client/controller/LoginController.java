package com.example.grpc.client.controller;

import com.example.grpc.client.controller.dto.HelloResponseDto;
import com.example.grpc.client.service.HelloService;
import com.example.grpc.client.service.dto.HelloServiceInDto;
import com.example.grpc.client.service.dto.HelloServiceOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    HelloService helloService;

    /**
     * カスタムログイン画面を表示
     */
    @GetMapping("/login")
    public String login() {
        return "login"; // src/main/resources/templates/login.html
    }

    /**
     * ログイン後のホームページを表示
     * Principal を使うことでログイン中のユーザー情報を取得可能
     */
    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        HelloServiceInDto serviceInDto = new HelloServiceInDto();
        if (principal != null) {
            model.addAttribute("username", principal.getName());
            serviceInDto.setName(principal.getName());
        }
        HelloServiceOutDto response = helloService.sayHello(serviceInDto);
        model.addAttribute("message", response.getMessage());

        return "home"; // src/main/resources/templates/home.html
    }

    /**
     * ルートパスにアクセスした場合、homeへリダイレクト
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
}