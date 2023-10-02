package uz.bookshop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageUploadService {

    public void save(MultipartFile multipart) {
        try {
            Path path = Paths.get("D:/Programming/Projects/Experience projects/book-shop/src/main/resources/image/" + multipart.getOriginalFilename());
            Files.write(path, multipart.getBytes());
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }
            throw new RuntimeException(e.getMessage());
        }
    }
}
