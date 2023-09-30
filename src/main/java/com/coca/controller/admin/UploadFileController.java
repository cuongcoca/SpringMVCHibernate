package com.coca.controller.admin;

import com.coca.dao.CommonDAO;
import com.coca.model.ImageUpload;
import com.coca.model.PagingResult;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

@Controller
@RequestMapping("/upload-file")
public class UploadFileController {
    private static String pathFile = "D:\\1\\";

    @Autowired
    private CommonDAO commonDAO;

    @RequestMapping("/index.html")
    public String index() {
        return "upload/index";
    }

    @RequestMapping("/search")
    @ResponseBody
    public ResponseEntity search(@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
                                 @RequestParam(value = "numberPerPage", required = false, defaultValue = "5") int numberPerPage){
        PagingResult pagingResult = new PagingResult();
        pagingResult.setPageNumber(pageNumber);
        pagingResult.setNumberPerPage(numberPerPage);

        pagingResult = commonDAO.getPage(pagingResult, ImageUpload.class);
        return new ResponseEntity(pagingResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        ImageUpload uploadFile = new ImageUpload();

        if (!file.isEmpty()) {
            String fileNameUpload = uploadFileBase64(pathFile, file);

            if(!fileNameUpload.equals("false")){
                uploadFile.setFileName(fileNameUpload);
                uploadFile.setUrl(pathFile + fileNameUpload);
                uploadFile.setUserId(1L);
                uploadFile.setProductId(1L);
                uploadFile.setGenDate(new Date());

                commonDAO.save(uploadFile);
            }
        }
        return "redirect:/upload-file/index.html";
    }

    @RequestMapping(value = "/dowload/{file_name:.+}", method = RequestMethod.GET)
    @ResponseBody
    public void dowloadFile(@PathVariable("file_name") String fileName, HttpServletResponse response) {
        try {
            File file = new File(pathFile + fileName);
            byte[] data = FileUtils.readFileToByteArray(file);
            // Thiết lập thông tin trả về
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename=" + file.getName());
            response.setContentLength(data.length);
            InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "/files/{file_name:.+}", method = RequestMethod.GET)
    @ResponseBody
    public FileSystemResource getFile(@PathVariable("file_name") String fileName) {
        return new FileSystemResource(pathFile + fileName);
    }

    private String uploadFileBase64(String pathFile, MultipartFile file){
        String fileName = String.format("%s_%s", new Date().getTime(), file.getOriginalFilename());
        try {
            byte[] image = Base64.encodeBase64(file.getBytes());
            String imageBase64 = new String(image);
            byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(imageBase64);

            // Creating the directory to store file
            File dir = new File(pathFile);
            if (!dir.exists())
                dir.mkdirs();

            // Create the file on server
            File serverFile = new File(pathFile + fileName);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(imageBytes);
            stream.close();

            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    private String uploadFileDefault(String pathFile, MultipartFile file){
        String fileName = String.format("%s_%s", new Date().getTime(), file.getOriginalFilename());
        try {
            byte[] bytes = file.getBytes();

            // Creating the directory to store file
            File dir = new File(pathFile);
            if (!dir.exists())
                dir.mkdirs();

            // Create the file on server
            File serverFile = new File(pathFile + fileName);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            return pathFile + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "false";
    }

    private String getStringBase64File(String urlFile){
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(urlFile));
            byte[] encodedByte = Base64.encodeBase64(fileContent);
            return new String(encodedByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
