package com.allways.domain.post.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.allways.common.response.Response;
import com.allways.domain.post.dto.MngtPostResponse;
import com.allways.domain.post.dto.PostCardResponse;
import com.allways.domain.post.dto.PostResponse;
import com.allways.domain.post.service.PostQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class PostQueryController {

	private final PostQueryService postQueryService;

	//최신순으로 10개 데이터를 조회합니다
	@GetMapping("api/posts/main")
	@ResponseStatus(HttpStatus.OK)
	public Response readMainPosts() {
		List<PostCardResponse> postCardResponse = postQueryService.readMainPosts();
		return Response.success(postCardResponse);
	}

	// 사용자가 작성한 게시글을 조회합니다.(관리자 페이지)
	@GetMapping("api/posts/users/{userSeq}")
	@ResponseStatus(HttpStatus.OK)
	public Response readAllPosts(@PathVariable Long userSeq, @PageableDefault Pageable pageable) {
		Page<MngtPostResponse> postResponse = postQueryService.readAllPosts(userSeq, pageable);
		return Response.success(postResponse);
	}

	//특정 게시글의 상세 정보를 조회합니다.
	@GetMapping("api/posts/{postSeq}")
	@ResponseStatus(HttpStatus.OK)
	public Response readPost(@PathVariable Long postSeq) {
		PostResponse postResponse = postQueryService.readPost(postSeq);
		return Response.success(postResponse);
	}


	//특정 사용자의 특정 카테고리 게시글을 목록을 보여줍니다.
	@GetMapping("api/posts/{userSeq}/{categorySeq}")
	@ResponseStatus(HttpStatus.OK)
	public Response readPostsInCategory(@PathVariable Long userSeq, @PathVariable Long categorySeq, @PageableDefault Pageable pageable) {
		Page<PostCardResponse> postResponse = postQueryService.readPostsInCategory(userSeq, categorySeq, pageable);
		return Response.success(postResponse);
	}



}
