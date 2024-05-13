package com.GuruDev.Blog.Application.Services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String UploadImage(String path, MultipartFile file) throws IOException;

    InputStream getContent(String path, String fileName) throws FileNotFoundException;

}
