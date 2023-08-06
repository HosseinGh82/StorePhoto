package com.hossein.Store.Photo.Java;

import com.hossein.Store.Photo.Java.model.Photo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;

@RestController
public class PhotoController {

    private final PhotoService photoService;

    public  PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello Hossein On Fire!";
    }

    @GetMapping("/photo")
    public Collection<Photo> getPhoto() {
        return photoService.getPhotos();
    }

    @GetMapping("/photo/{id}")
    public Photo getPhoto(@PathVariable String id) {
        Photo photo = photoService.getPhoto(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photo/{id}")
    public void deletePhoto(@PathVariable String id) {
        Photo photo = photoService.removePhoto(id);
        if(photo == null) throw new ResponseStatusException((HttpStatus.NOT_FOUND));
    }

    @PostMapping("/photo")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return photoService.savePhoto(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }
}
