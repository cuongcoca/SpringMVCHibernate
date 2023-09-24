package com.coca.controller.admin;

import com.coca.dao.CommonDAO;
import com.coca.model.ImageUpload;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.List;

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
    public ResponseEntity search(){
        List<ImageUpload> list = commonDAO.getAll(ImageUpload.class);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        ImageUpload uploadFile = new ImageUpload();

        if (!file.isEmpty()) {
            boolean checkUploadSuccess = false;
            String fileName = String.format("%s_%s", new Date().getTime(), file.getOriginalFilename());
            try {
                byte[] bytes = file.getBytes();

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
//                stream.write(bytes);
                stream.write(imageBytes);
                stream.close();

                uploadFile.setBase64Img(imageBase64);
                checkUploadSuccess = true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(checkUploadSuccess){
                uploadFile.setFileName(file.getOriginalFilename());
                uploadFile.setUrl(pathFile + fileName);
                uploadFile.setUserId(1L);
                uploadFile.setProductId(1L);
                uploadFile.setGenDate(new Date());

                commonDAO.save(uploadFile);
            }
        }
        return "upload/index";
    }

}
