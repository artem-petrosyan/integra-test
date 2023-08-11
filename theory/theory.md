# Тестирование веб-приложений в Spring Boot

## In-memory DB

pom.xml

```xml

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>test</scope>
</dependency>
```

application.properties

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

## TestRestTemplate

```java
@SpringBootTest
public class MyTest {
    @Autowired
    TestRestTemplate template;

    @Test
    void my_test()  {
        ResponseEntity<String> response 
                = restTemplate.getForEntity("/my-endpoint", String.class);
        //...
    }
}
```

## WebMvcTest

```java
@WebMvcTest(MyController.class)
public class MyTest {

    @Autowired
    private MockMvc mockMvc;

    //mock and spy
    
    @Test
    public void testControllerMethod() throws Exception {
        mockMvc.perform(get("/my-endpoint"))
               .andExpect(status().isOk())
               .andExpect(content().string("Expected Response"));
    }
}
```

## [Домашка](https://skyengpublic.notion.site/3-6-Spring-Boot-0070e5697e594bd0a5c6e5f96a29f950)