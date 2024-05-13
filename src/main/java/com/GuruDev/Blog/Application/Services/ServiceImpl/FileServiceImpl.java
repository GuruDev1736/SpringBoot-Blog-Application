package com.GuruDev.Blog.Application.Services.ServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.GuruDev.Blog.Application.Services.FileService;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String UploadImage(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();
        String randomID = UUID.randomUUID().toString();
        String FileName = randomID.concat(name.substring(name.lastIndexOf(".")));

        // Full Path

        String filePath = path + File.separator + FileName;

        // Create the Folder if not created

        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }

        // file Copy
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return FileName;
    }

    @Override
    public InputStream getContent(String path, String fileName) throws FileNotFoundException {
        String fullPath = path + File.separator + fileName;
        InputStream is = new FileInputStream(fullPath);
        return is;
    }
}
