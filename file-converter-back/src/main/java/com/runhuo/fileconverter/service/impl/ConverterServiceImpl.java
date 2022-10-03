package com.runhuo.fileconverter.service.impl;

import com.runhuo.fileconverter.service.ConverterService;
import com.runhuo.fileconverter.utils.ConverterUtils;
import com.runhuo.fileconverter.vo.Result;
import com.spire.pdf.FileFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * created by IntelliJ IDEA 2022.2.2
 *
 * @author run huo
 * @date 2022/10/3 14:20
 */
@Service
public class ConverterServiceImpl implements ConverterService {
    @Autowired
    private ConverterUtils converterUtils;

    @Override
    public Result pdfToWord(MultipartFile file, HttpServletResponse response) throws Exception {
        List<String> docxList = converterUtils.fileConverter(file, com.spire.pdf.FileFormat.DOCX, "docx");
        String downloadLink = null;
        if (docxList.size() == 1) {
            downloadLink = docxList.get(0);
        } else {
            downloadLink = converterUtils.merger(docxList, "docx");
        }
        return Result.success(downloadLink);
    }
}
//@Service
//public class ConverterServiceImpl implements ConverterService {
//    @Autowired
//    private ConverterUtils converterUtils;
//
//    @Override
//    public void pdfToWord(MultipartFile file, HttpServletResponse response) throws Exception {
//
//        OutputStream outputStream = response.getOutputStream();
//        //加载文件
//        PdfDocument pdfDocument=new PdfDocument(file.getBytes());
//        //获取页数
//        int pageCount=pdfDocument.getPages().getCount();
//        if (pageCount>3){
//            converterUtils.splitDocument(pdfDocument,outputStream,pageCount,FileFormat.DOCX);
//        }
//        else {
//            converterUtils.convertDocument(pdfDocument,outputStream,FileFormat.DOCX);
//        }
//
//




//
//
//
//
//        List<String> docxList = converterUtils.fileConverter(file, FileFormat.DOCX, "docx",response);
//        String downloadLink = null;
//        if (docxList.size() == 1) {
//            downloadLink = docxList.get(0);
//        } else {
//            converterUtils.merger(docxList, "docx",response);
//        }
