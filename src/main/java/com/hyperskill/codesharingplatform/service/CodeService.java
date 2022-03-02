package com.hyperskill.codesharingplatform.service;

import com.hyperskill.codesharingplatform.entity.Code;
import com.hyperskill.codesharingplatform.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CodeService {
    @Autowired
    CodeRepository repository;

    public List<Code> getLatestCode(){
        List<Code> codeList = new ArrayList<>();
        repository.findFirst10ByTimeIsAndViewsIsOrderByDateDesc(0, 0).forEach(codeList::add);
        return codeList;
    }

    public Code getCodeById(UUID id){
        Code foundCode = repository.findById(id).get();
        return foundCode;
    }

    public void addCode(Code newCode){
        System.out.println("addcode: " + newCode.getDate()+" "+newCode.getId());
        repository.save(newCode);
    }

    public LocalDateTime updateTime(){
        return null;
    }

    public Boolean isCodeExpired(Code code, LocalDateTime now){
        if(code.getViews() > 0 || (now.isBefore(code.getDate().plusSeconds(code.getTime())))){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean isSecretSnippet(Code code){
        if(code.getViews() != 0 || code.getTime() > 0){
            return true;
        }
        return false;
    }

    public void deleteCode(UUID id){
        Code code = getCodeById(id);
        repository.delete(code);
    }
}
