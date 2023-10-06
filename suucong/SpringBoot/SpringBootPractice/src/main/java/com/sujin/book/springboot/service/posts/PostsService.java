package com.sujin.book.springboot.service.posts;

import com.sujin.book.springboot.domain.posts.Posts;
import com.sujin.book.springboot.domain.posts.PostsRepository;
import com.sujin.book.springboot.web.dto.PostsListResponseDto;
import com.sujin.book.springboot.web.dto.PostsResponseDto;
import com.sujin.book.springboot.web.dto.PostsSaveRequestDto;
import com.sujin.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // readOnly = true를 주면 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선.
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                // .map(posts -> new PostsListResponseDto(posts)) 와 같다.
                // postsRepository의 결과로 넘어온 Posts의 Stream을 map을 통해
                // PostsListResponseDto로 변환 -> List로 반환하는 클래스
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));

        postsRepository.delete(posts);
    }
}
