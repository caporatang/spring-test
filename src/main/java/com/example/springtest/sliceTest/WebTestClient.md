## Slice Test - spring-webflux

# WebTestClient
spring-webflux에서 통신객체로 사용되는 webclient와 유사하며, webClient에서 제공되는 메서드를 동일하게 사용할 수 있고 WebTestClient는 별도의 생성 메서드와 body, cookie, header, status 검증 메서드를 제공한다.  

## WebTestClient 생성
webTestController를 생성할 수 있는 방법은 총 5가지가 있다.  
1. bindToController : controller들을 제공하여 WebTestClient를 생성한다. 
2. bindToRouterFunction : RouterFunction들을 제공하여 WebTestClient를 생성한다.
3. bindToApplicationContext : ApplicationContext 내의 bean들을 기반으로 WebTestClient를 생성한다.
4. bindToWebHandler : WebHandler를 직접 제공하여 WebTestClient를 생성한다.
5. bindToServer : reactor netty client를 사용해서 떠있는 서버에 직접 연결한다.
````java
static ControllerSpec bindToController(Object... controllers) {
        return new DefaultControllerSpec(controllers);
    }

    static RouterFunctionSpec bindToRouterFunction(RouterFunction<?> routerFunction) {
        return new DefaultRouterFunctionSpec(routerFunction);
    }

    static MockServerSpec<?> bindToApplicationContext(ApplicationContext applicationContext) {
        return new ApplicationContextSpec(applicationContext);
    }

    static MockServerSpec<?> bindToWebHandler(WebHandler webHandler) {
        return new DefaultMockServerSpec(webHandler);
    }

    static Builder bindToServer() {
        return new DefaultWebTestClientBuilder();
    }

    static Builder bindToServer(ClientHttpConnector connector) {
        return new DefaultWebTestClientBuilder(connector);
    }
````  
  
## MockServerSpec ?
bindToApplicationContext와 bindToWebHandler를 호출했을때 반환되는 MockServerSpec은 webFilter, webSessionManager, MockServerConfigurer등을 지원하며 configureClient를 통해 WebTestClient의 Builder를 반환하거나 build를 호출하여 바로 WebTestClient를 생성한다.  
````java
public interface MockServerSpec<B extends MockServerSpec<B>> {
        <T extends B> T webFilter(WebFilter... filter);

        <T extends B> T webSessionManager(WebSessionManager sessionManager);

        <T extends B> T apply(MockServerConfigurer configurer);

        Builder configureClient();

        WebTestClient build();
    }
````

## ControllerSpec ? 
bindToController를 사용해서 WebTestClient를 생성했을 때 반환되는 객체다.  
controllerAdvice, contentRtpeResolver, corsMapping, pathMatching, argumentResolver, httpMessageCodec, formatter, validator, viewResolver등을 설정할 수 있고 MockServerSpec을 상속하여 build를 호출하여 WebTestClient를 생성하거나 configureClient로 다시 Builder로 나갈 수 있다.   
````java
public interface ControllerSpec extends MockServerSpec<ControllerSpec> {
        ControllerSpec controllerAdvice(Object... controllerAdvice);

        ControllerSpec contentTypeResolver(Consumer<RequestedContentTypeResolverBuilder> consumer);

        ControllerSpec corsMappings(Consumer<CorsRegistry> consumer);

        ControllerSpec pathMatching(Consumer<PathMatchConfigurer> consumer);

        ControllerSpec argumentResolvers(Consumer<ArgumentResolverConfigurer> configurer);

        ControllerSpec httpMessageCodecs(Consumer<ServerCodecConfigurer> configurer);

        ControllerSpec formatters(Consumer<FormatterRegistry> consumer);

        ControllerSpec validator(Validator validator);

        ControllerSpec viewResolvers(Consumer<ViewResolverRegistry> consumer);
    }
````

## bindToController 사용 예제
SpringExtension, ContextConfiguration으로 GreetingController와 Advice를 bean으로 등록하고, 
WebTestClient의 bindToController와 ControllerSpec을 활용하여 WebTestClient를 생성한다.  
````java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
                GreetingController.class,
                GreetingControllerAdvice.class
        }
)
public class CreateWebTestClientByControllerExampleTest {
        @MockBean
        GreetingService mockGreetingService;

        @Autowired
        GreetingController greetingController;

        @Autowired
        GreetingControllerAdvice greetingControllerAdvice;

        WebTestClient webTestClient;

        @BeforeEach
        void setup() {
                webTestClient = WebTestClient.bindToController(
                                greetingController
                        ).corsMappings(cors ->
                                cors.addMapping("/api/**"))
                        .controllerAdvice(greetingControllerAdvice)
                        .build();
        }

}
````

## bindToApplicationContext 사용 예제
bindToApplicationContext는 @EnableWebFlux를 사용해줘야한다.    
@EnableWebFlux를 사용하게 되면, DispatcherHanlder가 bean으로 등록되게 되고, DispatcherHandler가 등록이 되야 관련된 webFilter, exceptionHandler 등을 사용할 수 있기 때문이다.
````java
@EnableWebFlux
@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = {
                GreetingController.class,
                GreetingControllerAdvice.class
        }
)
public class CreateWebTestClientByApplicationContextExampleTest {
    @MockBean
    GreetingService mockGreetingService;

    @Autowired
    ApplicationContext applicationContext;

    WebTestClient webTestClient;

    @BeforeEach
    void setup() {
        webTestClient = WebTestClient.bindToApplicationContext(
                applicationContext
        ).build();
    }
}
````

## bindToServer 사용 예제
기존에 떠있는 서버에 연결하여 WebTestClient를 생성하는 방식이다.  
bindToServer의 반환값은 builder이기 떄문에, builder를 활용하여 baseUrl을 설정해서 build한다.
````java
@ExtendWith(SpringExtension.class)
public class CreateWebTestClientByServerExampleTest {

    WebTestClient webTestClient;

    @BeforeEach
    void setup() {
        WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080")
                .build();
    }
}
````

