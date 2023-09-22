package com.coca.controller;

import com.coca.dao.ContactsDAO;
import com.coca.model.Contacts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/contacts")
public class ContactsController {
    @Autowired
    ContactsDAO contactsDAO;

    @RequestMapping("index.html")
    public String index(){
        return "contacts/index";
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseEntity search(){
        List<Contacts> list = contactsDAO.getAll();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Contacts contacts){
        try {
            Long checkAdd = contactsDAO.add(contacts);
            return new ResponseEntity(checkAdd, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity(0, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity delete(@RequestBody Long ids){
        return new ResponseEntity(ids, HttpStatus.OK);
    }
}
