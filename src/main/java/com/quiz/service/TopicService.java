package com.quiz.service;

import com.quiz.entity.Topic;
import com.quiz.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public void addTopic(Topic topic){
        topicRepository.addTopic(topic);
    }

    public Topic findTopicById(int id){
        return topicRepository.findTopicById(id);

    }
    public void updateTopic(Topic topic){
        topicRepository.updateTopic(topic);
    }

    public void deleteTopic(Integer id){
        topicRepository.deleteTopic(id);

    }

    public List<Topic> getAllTopic(){
        return topicRepository.getAll();
    }
}
