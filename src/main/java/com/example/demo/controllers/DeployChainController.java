package com.example.demo.controllers;

import com.example.demo.services.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/deployChain")
public class DeployChainController {

    private final TestService testService;

    public DeployChainController(TestService testService) {
        this.testService = testService;
    }


    @RequestMapping(
            value = "/{repoName}",
            method = RequestMethod.POST)
    private ResponseEntity<?> doDeployChain (
            @PathVariable(value = "repoName") String reponame,
            @RequestBody String json) {

        testService.doDeployChain(reponame, json);

        return null;
    }

}
