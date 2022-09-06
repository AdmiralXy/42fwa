package com.admiralxy.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private String name;
    private String originalName;
    private String size;
    private String mime;
}
