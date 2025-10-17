package dev.mybatis.dto;

import lombok.Builder;

@Builder
public class PostDto {

    private String title;
    private String content;
    private String writer;

}
