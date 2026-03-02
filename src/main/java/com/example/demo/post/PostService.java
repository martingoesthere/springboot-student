package com.example.demo.post;

import com.example.demo.common.EntityNotFoundException;
import com.example.demo.post.dto.PostRequestDto;
import com.example.demo.post.dto.PostResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final AppUserRepository appUserRepository;
    private final CategoryRepository categoryRepository;
    private final PostMapper postMapper;

    public PostService(PostRepository postRepository,
                       AppUserRepository appUserRepository,
                       CategoryRepository categoryRepository,
                       PostMapper postMapper) {
        this.postRepository = postRepository;
        this.appUserRepository = appUserRepository;
        this.categoryRepository = categoryRepository;
        this.postMapper = postMapper;
    }

    public AppUser createUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public PostResponseDto createPost(PostRequestDto request) {
        AppUser user = appUserRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User", request.getUserId()));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category", request.getCategoryId()));

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setUser(user);
        post.setCategory(category);

        return postMapper.toResponse(postRepository.save(post));
    }

    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll().stream().map(postMapper::toResponse).toList();
    }

    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post", id));
        return postMapper.toResponse(post);
    }

    public List<PostResponseDto> findByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category", categoryId));
        return postRepository.findByCategory(category).stream().map(postMapper::toResponse).toList();
    }

    public List<PostResponseDto> findAllByUser(Long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", userId));
        return postRepository.findAllByUser(user).stream().map(postMapper::toResponse).toList();
    }
}
