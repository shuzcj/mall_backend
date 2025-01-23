To provide a comprehensive overview, I'll define a simple class, detail a DAO interface, and show corresponding MyBatis mapper configurations for each method of parameter passing.

### 1. Using a `Map<>`
This method is flexible and useful for methods with variable or optional parameters.

#### DAO Interface
```java
List<Product> getProducts(Map<String, Object> queryParams);
```

#### Mapper XML
```xml
<select id="getProducts" resultType="Product">
    SELECT * FROM products
    WHERE name LIKE #{name} AND price <= #{maxPrice}
</select>
```
In this example, `queryParams` could include keys like `name` and `maxPrice` which are used in the SQL query.

### 2. Defining a Class
Ideal for operations where parameters are part of a larger entity, promoting clean and maintainable code.

#### Product Class
```java
public class Product {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    // getters and setters
}
```

#### DAO Interface
```java
void addProduct(Product product);
```

#### Mapper XML
```xml
<insert id="addProduct">
    INSERT INTO products (name, price, stock)
    VALUES (#{name}, #{price}, #{stock})
</insert>
```
This method utilizes an object of `Product` class to insert data into the database.

### 3. Using `@Param`
Useful for methods with a few parameters, making SQL mappings clear and straightforward.

#### DAO Interface
```java
void updateProduct(@Param("productId") Integer productId, @Param("price") BigDecimal price);
```

#### Mapper XML
```xml
<update id="updateProduct">
    UPDATE products
    SET price = #{price}
    WHERE id = #{productId}
</update>
```
In this case, the `@Param` annotation helps explicitly define SQL parameters in the mapper.

### Complete Setup Example
To put everything together, here's how you might define the DAO interface, the class, and the mapper for these methods.

#### ProductDAO Interface
```java
public interface ProductDAO {
    void addProduct(Product product);
    List<Product> getProducts(Map<String, Object> queryParams);
    void updateProduct(@Param("productId") Integer productId, @Param("price") BigDecimal price);
}
```

#### MyBatis Mapper XML (combining all three)
```xml
<mapper namespace="ProductDAO">
    <insert id="addProduct">
        INSERT INTO products (name, price, stock)
        VALUES (#{name}, #{price}, #{stock})
    </insert>

    <select id="getProducts" resultType="Product">
        SELECT * FROM products
        WHERE name LIKE #{name} AND price <= #{maxPrice}
    </select>

    <update id="updateProduct">
        UPDATE products
        SET price = #{price}
        WHERE id = #{productId}
    </update>
</mapper>
```

This setup provides a robust framework for managing product data with various methods of passing parameters, allowing for flexibility and clarity in your application's data access layer.

**Improved English for clarity:**
"Please explain these three methods with detailed examples including the mapper SQL, defining the class, DAO interface, and how to use them in the DAO mapper."