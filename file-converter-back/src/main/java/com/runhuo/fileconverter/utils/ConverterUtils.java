package com.runhuo.fileconverter.utils;

import com.spire.doc.Document;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.graphics.PdfMargins;
import com.spire.pdf.widget.PdfPageCollection;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * created by IntelliJ IDEA 2022.2.2
 *
 * @author run huo
 * @date 2022/10/3 14:20
 */
@Component
public class ConverterUtils {

    private String filePath = null;


    //保存pdf拆分后的文档路径
    private List<String> files = new ArrayList<>();

    public List<String> fileConverter(MultipartFile file, FileFormat type, String fileType) throws IOException {
        if (filePath == null) {
//            filePath = ResourceUtils.getURL("classpath:").getPath().substring(1) + "/document/";
            filePath = "src/main/resources/static/document/";
        }
        //加载文件
        PdfDocument pdfDocument = new PdfDocument();
        pdfDocument.loadFromBytes(file.getBytes());
        //切割文件
        PdfPageCollection pages = pdfDocument.getPages();
        //获取页数
        int count = pages.getCount();
        System.out.println(count);
        PdfDocument pdfDocument1 = new PdfDocument();
        PdfPageBase pageBase = null;
        String uri = UUID.randomUUID().toString();
        if (count > 3) {
            for (int i = 0; i < count; i++) {
                pageBase = pdfDocument1.getPages().add(pdfDocument.getPages().get(i).getSize(), new PdfMargins(0));
                pdfDocument.getPages().get(i).createTemplate().draw(pageBase, new Point2D.Float(0, 0));
                System.out.println(i + "---------->" + pdfDocument1.getPages().getCount());
                //每四页分一次
                if (pdfDocument1.getPages().getCount() == 3 || (i + 1) == count) {
                    pdfDocument1.saveToFile(filePath + uri + i + "." + fileType, type);
                    files.add(filePath + uri + i + "." + fileType);
                    pdfDocument1 = new PdfDocument();
                }
            }
        } else {
            pdfDocument.saveToFile(filePath + uri + "." + fileType, type);
            files.add(filePath + uri + "." + fileType);

        }
        return files;
    }

    /**
     * 合并所有子文件
     *
     * @param fileLink
     * @return
     */
    public String merger(List<String> fileLink, String fileType) throws Exception {
        String download = filePath + UUID.randomUUID() + "." + fileType;
        File file = new File(download);
        if (!file.exists()) {
            file.createNewFile();
        }
        Document document = new Document(fileLink.get(0));
        for (int i = 1; i < fileLink.size(); i++) {
            document.insertTextFromFile(fileLink.get(i), com.spire.doc.FileFormat.Docm_2013);
        }
        document.saveToFile(download, com.spire.doc.FileFormat.Docx_2013);
        document.close();
        return download;
    }

}

//    private String filePath = null;
//
//
//    //保存pdf拆分后的文档路径
//    private List<String> files = new ArrayList<>();
//
//    public List<String> fileConverter(MultipartFile file, FileFormat type, String fileType, HttpServletResponse response) throws IOException {
//        if (filePath == null) {
////            filePath = ResourceUtils.getURL("classpath:").getPath().substring(1) + "/document/";
//            filePath = "src/main/resources/static/document/";
//        }
//        //加载文件
//        PdfDocument pdfDocument = new PdfDocument();
//        pdfDocument.loadFromBytes(file.getBytes());
//        //切割文件
//        PdfPageCollection pages = pdfDocument.getPages();
//        //获取页数
//        int count = pages.getCount();
//        System.out.println(count);
//        PdfDocument pdfDocument1 = new PdfDocument();
//        PdfPageBase pageBase = null;
//        String uri = UUID.randomUUID().toString();
//        if (count > 3) {
//            for (int i = 0; i < count; i++) {
//                pageBase = pdfDocument1.getPages().add(pdfDocument.getPages().get(i).getSize(), new PdfMargins(0));
//                pdfDocument.getPages().get(i).createTemplate().draw(pageBase, new Point2D.Float(0, 0));
//                System.out.println(i + "---------->" + pdfDocument1.getPages().getCount());
//                //每四页分一次
//                if (pdfDocument1.getPages().getCount() == 3 || (i + 1) == count) {
//                    pdfDocument1.saveToFile(filePath + uri + i + "." + fileType, type);
//                    files.add(filePath + uri + i + "." + fileType);
//                    pdfDocument1 = new PdfDocument();
//                }
//            }
//        } else {
//            OutputStream outputStream = response.getOutputStream();
//            pdfDocument.saveToStream(outputStream, type);
//            files.add(filePath + uri + "." + fileType);
//            outputStream.flush();
//        }
//        pdfDocument1.close();
//        pdfDocument.close();
//        return files;
//    }
//
//    /**
//     * 合并所有子文件
//     *
//     * @param fileLink
//     * @return
//     */
//    public void merger(List<String> fileLink, String fileType, HttpServletResponse response) throws Exception {
//        ServletOutputStream outputStream = response.getOutputStream();
//        String download = filePath + UUID.randomUUID() + "." + fileType;
//        File file = new File(download);
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        Document document = new Document(fileLink.get(0));
//        for (int i = 1; i < fileLink.size(); i++) {
//            document.insertTextFromFile(fileLink.get(i), com.spire.doc.FileFormat.Docm_2013);
//        }
//        document.saveToFile(outputStream, com.spire.doc.FileFormat.Docx_2013);
//        document.close();
//        outputStream.flush();
//    }
//

//    /**
//     * 当页数小于等于3时，直接转换文档
//     *
//     * @param pdfDocument
//     * @param outputStream
//     * @param type
//     */
//    public void convertDocument(PdfDocument pdfDocument, OutputStream outputStream, FileFormat type) {
//        try {
//            pdfDocument.saveToStream(outputStream, type);
//            outputStream.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 当页数大于3时,拆分文档
//     *
//     * @param pdfDocument
//     * @param pageCount
//     * @param type
//     * @return
//     */
//    public void splitDocument(PdfDocument pdfDocument, OutputStream outputStream, int pageCount, FileFormat type) {
//        PdfDocument pdfDocument1 = new PdfDocument();
//        PdfPageBase pageBase = null;
//        try {
//            for (int i = 0; i < pageCount; i++) {
//                pageBase = pdfDocument1.getPages().add(pdfDocument.getPages().get(i).getSize(), new PdfMargins(0));
//                pdfDocument.getPages().get(i).createTemplate().draw(pageBase, new Point2D.Float(0, 0));
//                System.out.println(i + "---------->" + pdfDocument1.getPages().getCount());
//                //每四页分一次
//                if (pdfDocument1.getPages().getCount() == 3 || (i + 1) == pageCount) {
//                    pdfDocument1.saveToStream(outputStream, type);
//                    pdfDocument1 = new PdfDocument();
//                    outputStream.flush();
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            pdfDocument1.close();
//        }
//    }
//}
//

