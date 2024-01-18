package com.tintucspringboot.tintuc.controller.admin;

import com.tintucspringboot.tintuc.utils.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class FileController {

    @Autowired
    private FileManager fileManager;

    @GetMapping("/file/{filename}")
    public ResponseEntity<Resource> download(@PathVariable String filename) throws IOException {
        String contentType = "image/" + filename.substring(filename.lastIndexOf(".") + 1); // Bạn cũng có thể sử dụng định dạng thực tế của file

        // Thiết lập các HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        return ResponseEntity.ok().headers(headers).body(fileManager.loadFile(filename));
    }
}
