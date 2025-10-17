package dev.mybatis.repository;

import dev.mybatis.dto.PostDto;
import dev.mybatis.domain.Post;
import dev.mybatis.dto.PostSearchCondition;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    List<Post> findAll(PostSearchCondition postSearchCondition);

    Optional<Post> findById(Long id);

    //

    void update(Long id, PostDto postDto);

    Post save(Post post);

    void deleteById(Long id);

}
