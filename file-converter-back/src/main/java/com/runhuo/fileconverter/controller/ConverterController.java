package com.runhuo.fileconverter.controller;

import com.runhuo.fileconverter.service.ConverterService;
import com.runhuo.fileconverter.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * created by IntelliJ IDEA 2022.2.2
 *
 * @author run huo
 * @date 2022/10/3 14:18
 */
@RestController
@RequestMapping("converter")
public class ConverterController {
    @Autowired
    private ConverterService converterService;

    /**
     * pdf转word
     *
     * @param file
     * @param response
     * @return
     * @throws IOException
     */
    @PostMapping("/pdfToWord")
    public Result pdfToWord(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception {
        return converterService.pdfToWord(file, response);
    }
}
