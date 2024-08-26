## 如何引用common包里的webconfig？

是的，确实有两种主要方式来实现你描述的场景：

### **方式 1：通过 Maven 引用 `common` 模块并扩大扫描范围**

1. **Maven 引用**：
    - 首先，在微服务的 `pom.xml` 文件中添加对 `common` 模块的依赖。这会确保 `common` 模块中的类和配置被包含在微服务的构建中。

2. **扩大扫描范围**：
    - 然后，在微服务的启动类上使用 `@ComponentScan` 注解，将 `common` 模块所在的包（如 `com.mall.common`）包括在 Spring 的扫描路径中。这样，Spring 容器就能够发现并注册 `common` 模块中的 `@Component`、`@Configuration` 等注解标记的类。

   ```java
   @SpringBootApplication
   @ComponentScan(basePackages = {"com.mall.userservice", "com.mall.common"})
   public class UserServiceApplication {
       public static void main(String[] args) {
           SpringApplication.run(UserServiceApplication.class, args);
       }
   }
   ```

### **方式 2：使用 Spring Boot Auto-Configuration**

1. **在 `common` 模块中配置自动配置**：
    - 你可以在 `common` 模块中使用 Spring Boot 的自动配置机制，使得任何引用该模块的微服务都能自动加载 `common` 模块中的配置，而不需要手动扩大扫描范围。

    - 在 `common` 模块的 `src/main/resources/META-INF/` 目录下创建 `spring.factories` 文件，并在其中声明自动配置类：

      ```properties
      org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
      com.mall.common.config.WebConfig
      ```

    - 确保 `WebConfig` 类上有 `@Configuration` 注解：

      ```java
      @Configuration
      public class WebConfig implements WebMvcConfigurer {
          // 配置类内容...
      }
      ```

2. **Maven 引用**：
    - 同样，需要在微服务的 `pom.xml` 文件中添加对 `common` 模块的依赖，以确保微服务可以访问 `common` 模块中的类和配置。

    - 由于使用了 Spring Boot 的自动配置机制，微服务引用 `common` 模块后，`common` 模块中的配置类会自动生效，无需手动扩大扫描范围。

### **总结**

- **方式 1：Maven 引用 + 扩大扫描范围**：这种方式比较直接和传统，通过在微服务中手动指定扫描路径，确保 Spring 能够发现和加载 `common` 模块中的组件。

- **方式 2：Spring Boot Auto-Configuration**：这种方式更加自动化和优雅，特别适合当你希望 `common` 模块的配置能够自动应用到所有引用它的微服务时。Spring Boot 的自动配置机制可以让你的微服务在引用 `common` 模块后自动加载和应用 `common` 模块中的配置，无需额外的配置工作。

根据你的项目需求和习惯，你可以选择其中一种方式。