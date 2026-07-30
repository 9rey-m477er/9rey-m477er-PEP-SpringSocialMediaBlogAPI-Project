package com.example.service;

import java.util.List;
import java.util.Optional;

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
    if(m == null || m.getMessageText() == null || m.getMessageText().isBlank() || m.getMessageText().length() > 255 || !acRep.findById(m.getPostedBy()).isPresent()){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    return meRep.save(m);
  }
  public int deleteMessage(int id){
    if(!meRep.findById(id).isPresent()){
      return 0;
    }else{
      meRep.deleteById(id);
      return 1;
    }
  }

  public List<Message> getAllMessages(){
    return meRep.findAll();
  }

  public List<Message> getAllMessagesByUser(int id){
    return meRep.findByPostedBy(id);
  }

  public int updateMessage(int id, Message updated){
    Optional<Message> target = meRep.findById(id);
    if(!target.isPresent() || updated == null || updated.getMessageText().isBlank() || updated.getMessageText().length() > 255){
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }else{
      Message newMessage = target.get();
      newMessage.setMessageText(updated.getMessageText());
      meRep.save(newMessage);
      return 1;
    }
  }

}
