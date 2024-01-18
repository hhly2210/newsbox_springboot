package com.tintucspringboot.tintuc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class FileManager {

    @Autowired
    private ResourceLoader resourceLoader;

    public String saveFile(MultipartFile file) throws IOException {

        // Đường dẫn đến thư mục image trong resources
        Path imageFolderPath = Path.of("D:\\up");
   
        String newName = UUID.randomUUID() + file.getOriginalFilename().substring(
                file.getOriginalFilename().lastIndexOf(".")
        );
        // Đường dẫn đến file trong thư mục image
        Path filePath = imageFolderPath.resolve(newName);

        // Lưu file vào thư mục image
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return newName;
    }

    public Resource loadFile(String filename) throws IOException {
        Path imageFolderPath =  Path.of("D:\\up");
        // Đường dẫn đến file trong thư mục image
        Path filePath = imageFolderPath.resolve(filename);
        // Tạo một đối tượng Resource từ đường dẫn file
        return resourceLoader.getResource("file:" + filePath);
    }
}
