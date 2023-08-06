package com.hossein.Store.Photo.Java;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class PhotoController {
    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "Hossein", null));
        put("2", new Photo("2", "Ali", null));
    }};

    @GetMapping("/")
    public String hello() {
        return "Hello Hossein On Fire!";
    }

    @GetMapping("/photo")
    public Collection<Photo> getPhoto() {
        return db.values();
    }

    @GetMapping("/photo/{id}")
    public Photo getPhoto(@PathVariable String id) {
        Photo photo = db.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photo/{id}")
    public void deletePhoto(@PathVariable String id) {
        Photo photo = db.remove(id);
        if(photo == null) throw new ResponseStatusException((HttpStatus.NOT_FOUND));
    }

    @PostMapping("/photo")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(file.getOriginalFilename());
        photo.setData(file.getBytes());
        db.put(photo.getId(), photo);
        return photo;
    }
}
