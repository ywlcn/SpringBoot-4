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
- カスタマイズ

```
ソースコード
```

|                          | **Path** | **Query** | **Header** | **Media Type** |
| ------------------------ | -------- | --------- | ---------- | -------------- |
| **可視性**               | 極高     | 高        | 低         | 極低           |
| **REST 標準**            | 低       | 中        | 高         | 極高           |
| **ブラウザからアクセス** | 極易     | 容易      | 難         | 極難           |

### 1.4 webclient

????







### 1.5 OpenTelemetry スターターの新設

`spring-boot-starter-opentelemetry` が新たに追加された。
OTLP 形式でのメトリクス・トレース送信、OpenTelemetry SDK の自動設定を行う。

```properties
org.springframework.boot:spring-boot-starter-opentelemetry
org.springframework.boot:spring-boot-opentelemetry
```



### 1.6 JMS：新しい JmsClient API のサポート

JMS の自動構成において、新しい **JmsClient API** のサポートが追加された。
既存の `JmsTemplate` などはそのまま利用できる。



## 2 gRPC

https://github.com/spring-projects/spring-grpc/tree/main

https://docs.spring.io/spring-grpc/reference/index.html

### 2.1 starter

- クライアント
  - spring-grpc-client-spring-boot-starter
- サーバ
  - spring-grpc-server-web-spring-boot-starter（共存）
    - spring.grpc.server.servlet.enabled=false　　　　　grpc-servlet-jakarta
    - Servlet Server　　https://docs.spring.io/spring-grpc/reference/server.html#_servlet_server
    - gRPC はServlet容器で実施される　　同じPortでHttp　grpcとも対応できる
  - spring-grpc-server-spring-boot-starter（独立）
    - Netty Server　　　https://docs.spring.io/spring-grpc/reference/server.html#_netty_server
    - gRPC-over-HTTP/2　のみ
    - `spring-boot-starter-web`，を入れると、Servlet Serverも起動する。（ 8080 ： REST，9090： gRPC）。
- All-in-one
  - spring-grpc-spring-boot-starter
    - spring-grpc-client-spring-boot-starter
    - spring-grpc-server-spring-boot-starter






```bat

```







https://github.com/spring-projects/spring-grpc/tree/main/spring-grpc-server-spring-boot-starter)





### 2.2 クライアント機能

#### 2.2.1 コンフィグ機能

- spring.grpc.client.channels.local.address=0.0.0.0:9090  　ほかいろいろな設定

  ```yaml
  spring:
    grpc:
      client:
        channels:
          local:
  #          health:
  #            enabled: true
  #            service-name: grpc.health.v1.Health
            address: "static://localhost:8003"
            negotiation-type: plaintext
  ```

  ```java
  @Bean
  SimpleGrpc.SimpleBlockingStub stub(GrpcChannelFactory channels) {
  	return SimpleGrpc.newBlockingStub(channels.createChannel("local"));
  }
  
  @Bean
  @Order(100)
  GrpcChannelBuilderCustomizer<NettyChannelBuilder> flowControlCustomizer() {
      return (name, builder) -> builder.flowControlWindow(1024 * 1024);
  }
  
  @Bean
  @Order(200)
  <T extends ManagedChannelBuilder<T>> GrpcChannelBuilderCustomizer<T> retryChannelCustomizer() {
      return (name, builder) -> builder.enableRetry().maxRetryAttempts(5);
  }
  ///////////////////////////////////////  
  @Bean
  SimpleGrpc.SimpleBlockingStub stub(GrpcChannelFactory channels) {
  	return SimpleGrpc.newBlockingStub(channels.createChannel("0.0.0.0:9090"));
  }
  ```

  ```java
  @Component
  public class HelloRepositoryImpl implements HelloRepository {
  
      @Autowired
      SimpleGrpc.SimpleBlockingStub stub;
  
      @Override
      public HelloRepositoryOutDto sayHello(HelloRepositoryInDto inDto) {
          HelloWorldProto.HelloRequest request = mapper.mapToProto(inDto);
          HelloWorldProto.HelloReply response = stub.sayHello(request);
          return mapper.mapToDto(response);
      }
  }
  ```

#### 2.2.2 インターセプト機能

- Global

  ```Java
  @Bean
  @Order(100)
  @GlobalClientInterceptor
  ClientInterceptor globalLoggingInterceptor() {
      return new LoggingInterceptor();
  }
  
  @Bean
  @Order(200)
  @GlobalClientInterceptor
  ClientInterceptor globalExtraThingsInterceptor() {
      return new ExtraThingsInterceptor();
  }
  ```

- Filtering

  Globalのインターセプトに対して、一部適用しない場合

  ```
  TODO
  ```

- Per-Channel

  特定のChannelのみ適用したい場合

  ```java
  @Bean
  SimpleGrpc.SimpleBlockingStub stub(GrpcChannelFactory channelFactory) {
      ClientInterceptor interceptor1 = getChannelInterceptor1();
      ClientInterceptor interceptor2 = getChannelInterceptor2();
      ChannelBuilderOptions options = ChannelBuilderOptions.defaults()
              .withInterceptors(List.of(interceptor1, interceptor2));
      ManagedChannel channel = channelFactory.createChannel("localhost", options);
      return SimpleGrpc.newBlockingStub(channel);
  }
  ```

#### 2.2.3 Observability

```properties
# HELP grpc_client_active_seconds  
# TYPE grpc_client_active_seconds summary
grpc_client_active_seconds_count{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="9090",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 0
grpc_client_active_seconds_sum{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="9090",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 0.0
# HELP grpc_client_active_seconds_max  
# TYPE grpc_client_active_seconds_max gauge
grpc_client_active_seconds_max{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="9090",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 0.0
# HELP grpc_client_received_total  
# TYPE grpc_client_received_total counter
grpc_client_received_total{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="9090",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 7.0
# HELP grpc_client_seconds  
# TYPE grpc_client_seconds summary
grpc_client_seconds_count{error="none",grpc_status_code="OK",net_peer_name="localhost",net_peer_port="9090",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 7
grpc_client_seconds_sum{error="none",grpc_status_code="OK",net_peer_name="localhost",net_peer_port="9090",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 0.5746595
# HELP grpc_client_seconds_max  
# TYPE grpc_client_seconds_max gauge
grpc_client_seconds_max{error="none",grpc_status_code="OK",net_peer_name="localhost",net_peer_port="9090",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 0.0154066
# HELP grpc_client_sent_total  
# TYPE grpc_client_sent_total counter
grpc_client_sent_total{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="9090",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 7.0
```

#### 2.2.4 Security

```

```

```

```



#### 2.2.5 Health

- ```properties
  spring:
    grpc:
      client:
        default-channel:
        health:
          enabled: true
        channels:
          one:
            health:
              enabled: true
              service-name: service-one
              
              
              
  grpcurl -plaintext -d '{"service": "Simple"}' localhost:9090 grpc.health.v1.Health/Check
  ```



### 2.3サーバ機能

#### 2.3.1 gRPCサーバ

- spring-grpc-server-web-spring-boot-starter（共存）
  - spring.grpc.server.servlet.enabled=false　　　　　grpc-servlet-jakarta
  - Servlet Server　　https://docs.spring.io/spring-grpc/reference/server.html#_servlet_server
  - gRPC はServlet容器で実施される　　同じPortでHttp　grpcとも対応できる
- spring-grpc-server-spring-boot-starter（独立）
  - Netty Server　　　https://docs.spring.io/spring-grpc/reference/server.html#_netty_server
  - gRPC-over-HTTP/2　のみ
  - `spring-boot-starter-web`，を入れると、Servlet Serverも起動する。（ 8080 ： REST，9090： gRPC）。

#### 2.2.2 インターセプト機能

- Global

  ```Java
  @Bean
  @Order(100)
  @GlobalServerInterceptor
  ServerInterceptor myGlobalLoggingInterceptor() {
      return new MyLoggingInterceptor();
  }
  ```

- Filtering

  Globalのインターセプトに対して、一部適用しない場合

  ```
  TODO
  ```

- Per-Channel

  特定のServiceのみ適用したい場合

  ```java
  @Bean
  MyServerInterceptor myServerInterceptor() {
      return new MyServerInterceptor();
  }
  
  @GrpcService(interceptors = MyServerInterceptor.class)
  BindableService myService() {
  	...
  }
  ```

#### 2.2.3 Reflection

```bash

$ grpcurl -plaintext localhost:9090 list
Simple
grpc.health.v1.Health
grpc.reflection.v1.ServerReflection

$ grpcurl -plaintext localhost:9090 list Simple
Simple.SayHello
Simple.StreamHello

$ grpcurl -plaintext localhost:9090 Simple.SayHello
{
  "message": "Hello ==\u003e "
}

$ grpcurl -plaintext -d "{\"name\":\"Java\"}" localhost:8003 Simple.SayHello
{
  "message": "Hello ==\u003e Java"
}

```

#### 2.2.4 Health

- ```properties
  spring.grpc.server.health.enabled=true
  ```

- ```yml
  spring:
    grpc:
      server:
        health:
          actuator:
            health-indicator-paths:
              - db
              - redis
  ```

  grpcのヘルスチェックはdbなどのチェックする

- grpcurl -plaintext localhost:9090 grpc.health.v1.Health.Check



#### 2.2.5 Observability

```json
# HELP grpc_server_active_seconds  
# TYPE grpc_server_active_seconds summary
grpc_server_active_seconds_count{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="8004",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 0
grpc_server_active_seconds_sum{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="8004",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 0.0
grpc_server_active_seconds_count{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="8004",rpc_method="ServerReflectionInfo",rpc_service="grpc.reflection.v1.ServerReflection",rpc_type="BIDI_STREAMING"} 0
grpc_server_active_seconds_sum{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="8004",rpc_method="ServerReflectionInfo",rpc_service="grpc.reflection.v1.ServerReflection",rpc_type="BIDI_STREAMING"} 0.0
# HELP grpc_server_active_seconds_max  
# TYPE grpc_server_active_seconds_max gauge
grpc_server_active_seconds_max{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="8004",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 0.0
grpc_server_active_seconds_max{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="8004",rpc_method="ServerReflectionInfo",rpc_service="grpc.reflection.v1.ServerReflection",rpc_type="BIDI_STREAMING"} 0.0
# HELP grpc_server_received_total  
# TYPE grpc_server_received_total counter
grpc_server_received_total{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="8004",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 1.0
grpc_server_received_total{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="8004",rpc_method="ServerReflectionInfo",rpc_service="grpc.reflection.v1.ServerReflection",rpc_type="BIDI_STREAMING"} 2.0
# HELP grpc_server_seconds  
# TYPE grpc_server_seconds summary
grpc_server_seconds_count{error="none",grpc_status_code="OK",net_peer_name="localhost",net_peer_port="8004",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 1
grpc_server_seconds_sum{error="none",grpc_status_code="OK",net_peer_name="localhost",net_peer_port="8004",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 0.0037934
grpc_server_seconds_count{error="none",grpc_status_code="OK",net_peer_name="localhost",net_peer_port="8004",rpc_method="ServerReflectionInfo",rpc_service="grpc.reflection.v1.ServerReflection",rpc_type="BIDI_STREAMING"} 2
grpc_server_seconds_sum{error="none",grpc_status_code="OK",net_peer_name="localhost",net_peer_port="8004",rpc_method="ServerReflectionInfo",rpc_service="grpc.reflection.v1.ServerReflection",rpc_type="BIDI_STREAMING"} 0.1547173
# HELP grpc_server_seconds_max  
# TYPE grpc_server_seconds_max gauge
grpc_server_seconds_max{error="none",grpc_status_code="OK",net_peer_name="localhost",net_peer_port="8004",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 0.0037934
grpc_server_seconds_max{error="none",grpc_status_code="OK",net_peer_name="localhost",net_peer_port="8004",rpc_method="ServerReflectionInfo",rpc_service="grpc.reflection.v1.ServerReflection",rpc_type="BIDI_STREAMING"} 0.1383122
# HELP grpc_server_sent_total  
# TYPE grpc_server_sent_total counter
grpc_server_sent_total{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="8004",rpc_method="SayHello",rpc_service="Simple",rpc_type="UNARY"} 1.0
grpc_server_sent_total{grpc_status_code="UNKNOWN",net_peer_name="localhost",net_peer_port="8004",rpc_method="ServerReflectionInfo",rpc_service="grpc.reflection.v1.ServerReflection",rpc_type="BIDI_STREAMING"} 2.0
```

#### 2.2.6 Exception Handling

```

```

```

```

#### 2.2.7 Security

```
@Bean
@GlobalServerInterceptor
AuthenticationProcessInterceptor jwtSecurityFilterChain(GrpcSecurity grpc) throws Exception {
	return grpc
			.authorizeRequests(requests -> requests
					.methods("Simple/StreamHello").hasAuthority("ROLE_ADMIN")
					.methods("Simple/SayHello").hasAuthority("ROLE_USER")
					.methods("grpc.*/*").permitAll()
					.allRequests().denyAll())
			.httpBasic(withDefaults())
			.preauth(withDefaults())
			.build();
}
```

```

```

