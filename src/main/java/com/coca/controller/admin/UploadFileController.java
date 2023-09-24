package com.coca.controller.admin;

import com.coca.dao.CommonDAO;
import com.coca.model.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
        if (!file.isEmpty()) {
            boolean checkUploadSuccess = false;
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                File dir = new File(pathFile);
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(pathFile + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                checkUploadSuccess = true;
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(checkUploadSuccess){
                ImageUpload uploadFile = new ImageUpload();
                uploadFile.setFileName(file.getOriginalFilename());
                uploadFile.setUrl(pathFile + file.getOriginalFilename());
                uploadFile.setUserID(1L);
                uploadFile.setProductId(1L);
                uploadFile.setGenDate(new Date());

                commonDAO.save(uploadFile);
            }
        }
        return "upload/index";
    }

}
