package com.quiz.controller;

import com.quiz.entity.Topic;
import com.quiz.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody Topic topic) {
        topicService.addTopic(topic);
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @GetMapping("/by_id/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id) {
        Topic topic = topicService.findTopicById(id);
        return new ResponseEntity<>(topic, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getAllTopic() {
        List<Topic> topicList = topicService.getAllTopic();
        return new ResponseEntity<>(topicList, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Topic topic) {
        topicService.updateTopic(topic);
        return new ResponseEntity<>("Update Successfully", HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        topicService.deleteTopic(id);
        return new ResponseEntity<>("Topic deleted", HttpStatus.OK);

    }

}
