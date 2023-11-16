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
import com.allways.domain.post.dto.PostFindAllResponse;
import com.allways.domain.post.dto.PostFindMainResponse;
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
	public Response findMainPost() {
		List<PostFindMainResponse> postFindMainResponse = postQueryService.findMainPosts();
		return Response.success(postFindMainResponse);
	}

	// 사용자가 작성한 게시글을 조회합니다.(관리자 페이지)
	@GetMapping("api/posts/{userSeq}")
	@ResponseStatus(HttpStatus.OK)
	public Response findAllPost(@PathVariable Long userSeq, @PageableDefault Pageable pageable) {
		Page<Post> posts = postQueryService.findAllPosts(userSeq, pageable);
		PostFindAllResponse postFindAllResponse = new PostFindAllResponse(posts);
		return Response.success(postFindAllResponse);
	}

	// 특정 게시글의 상세 정보를 조회합니다.
	// @GetMapping("api/posts/{user_id}")
	// @ResponseStatus(HttpStatus.OK)
	// public Response readPost() {
	// 	PostReadResponse postReadResponse = postQueryService.readPost();
	// 	return Response.success(postReadResponse);
	// }



}
