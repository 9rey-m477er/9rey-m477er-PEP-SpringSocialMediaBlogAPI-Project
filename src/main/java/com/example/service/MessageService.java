package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
  @Autowired
  private MessageRepository meRep;

  @Autowired
  private AccountRepository acRep;

  public Message createMessage(Message m){
    if(m == null || m.getMessageText().isBlank() || m.getMessageText().length() > 255 || acRep.findById(m.getPostedBy()).isPresent()){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return meRep.save(m);
  }
}
