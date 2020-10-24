package com.example.demo.controller;

import com.example.demo.controller.provider.GithubProvider;
import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("4cf9941bd485368ff105");
        accessTokenDTO.setClient_secret("1bec7b4c38af3a3b8a58c9894acaddd753f80313");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        System.out.println("now: "+accessToken);
        GithubUser user = githubProvider.getUser("e8873b385309337c4f34296f4ee7548a379e90a6");
        System.out.println(user.getName());
        return "index";
    }


}
