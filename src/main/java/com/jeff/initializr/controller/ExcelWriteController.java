package com.jeff.initializr.controller;



import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.jeff.initializr.service.ExcelService;
import com.jeff.initializr.vo.UserEntity;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.excel.EasyExcel;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/excel")
public class ExcelWriteController{
    @Autowired
    private ExcelService excelService;

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


    /**
     * 测试写入Excel文件
     *
     * @param response
     * @throws IOException
     */
    @GetMapping("/download")
    public void doDownLoad(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("第一个文件", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), UserEntity.class).sheet("模板").doWrite(getData());
    }

    /**
     * 构造假数据，实际上应该从数据库查出来
     *
     * @return List<UserEntity>
     */
    private List<UserEntity> getData(){
        List<UserEntity> users = new ArrayList<UserEntity>();
        for (int i = 1; i <= 9; i++) {
            UserEntity user = new UserEntity();
            user.setBirthday(new Date());
            user.setName("user_" + i);
            user.setSalary(1.285 * i);
            user.setTelphone("1888888888" + i);
            users.add(user);
        }
        return users;
    }
}