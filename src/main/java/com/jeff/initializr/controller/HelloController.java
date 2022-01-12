package com.jeff.initializr.controller;

import com.jeff.initializr.service.ExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class HelloController {
    @Autowired
    private ExcelService excelService;

    @RequestMapping("/")
    @ResponseBody
    public String hello() {
        return "Hello,SpringBoot!";
    }


    @RequestMapping("/testExcel")
    @ResponseBody
    public void testExcel(@RequestParam("excelInput") MultipartFile excelInput) throws IOException {
        Workbook wb = excelService.createWorkbook(excelInput.getInputStream());
        // 2.读取页脚sheet
        Sheet sheetAt = wb.getSheetAt(0);
        // 3.循环读取某一行
        for (Row row : sheetAt) {
            // 4.读取每一行的单元格
            String stringCellValue = row.getCell(0).getStringCellValue(); // 第一列数据
            System.out.println(stringCellValue);
        }
    }


    @GetMapping("home")
    public String index() {
        return "index";
    }
}