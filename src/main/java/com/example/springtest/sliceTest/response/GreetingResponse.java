package com.example.springtest.sliceTest.response;

import lombok.Data;

/**
 * packageName : com.example.springtest.sliceTest.response
 * fileName : GreetingResponse
 * author : taeil
 * date : 6/12/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 6/12/24        taeil                   최초생성
 */
@Data
public class GreetingResponse {
    private final String message;
    private final Long age;
    private final String who;
}