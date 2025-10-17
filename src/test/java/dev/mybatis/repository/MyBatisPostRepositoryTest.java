package dev.mybatis.repository;

import dev.mybatis.domain.Post;
import dev.mybatis.dto.PostDto;
import dev.mybatis.dto.PostSearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@Transactional
@SpringBootTest
class MyBatisPostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    void test(List<Post> result, Post... posts) {
        assertThat(result).containsExactly(posts);
    }

    @Test
    void findAll() {

        // Given
        Post oldPost = new Post("oldTitle", "oldContent", "writer");
        Post newPost = new Post("newTitle", "newContent", "writer");
        Post postA = new Post("titleA", "contentA", "writerA");
        Post postB = new Post("titleB", "contentB", "writerB");

        oldPost = postRepository.save(oldPost);
        newPost = postRepository.save(newPost);
        postA = postRepository.save(postA);
        postB = postRepository.save(postB);

        // When - Then

        PostSearchCondition postSearchCondition = new PostSearchCondition();
        List<Post> posts = postRepository.findAll(postSearchCondition);
        log.info("posts {}", posts);
        test(posts, oldPost, newPost, postA, postB);

        //

        postSearchCondition.setContent("Content");
        posts = postRepository.findAll(postSearchCondition);
        log.info("posts {}", posts);

        test(posts, oldPost, newPost);

        //

        postSearchCondition.setContent("content");
        postSearchCondition.setWriter("writerA");
        posts = postRepository.findAll(postSearchCondition);
        log.info("posts {}", posts);

//        test(posts, postA);

    }

    @Test
    void findById() {

        // Given
        Post post = new Post("title", "content", "writer");
        Post savedPost = postRepository.save(post);

        // When
        Post foundPost = postRepository.findById(savedPost.getId())
                .orElseThrow(NoSuchElementException::new);

        // Then
        assertEquals(savedPost.getId(), foundPost.getId());

    }

    //

    @Test
    void update() {

        // Given
        Post post = new Post("title", "content", "writer");

        // When
        post = postRepository.save(post);

        PostDto postDto = PostDto.builder()
                .title("newTitle")
                .content("newContent")
                .writer("newWriter")
                .build();

        postRepository.update(post.getId(), postDto);

        // Then
        Optional<Post> optionalUpdatedPost = postRepository.findById(post.getId());
        assertThat(optionalUpdatedPost).isPresent();

        Post updatedPost = optionalUpdatedPost.get();
        assertEquals("newTitle", updatedPost.getTitle());
        assertEquals("newContent", updatedPost.getContent());
        assertEquals("newWriter", updatedPost.getWriter());

    }

    @Test
    void save() {

        // Given
        Post post = new Post("title", "content", "writer");
        Post savedPost = postRepository.save(post);

        // When
        Post foundPost = postRepository.findById(savedPost.getId())
                .orElseThrow(NoSuchElementException::new);

        // Then
        assertEquals(savedPost.getId(), foundPost.getId());

    }

    @Test
    void deleteById() {

        // Given
        Post post = new Post("title", "content", "writer");
        post = postRepository.save(post);

        // When
        postRepository.deleteById(post.getId());

        // Then
        Optional<Post> deletedPost = postRepository.findById(post.getId());
        assertThat(deletedPost).isEmpty();

    }

}