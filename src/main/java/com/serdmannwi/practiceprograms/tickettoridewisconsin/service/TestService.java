package com.serdmannwi.practiceprograms.tickettoridewisconsin.service;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.TestRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.TestRepository;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.model.TestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestRecord> getAllTests() {
        List<TestRecord> testRecords = new ArrayList<>();
        testRecords.addAll(testRepository.findAll());
        return testRecords;
    }

    public TestRecord getTestModelById(String id) {
        return testRepository.findById(id).orElse(null);
    }
    public TestRecord addTest(TestModel testModel) {
        return testRepository.save(new TestRecord(testModel.getId(), testModel.getName()));
    }
}
