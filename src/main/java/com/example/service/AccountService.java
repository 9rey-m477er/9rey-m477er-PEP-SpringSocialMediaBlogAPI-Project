package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
  @Autowired
  private AccountRepository acRep;

  public Account addAccount(Account ac){
    if(ac == null || ac.getUsername().isBlank() || ac.getPassword().length() < 4){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }else if(acRep.findByUsername(ac.getUsername())!= null){
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }
    return acRep.save(ac);
  }

  public Account login(String username, String password){
    if(acRep.findByUsernameAndPassword(username, password) == null){
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
    return acRep.findByUsernameAndPassword(username, password);
  }
}
