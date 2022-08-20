package com.admiralxy.cinema.repositories;

import com.admiralxy.cinema.models.Image;
import com.admiralxy.cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ImageRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ImageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(User user, Image image) {
        this.jdbcTemplate.update(String.format("INSERT INTO images (name, original_name, size, mime) VALUES ('%s', '%s', '%s', '%s')",
                image.getName(),
                image.getOriginalName(),
                image.getSize(),
                image.getMime()
        ));
        this.jdbcTemplate.update(String.format("UPDATE users SET image = '%s' WHERE email = '%s'", image.getName(), user.getEmail()));
    }

    public List<Image> findAll() {
        try {
            return jdbcTemplate.query("SELECT * FROM images", (rs, rowNum) -> {
                Image c = new Image();
                c.setName(rs.getString(2));
                c.setOriginalName(rs.getString(3));
                c.setSize(rs.getString(4));
                c.setMime(rs.getString(5));
                return c;
            });
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
