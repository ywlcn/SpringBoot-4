# SpringBoot 4.0

https://spring.io/blog/2025/11/20/spring-boot-4-0-0-available-now

## 1. 変更点&新機能

### 1.1 依存関係

|                     | SpringBoot 3.x                                       | SpringBoot 4.x                                         |
| ------------------- | ---------------------------------------------------- | ------------------------------------------------------ |
| リリース時期        | 2022/11                                              | 2025/11                                                |
| JDK                 | Java 17 Baseline <br>Java 19 Support                 | Java 17 Baseline <br/>Java 25 Support                  |
| Tomcat              | 10.x                                                 | 11.x                                                   |
| Springframework     | Spring Framework 6                                   | Spring Framework 7                                     |
| jakarta.servlet-api | 6.0                                                  | 6.1                                                    |
| Jakarta             | Support for Jakarta EE 10 with an EE 9 baseline      | Jakarta EE 11 baseline                                 |
| Kafka               | 3.x                                                  | 4.x                                                    |
| gRPC                | -                                                    | ※                                                      |
| JUnit               | 5.x                                                  | 6.x                                                    |
| jackson             | com.fasterxml.jackson.core:jackson-core:2.x          | tools.jackson.core:jackson-core:3.x                    |
| コンパイルツール    | Maven 3.6.3 以上<br>Gradle 7.x (7.5 以降) および 8.x | Maven 3.6.3 以上<br>Gradle 8.x (8.14 or later) and 9.x |

https://spring.io/blog/2022/11/24/spring-boot-3-0-goes-ga

https://spring.io/blog/2025/11/20/spring-boot-4-0-0-available-now

※ SpringGrpc 1.0.1  2026/1  Boot-startも含めています。　　https://github.com/spring-projects/spring-grpc

```
Update Spring Boot to 4.0.1 by @onobc in #338
Update Spring Framework to 7.0.2 by @onobc in #338
Update Spring Security to 7.0.2 by @onobc in #338
Update Micrometer to 1.16.1 by @onobc in #338
Update Netty to 4.2.9.Final by @onobc in #338
Update io.grpc:grpc-bom to 1.77.1 by @onobc in #338
Update protobuf-java to 4.33.2 by @onobc in #338
Update proto-google-common-protos to 2.63.2 by @onobc in #338
```

https://spring.pleiades.io/spring-grpc/reference/

### 1.2 Bean定義方式追加

https://docs.spring.io/spring-framework/reference/core/beans/java/programmatic-bean-registration.html

- Configuration

  ```java
  @Configuration
  @Import(MyBeanRegistrar.class)
  class MyConfiguration {
  }
  ```

- 定義体

  ```java
  class MyBeanRegistrar implements BeanRegistrar {
  
  	@Override
  	public void register(BeanRegistry registry, Environment env) {
  		registry.registerBean("foo", Foo.class);
  		registry.registerBean("bar", Bar.class, spec -> spec
  				.prototype()
  				.lazyInit()
  				.description("Custom description")
  				.supplier(context -> new Bar(context.bean(Foo.class))));
  		if (env.matchesProfiles("baz")) {
  			registry.registerBean(Baz.class, spec -> spec
  					.supplier(context -> new Baz("Hello World!")));
  		}
  		registry.registerBean(MyRepository.class);
  		registry.registerBean(RouterFunction.class, spec ->
  				spec.supplier(context -> router(context.bean(MyRepository.class))));
  	}
  
  	RouterFunction<ServerResponse> router(MyRepository myRepository) {
  		return RouterFunctions.route()
  				// ...
  				.build();
  	}
  
  }
  ```

- なぜ？
  - もっと制御できる
  - 条件によって登録するかどうか、以前のアノテーションでも可能ですが、やはりコーディングではやりやすい

- 優先順位？

### 1.3 APi Version

https://spring.io/blog/2025/09/16/api-versioning-in-spring

- 設定方法としては：
  - path-based mapping (e.g.\*/api/v1/hello* vs. \*/api/v2/hello*)
  - request-header-based (e.g. \*X-API-Version: 1* vs. \*X-API-Version: 2*)
  - query-parameter-based (e.g. \*/hello?version=1* vs. \*/hello?version=2*)
  - mediatype-header-based (e.g. \*Accept: application/json; version=1* vs. \*Accept: application/json; version=2*)

- サンプル例：path-based mapping

    - Client Support

      ```Java
      RestClient client = RestClient.builder()
            .baseUrl("http://localhost:8080")
            .apiVersionInserter(ApiVersionInserter.useHeader("API-Version"))
            .build();

      Account account = client.get().uri("/accounts/1")
            .apiVersion(1.1)
            .retrieve()
            .body(Account.class);
      ```
      
    - Server
    
      ```java
        @Configuration
        public class ApiConfig implements WebMvcConfigurer {
        
            @Override
            public void configureApiVersioning(ApiVersionConfigurer configurer) {
                configurer.usePathSegment(1);
            }
        
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                configurer.addPathPrefix("/api/v{version}", HandlerTypePredicate.forAnnotation(RestController.class));
            }
        
        }
      
      @Configuration
        public class WebConfiguration implements WebMvcConfigurer {
          @Override
          public void configureApiVersioning(ApiVersionConfigurer configurer) {
              configurer.useRequestHeader("API-Version");
          }
        }
      
        @HttpExchange("/accounts")
        public interface AccountService {
      
          @GetExchange(url = "/{id}", version = "1.1")
          Account getAccount(@PathVariable int id);
      
        }
      ```
    
      







### 1.3 webclient





https://techfeed.io/entries/691f8bf33837533862edf005



### RestTestClient の追加   ???

以下で新しい **RestTestClient** が利用可能になった。

- `@SpringBootTest`（MockMvc ベース）
- `@AutoConfigureMockMvc`
- 統合テスト（定義ポートまたはランダムポート）





### HTTP Service Clients の自動構成   ???

**HTTP Service Clients** を利用したインターフェース定義が自動構成でサポートされた。
Java インターフェースにアノテーションを付与するだけで実装が生成される。

```java
@HttpExchange(url = "https://echo.zuplo.io")
public interface EchoService {

    @PostExchange
    Map<?, ?> echo(@RequestBody Map<String, String> message);

}
```







### 1.X OpenTelemetry スターターの新設

`spring-boot-starter-opentelemetry` が新たに追加された。
OTLP 形式でのメトリクス・トレース送信、OpenTelemetry SDK の自動設定を行う。

```properties
org.springframework.boot:spring-boot-starter-opentelemetry
org.springframework.boot:spring-boot-opentelemetry
```



### 1.X JMS：新しい JmsClient API のサポート   ???

JMS の自動構成において、新しい **JmsClient API** のサポートが追加された。
既存の `JmsTemplate` などはそのまま利用できる。



## 2 gRPC

https://github.com/spring-projects/spring-grpc/tree/main

### 2.1 starter

- クライアント
  - spring-grpc-client-spring-boot-starter
- サーバ
  - spring-grpc-server-web-spring-boot-starter
    - spring.grpc.server.servlet.enabled=false　　　　　grpc-servlet-jakarta
    - Servlet Server　　https://docs.spring.io/spring-grpc/reference/server.html#_servlet_server
  - spring-grpc-server-spring-boot-starter
    - Netty Server　　　https://docs.spring.io/spring-grpc/reference/server.html#_netty_server
- All-in-one
  - spring-grpc-spring-boot-starter
    - spring-grpc-client-spring-boot-starter
    - spring-grpc-server-spring-boot-starter






```bat
$ grpcurl -plaintext localhost:8003 list
Simple
grpc.health.v1.Health
grpc.reflection.v1.ServerReflection

$ grpcurl -plaintext localhost:8003 list Simple
Simple.SayHello
Simple.StreamHello

$ grpcurl -plaintext localhost:8003 Simple.SayHello
{
  "message": "Hello ==\u003e "
}

$ grpcurl -plaintext -d "{\"name\":\"Java\"}" localhost:8003 Simple.SayHello
{
  "message": "Hello ==\u003e Java"
}

```



https://ik.am/entries/850



### 区別

[spring-grpc-server-web-spring-boot-starter](https://github.com/spring-projects/spring-grpc/tree/main/spring-grpc-server-web-spring-boot-starter)

[spring-grpc-server-spring-boot-starter](https://github.com/spring-projects/spring-grpc/tree/main/spring-grpc-server-spring-boot-starter)