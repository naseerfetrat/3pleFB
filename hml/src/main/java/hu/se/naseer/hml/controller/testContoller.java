package hu.se.naseer.hml.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * tesyContoler
 */
@RestController
public class testContoller {

    @GetMapping("/test")
    public String name() {
        return "naseer fetrat";
    }
}