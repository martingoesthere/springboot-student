package com.example.demo.post;

import com.example.demo.post.dto.PostRequestDto;
import com.example.demo.post.dto.PostResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser createUser(@RequestBody AppUser user) {
        return postService.createUser(user);
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category) {
        return postService.createCategory(category);
    }

    @PostMapping("/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDto createPost(@RequestBody PostRequestDto request) {
        return postService.createPost(request);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getPosts(@RequestParam(required = false) Long categoryId,
                                          @RequestParam(required = false) Long userId) {
        if (categoryId != null) {
            return postService.findByCategory(categoryId);
        }
        if (userId != null) {
            return postService.findAllByUser(userId);
        }
        return postService.getAllPosts();
    }

    @GetMapping("/posts/{id}")
    public PostResponseDto getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }
}
