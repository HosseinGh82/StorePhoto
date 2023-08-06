package com.hossein.Store.Photo.Java.service;

import com.hossein.Store.Photo.Java.model.Photo;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Getter
public class PhotoService {

    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1", "Hossein", "png", null));
        put("2", new Photo("2", "Ali", "png", null));
    }};

    public Collection<Photo> getPhotos() {
        return db.values();
    }

    public Photo getPhoto(String id) {
        return db.get(id);
    }

    public Photo removePhoto(String id) {
        return db.remove(id);
    }

    public Photo savePhoto(String fileName, String fileContentType, byte[] data) {
        Photo photo = new Photo();
        photo.setId(UUID.randomUUID().toString());
        photo.setFileName(fileName);
        photo.setContentType(fileContentType);
        photo.setData(data);
        db.put(photo.getId(), photo);
        return photo;
    }

}
