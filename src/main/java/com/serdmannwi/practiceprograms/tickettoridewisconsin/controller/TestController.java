package com.serdmannwi.practiceprograms.tickettoridewisconsin.controller;

import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.TestRequest;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.controller.model.TestResponse;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.TestRecord;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.service.TestService;
import com.serdmannwi.practiceprograms.tickettoridewisconsin.repository.model.TestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/allTests")
    public ResponseEntity<List<TestRecord>> getAllTestModels() {
        System.out.println("Get all Test endpoint running");
        return ResponseEntity.ok(testService.getAllTests());
    }

    @PostMapping("/newTest")
    public ResponseEntity<TestResponse> createNewtest(@RequestBody TestRequest testRequest) {
        System.out.println("Create new Test endpoint running");
        TestRecord newTestRecord = testService.addTest(requestToModel(testRequest));
        return ResponseEntity.ok(recordToResponse(newTestRecord));
    }

    /**------------------------------------Helper Methods------------------------------------**/
    private TestModel requestToModel(TestRequest testRequest) {
        return new TestModel(testRequest.getId(), testRequest.getName());
    }

    private TestResponse recordToResponse(TestRecord testRecord) {
        return new TestResponse(testRecord.getId(), testRecord.getName());
    }
}
