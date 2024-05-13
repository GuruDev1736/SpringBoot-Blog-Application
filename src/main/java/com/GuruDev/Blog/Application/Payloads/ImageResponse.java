package com.GuruDev.Blog.Application.Payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {

    private String fileName;
    private String message;
    private boolean success;
    private PostDTO content;

}
