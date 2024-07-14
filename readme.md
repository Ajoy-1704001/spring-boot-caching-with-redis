# Spring Boot Caching x Redis

Redis, an advanced key-value store known for its speed and versatility, serves as a powerful tool for caching in modern applications. When combined with Spring Boot, a popular framework for building Java applications, Redis provides robust caching capabilities that enhance performance and scalability.

In this repository, I explored how to implement Redis caching in a Spring Boot application. I have covered the basic setup, configuration, and integration, and provide a GitHub repository where we can find a simple implementation to kickstart in your own projects.

## Implementing Redis Cache with Spring Boot
### Step 1: Setting Up Spring Boot Application With Dependencies

First, initialize a spring boot project with dependencies for
Spring Web, Spring Data jpa, H2 database, Spring Data Redis and Spring Cache Abstraction.
Add these dependencies to `pom.xml` file:
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

### Step 2: Configuring Redis Connection
```properties
spring.cache.type=redis
spring.redis.host=localhost
spring.redis.port=6379
```
Explicitly commanding spring boot to use redis for cache implementation.

### Step 3: Implementing Redis Cache Manager (Optional)
As we are directly using configuration from `application.properties` file,
this step is optional. Still we can configure it specifying ttl and json serialization for cache entries.

### Step 4: Define Cacheable Methods With Annotation

| Annotation    | Description                                                                                                                                                      | Parameters                                                                                       |
|---------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------|
| `@Cacheable`  | Marks a method to cache its result based on the specified cache name and key. If the method is invoked with the same arguments, the cached result is returned.  | - `value`: Name of the cache (required)<br>- `key`: SpEL expression defining the cache key (optional, default is based on method parameters)              |
| `@CacheEvict` | Removes entries from the cache upon method invocation, optionally based on certain conditions like before or after method execution.                         | - `value`: Name of the cache (required)<br>- `key`: SpEL expression defining the cache key (optional)<br>- `allEntries`: Whether to evict all entries in the cache (default is false) |
| `@CachePut`   | Forces a method to execute and puts its result into the cache, overriding the existing cache entry for the specified key.                                      | - `value`: Name of the cache (required)<br>- `key`: SpEL expression defining the cache key (optional, default is based on method parameters)              |
| `@Caching`    | Allows multiple caching annotations (`@Cacheable`, `@CachePut`, `@CacheEvict`) to be applied on the same method, providing flexibility in caching strategies.    | - Accepts an array of `Cacheable`, `CacheEvict`, or `CachePut` annotations as parameters.                                                                   |


## Problems I Faced While Learning & Implementing
I think, most of us will face these problems while implementing the caching for the first time.

1. Incorrect Usage of Cache Key. So,
   the SpEL should be correctly used to get cache keys. Example:
   ```java
    @CachePut(value = "products", key = "#product.id")
    public Product updateProduct(Product product){
        // logic
    }
    ```
2. Forget to Evict Cache

   - We should Use @CacheEvict appropriately to remove or update cache entries when underlying data changes to maintain data consistency.
3. Excessive Caching and Lack of Proper Eviction Policy
    - We can use ttl to define the cache lifetime
    - Sometimes memory can be exceeded because of heavy cache. So, evicting the cache depends on the decision we make. Either it will be First in- First out approach, Least Frequently Used or Least Recently Used approach. We have to mention it in redis configuration file.