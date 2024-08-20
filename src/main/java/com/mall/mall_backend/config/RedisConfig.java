package com.mall.mall_backend.config;



import com.mall.mall_backend.utils.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory)
    {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}


/*
so we write a class which return a redistemplate,and add annotation bean,so why this could influence the raw redis config? it is just a class ?!
ChatGPT 说：

Your question touches on a key concept in how Spring's dependency injection and configuration management work. Let's break down why writing a configuration class with a @Bean method that returns a RedisTemplate can influence the Redis configuration in your Spring application.

Spring's Dependency Injection and Configuration
Configuration Class with @Configuration:

When you annotate a class with @Configuration, Spring treats it as a source of bean definitions. This means that Spring will automatically process this class when the application context is being created.
Spring scans this class for methods annotated with @Bean and adds the returned objects from these methods to the Spring application context as beans.
@Bean Annotation:

The @Bean annotation tells Spring that the method it decorates will return an object that should be managed as a Spring bean.
When Spring needs an instance of a RedisTemplate, it will look in the application context for any beans of that type. If it finds one, it will use that instead of creating a new one with default settings.
Influence on Raw Redis Configuration:

By providing your own RedisTemplate bean in a configuration class, you're essentially overriding the default RedisTemplate that Spring Boot might automatically configure for you.
The default behavior in Spring Boot is to auto-configure certain beans like RedisTemplate based on properties and environment settings. However, if you define your own RedisTemplate bean, Spring Boot's auto-configuration will back off, meaning it won’t create a default bean, and it will use your custom-configured RedisTemplate instead.
Why Does This Work?
Spring Application Context: The Spring application context is essentially a container that holds all the beans that are created by or registered with Spring. When your application needs a bean, like a RedisTemplate, Spring will provide it from this context.
Custom vs. Default: If a bean of a specific type (like RedisTemplate) is already present in the context (because you've defined it), Spring won't create a default one. Your custom bean takes precedence.
Bean Injection: Throughout your application, whenever you @Autowired a RedisTemplate, Spring will inject the instance that you've defined in your configuration class.
Analogy to Help Understand
Think of your Spring configuration class as a recipe book that Spring uses to make the "ingredients" (beans) your application needs. If you provide a specific recipe (like a custom RedisTemplate), Spring will use that instead of following the default recipe it might have used otherwise.

Example
Here’s a simplified example:

Without Custom Configuration: If you don't define a RedisTemplate bean, Spring Boot will automatically configure one for you using its default settings (e.g., default serializers, connection settings).

With Custom Configuration: When you define a RedisTemplate bean with custom settings (e.g., using FastJsonRedisSerializer), Spring Boot will not create a second RedisTemplate. Instead, it will use the one you've provided, which might serialize and deserialize data differently than the default.

English Summary:
When you define a RedisTemplate bean in a Spring @Configuration class, you are effectively telling Spring how to configure and use Redis in your application. This custom configuration overrides the default auto-configuration provided by Spring Boot. The Spring application context manages these beans and ensures your custom-configured RedisTemplate is used throughout your application instead of the default one.
 */




/*
Cookie:

Storage: Stored in the browser's cookie storage.
Transmission: Automatically sent with every request by the browser.
Security: Vulnerable to CSRF attacks since it's automatically included in requests.
Stateful: Typically used in traditional, stateful authentication where the server maintains session state.
Token:

Storage: Typically stored in localStorage, sessionStorage, or manually handled in headers.
Transmission: Manually sent via HTTP headers (e.g., Authorization) or request parameters.
Security: More secure against CSRF; however, requires protection against XSS attacks.
Stateless: Ideal for modern, stateless authentication in distributed systems (e.g., JWT).
Summary: Tokens offer more flexibility, security, and scalability for modern web applications, while cookies are simpler but less secure for stateful authentication.
 */