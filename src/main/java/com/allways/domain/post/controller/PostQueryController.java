package com.allways.domain.post.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.allways.common.response.Response;
import com.allways.domain.post.dto.PostCardResponse;
import com.allways.domain.post.dto.PostDetailResponse;
import com.allways.domain.post.dto.PostMainResponse;
import com.allways.domain.post.entity.Post;
import com.allways.domain.post.service.PostQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostQueryController {

	private final PostQueryService postQueryService;

	//블로그 메인 게시글 조회 API
	//최신순으로 10개 데이터를 조회한다
	@GetMapping("api/posts/main")
	@ResponseStatus(HttpStatus.OK)
	public Response findMainPosts() {
		List<PostCardResponse> postCardResponse = postQueryService.findMainPosts();
		return Response.success(postCardResponse);
	}

	//사용자가 작성한 게시글을 조회합니다.(관리자 페이지)
	@GetMapping("api/posts/user/{userSeq}")
	@ResponseStatus(HttpStatus.OK)
	public Response findPostsByUser(@PathVariable Long userSeq, @PageableDefault Pageable pageable) {
		Page<Post> posts = postQueryService.findPostsByUser(userSeq, pageable);

		return Response.success(posts);
	}

	//특정 게시글의 상세 정보를 조회합니다.
	@GetMapping("api/posts/{postSeq}")
	@ResponseStatus(HttpStatus.OK)
	public Response readPostDetail(@PathVariable Long postSeq) {
		PostDetailResponse postDetailResponse = postQueryService.readPostDetail(postSeq);
		return Response.success(postDetailResponse);
	}


	//특정 사용자의 특정 카테고리 게시글을 보여줍니다.
	@GetMapping("api/posts/{userSeq}/{categorySeq}")
	@ResponseStatus(HttpStatus.OK)
	public Response readPostsByCategory(@PathVariable Long userSeq, @PathVariable Long categorySeq, @PageableDefault Pageable pageable) {
		Page<Post> posts = postQueryService.readPostsByCategory(userSeq, categorySeq, pageable);

		return Response.success(posts);
	}



}
