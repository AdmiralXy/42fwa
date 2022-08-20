package com.admiralxy.cinema.services;

import com.admiralxy.cinema.models.Image;
import com.admiralxy.cinema.models.User;
import com.admiralxy.cinema.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void save(User user, Image image) {
        this.imageRepository.save(user, image);
    }
}
