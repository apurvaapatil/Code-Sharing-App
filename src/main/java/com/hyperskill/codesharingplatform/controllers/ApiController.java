package com.hyperskill.codesharingplatform.controllers;

import com.hyperskill.codesharingplatform.entity.Code;
import com.hyperskill.codesharingplatform.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class ApiController {
    @Autowired
    CodeService service;

    @GetMapping("snippetshare/api/code/get/{id}")
    public Code getCodeById(@PathVariable UUID id){
        try{
            Code newCode = service.getCodeById(id);
            Boolean secretSnippet = service.isSecretSnippet(newCode);
            LocalDateTime now = LocalDateTime.now();

            if(secretSnippet){
                newCode.setViews(newCode.getViews() - 1);
                if(service.isCodeExpired(newCode, now)){
                    service.deleteCode(newCode.getId());
                }
                else{
                    service.addCode(newCode);
                }
            }

            return newCode;

        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("snippetshare/api/code/latest")
    public List<Code> getLatestCode(){
        List<Code> lastestCodeList = service.getLatestCode();
        return lastestCodeList;
    }

    @PostMapping("snippetshare/api/code/new")
    public Map<String, UUID> postCode(@RequestBody Code code){
        service.addCode(code);
        return Collections.singletonMap("id",code.getId());
    }

    @DeleteMapping("snippetshare/api/code/{id}")
    public void deleteCode(@PathVariable UUID id){
        service.deleteCode(id);
    }

}
