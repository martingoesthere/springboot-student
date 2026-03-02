package com.example.demo.post;

import com.example.demo.post.dto.PostResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public PostResponseDto toResponse(Post post) {
        PostResponseDto response = new PostResponseDto();
        response.setId(post.getId());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setUserId(post.getUser().getId());
        response.setUserName(post.getUser().getName());
        response.setCategoryId(post.getCategory().getId());
        response.setCategoryName(post.getCategory().getName());
        return response;
    }
}
