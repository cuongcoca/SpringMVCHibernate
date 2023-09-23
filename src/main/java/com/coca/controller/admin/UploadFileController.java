package com.coca.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/upload-file")
public class UploadFileController {
    @RequestMapping("/index.html")
    public String index() {
        return "upload/index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                File dir = new File("D:\\1\\");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File("D:\\1\\" + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "upload/index";
    }

}
