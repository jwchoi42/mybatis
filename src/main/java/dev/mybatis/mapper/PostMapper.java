package dev.mybatis.mapper;

import dev.mybatis.dto.PostDto;
import dev.mybatis.domain.Post;
import dev.mybatis.dto.PostSearchCondition;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {

    List<Post> findAll(PostSearchCondition postSearchCondition);

    // @Select("select * from posts where id=#{id}")
    Optional<Post> findById(Long id);

    /*

     */

    // @Update("update posts set title = #{postDto.title}, content = #{postDto.content}, writer = #{postDto.writer} where id = #{id}")
    void update(@Param("id") Long id, @Param("postDto") PostDto postDto);

    // @Insert("insert into posts (title, content, writer) values (#{title}, #{content}, #{writer})")
    // @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Post post);

    // @Delete("delete from posts where id = #{id}")
    void deleteById(Long id);

}
