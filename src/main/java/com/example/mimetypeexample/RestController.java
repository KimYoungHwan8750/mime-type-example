package com.example.mimetypeexample;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    @GetMapping("/image")
    public ResponseEntity<byte[]> getImage(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_PNG);
        ClassPathResource classPathResource = new ClassPathResource("templates/image.png");
        try (InputStream inputStream = new FileInputStream(classPathResource.getFile())){
            byte[] data = inputStream.readAllBytes();
            inputStream.close();
            return new ResponseEntity<>(data, httpHeaders, HttpStatus.OK);
        } catch (IOException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
