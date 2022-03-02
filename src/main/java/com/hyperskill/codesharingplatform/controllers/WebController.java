package com.hyperskill.codesharingplatform.controllers;

import com.hyperskill.codesharingplatform.entity.Code;
import com.hyperskill.codesharingplatform.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("snippetshare")
public class WebController {
    @Autowired
    CodeService service;

    Long secondsRemaining;

    @GetMapping("/home")
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/about")
    public String getAboutPage(){
        return "aboutUs";
    }

    @GetMapping("/contact")
    public String getContactPage(){
        return "contact";
    }

    @GetMapping("/code/get/{id}")
    public String getCodeById(Model model, @PathVariable UUID id){
        try{
            Code newCode = service.getCodeById(id);
            Boolean secretSnippet = service.isSecretSnippet(newCode);

            if(secretSnippet){
                LocalDateTime expireTime = newCode.getDate().plusSeconds(newCode.getTime());
                LocalDateTime now = LocalDateTime.now();
                Duration duration = Duration.between(now, expireTime);
                secondsRemaining = duration.getSeconds();

                if(secondsRemaining < 0){
                    secondsRemaining = Long.valueOf(0);
                }

                if(newCode.getViews() > 0){
                    newCode.setViews(newCode.getViews() - 1);
                }

                if(service.isCodeExpired(newCode, now)){
                    service.deleteCode(newCode.getId());
                }
                else{
                    service.addCode(newCode);
                }
            }

            model.addAttribute("userCode", newCode.getCode());  // the value inside ${__}
            model.addAttribute("date", newCode.getFormattedDate());
            model.addAttribute("time", secondsRemaining);
            model.addAttribute("views", newCode.getViews());
            model.addAttribute("secret", secretSnippet);
            model.addAttribute("snippetId", newCode.getId());
            return "displayCode";

        } catch (Exception e){
            return "notFoundErrorPage";
        }
    }

    @GetMapping("/code/latest")
    public String getLatestCode(Model model){
        List<Code> lastestCodeList = service.getLatestCode();
        model.addAttribute("codeLists", lastestCodeList);
        return "displayCodeList";
    }

    @GetMapping("/code/new")
    public String addNewCodePage(Model model){
        return "submitNewCode";
    }

}
