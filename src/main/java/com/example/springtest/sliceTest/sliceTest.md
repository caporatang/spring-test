## Slice Test
Controller는 http 요청을 받아서 처리하는 레이어에 해당하며 프레젠테이션 레이어라 하고,   
Repository는 데이터베이스에 연결하여 데이터의 조회, 저장, 수정, 삭제 등의 CRUD를 수행하는 데이터 엑세스 레이어에 해당한다.
````java
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    UserR2dbcRepository userR2dbcRepository;

    @Test
    void when_find_by_id_returns_user_mono() {
        // given
        var user = new UserEntity(1L, "taeil", 20, "1", "1234");

        System.out.println(user);

        var userR2dbcRepository = mock(UserR2dbcRepository.class, Mockito.RETURNS_DEEP_STUBS);

        when(userR2dbcRepository.findByName(any()))
                .thenReturn(Mono.just(user));
        // when
        var resp = userR2dbcRepository.findByName("taeil");

        //then
        StepVerifier.create(resp)
                .assertNext(u -> {
                    System.out.println("test!");
                    assertEquals("taeil", u.getName());
                    assertEquals(20, u.getAge());
                })
                .verifyComplete();
    }
}
````
예시 코드처럼 http 혹은 데이터베이스 부분을 mocking 해서 테스트를 작성하는것은 의미가 없다. 왜냐하면 R2dbcRepository는 R2dbc를 사용했기 때문에 이미 검증된 DB를 가져다가 쓰는것이기 때문이다.  
이럴때 사용할 수 있는 기법이 **슬라이스 테스트** 이다.

스프링 프레임워크에서 제공하는 테스트 기능중에 하나이며, 하나의 레이어를 나눠서 테스트 한다는 이유에서 슬라이스 테스트라고 한다.  
슬라이스 테스트는 2가지 레이어로 나눌수있는데, 예를들면  
프레젠테이션 레이어 : @WebFluxTest
데이터 엑세스 레이어 : @DataR2dbcTest, @DataRedisTest, @DataMongoTest 가 있다.

