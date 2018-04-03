package com.yy.Apollo.demointegration.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yy.Apollo.demointegration.mongo.UserRepository;
import com.yy.Apollo.demointegration.mongo.entity.User;
import com.yy.Apollo.demointegration.mongo.entity.UserOut;
import com.yy.Apollo.demointegration.rabbitmq.MessageSend;

@RestController
@RequestMapping("/mongo")
public class TestController {

    @Autowired
    private UserRepository useRepository;
    
    @Autowired
    private MessageSend messageSend;

    @GetMapping("/testft")
    public Map<String, List<User>> testFindTogether(@RequestParam("name")String name) {
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, User.FILED.FILED_NAME));
        List<User> repo = useRepository.findByName(name, sort);
        User user = new User();
        user.setName(name);
        List<User> mine = useRepository.search(user, null, sort);
        Map<String, List<User>> result = new HashMap<>();
        result.put("repo", repo);
        result.put("mine", mine);
        return result;
    }

    @PostMapping("/save")
    public User save(@RequestBody UserOut userOut) {
        User user = new User(userOut);
        return useRepository.save(user);
    }
    
    @GetMapping("/sendMsg/{queue}/{message}")
    public String sendMsg(@PathVariable("message") String message,@PathVariable("queue")String queue) {
        messageSend.sendMsg(message,queue);
        System.out.println("Message!发送成功");
        return "OK";
    }

}
