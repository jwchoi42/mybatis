package dev.mybatis.repository;

import dev.mybatis.dto.PostDto;
import dev.mybatis.domain.Post;
import dev.mybatis.dto.PostSearchCondition;
import dev.mybatis.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisPostRepository implements PostRepository {

    private final PostMapper postMapper;

    @Override
    public List<Post> findAll(PostSearchCondition postSearchCondition) {
        return postMapper.findAll(postSearchCondition);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postMapper.findById(id);
    }

    //

    @Override
    public void update(Long id, PostDto postDto) {
        postMapper.update(id, postDto);
    }

    @Override
    public Post save(Post post) {
        postMapper.save(post);
        return post;
    }

    @Override
    public void deleteById(Long id) {
        postMapper.deleteById(id);
    }

}
