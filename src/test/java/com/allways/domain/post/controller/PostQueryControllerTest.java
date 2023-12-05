package com.allways.domain.post.controller;

import com.allways.common.response.Response;
import com.allways.domain.post.dto.PostResponse;
import com.allways.domain.post.entity.Post;
import com.allways.domain.post.service.PostQueryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PostQueryControllerTest {
    @Mock private PostQueryService postQueryService;
    @InjectMocks private PostQueryController postQueryController;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(postQueryController).build();
    }

//    @Test
//    void readMainPostsTest() throws Exception {
//        // When
//        mockMvc.perform(get("/api/post/main")
//                        .contentType("application/json"))
//                .andExpect(status().isOk());
//
//        // Then
//        verify(postQueryService).readMainPosts();
//    }

    // @Pageable로 인해 에러 발생;;
//    @Test
//    void readAllPostsTest() throws Exception {
//        // Given
//        int page = 0; // 페이지 번호
//        int size = 10; // 페이지 크기
//        Pageable pageable = PageRequest.of(page, size);
//
//        // When, Then
//        MvcResult result = mockMvc.perform(get("/api/post")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .header("userSeq", String.valueOf(1L))
//                        .param("page", String.valueOf(page))
//                        .param("size", String.valueOf(size)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        MockHttpServletRequest request = result.getRequest();
//        String userSeq = request.getHeader("userSeq");
//
//        verify(postQueryService).readAllPosts(Long.parseLong(userSeq), pageable);
//    }

//    @Test
//    void readPostTest() throws Exception {
//        // Given
//        Long postSeq = 1L;
//        Post post = new Post("", " ");
//        PostResponse response = new PostResponse();
//
//        // When, Then
//        mockMvc.perform(get("/api/post/{postSeq}", postSeq)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        verify(postQueryService).readPost(postSeq);
//    }


    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
