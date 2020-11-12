package com.zhao.springboot.shop.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EntryComponent {
  @Value("${mypassword}")
  String password;
  @Value("${mysalt}")
  String salt;
  @Autowired
  ObjectMapper mapper;
public String encry(Map map)  {
    try {
        String josh=mapper.writeValueAsString(map);
        //String josh=map.toString();
        //password, salt由配置文件中取读
        return Encryptors.text(password, salt).encrypt(josh);
    } catch (JsonProcessingException e) {
        return null;
    }
}
public Map decry(String string)  {
    try {
        String josh = Encryptors.text(password, salt).decrypt(string);
        return mapper.readValue(josh,Map.class);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
    }
    return null;
}
}
