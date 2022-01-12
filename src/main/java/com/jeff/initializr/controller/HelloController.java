package com.jeff.initializr.controller;

import com.jeff.initializr.service.ExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class HelloController {


    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello,SpringBoot!";
    }





    @GetMapping("home")
    public String index() {
        return "index";
    }
}