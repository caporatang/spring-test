package com.example.springtest.unitTest.mockito;

import com.example.springtest.unitTest.mockito.helper.GreetingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;

/**
 * packageName : com.example.springtest.unitTest.mockito
 * fileName : CreateMOckExampleTest
 * author : taeil
 * date : 5/26/24
 * description :
 * =======================================================
 * DATE          AUTHOR                      NOTE
 * -------------------------------------------------------
 * 5/26/24        taeil                   최초생성
 */
public class CreateMOckExampleTest {

    @Test
    void createMock() {
        GreetingService mocked = mock();
        assertInstanceOf(GreetingService.class, mocked);
    }

    @Test
    void createMock2() {
        var mocked = mock(GreetingService.class);
        assertInstanceOf(GreetingService.class, mocked);
    }
}