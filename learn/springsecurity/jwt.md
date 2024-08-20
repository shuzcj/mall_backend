JWT简介
1.什么是JWT

在介绍JWT之前，我们先来回顾一下利用token进行用户身份验证的流程：

    客户端使用用户名和密码请求登录
    服务端收到请求，验证用户名和密码
    验证成功后，服务端会签发一个token，再把这个token返回给客户端
    客户端收到token后可以把它存储起来，比如放到cookie中
    客户端每次向服务端请求资源时需要携带服务端签发的token，可以在cookie或者header中携带
    服务端收到请求，然后去验证客户端请求里面带着的token，如果验证成功，就向客户端返回请求数据

这种基于token的认证方式相比传统的session认证方式更节约服务器资源，并且对移动端和分布式更加友好。其优点如下：

    支持跨域访问：cookie是无法跨域的，而token由于没有用到cookie(前提是将token放到请求头中)，所以跨域后不会存在信息丢失问题
    无状态：token机制在服务端不需要存储session信息，因为token自身包含了所有登录用户的信息，所以可以减轻服务端压力
    更适用CDN：可以通过内容分发网络请求服务端的所有资料
    更适用于移动端：当客户端是非浏览器平台时，cookie是不被支持的，此时采用token认证方式会简单很多
    无需考虑CSRF：由于不再依赖cookie，所以采用token认证方式不会发生CSRF，所以也就无需考虑CSRF的防御

而JWT就是上述流程当中token的一种具体实现方式，其全称是JSON Web Token，官网地址：https://jwt.io/

通俗地说，JWT的本质就是一个字符串，它是将用户信息保存到一个Json字符串中，然后进行编码后得到一个JWT token，并且这个JWT token带有签名信息，接收后可以校验是否被篡改，所以可以用于在各方之间安全地将信息作为Json对象传输。JWT的认证流程如下：

    首先，前端通过Web表单将自己的用户名和密码发送到后端的接口，这个过程一般是一个POST请求。建议的方式是通过SSL加密的传输(HTTPS)，从而避免敏感信息被嗅探
    后端核对用户名和密码成功后，将包含用户信息的数据作为JWT的Payload，将其与JWT Header分别进行Base64编码拼接后签名，形成一个JWT Token，形成的JWT Token就是一个如同lll.zzz.xxx的字符串
    后端将JWT Token字符串作为登录成功的结果返回给前端。前端可以将返回的结果保存在浏览器中，退出登录时删除保存的JWT Token即可
    前端在每次请求时将JWT Token放入HTTP请求头中的Authorization属性中(解决XSS和XSRF问题)
    后端检查前端传过来的JWT Token，验证其有效性，比如检查签名是否正确、是否过期、token的接收方是否是自己等等
    验证通过后，后端解析出JWT Token中包含的用户信息，进行其他逻辑操作(一般是根据用户信息得到权限等)，返回结果

![img.png](img.png)


2.为什么要用JWT
2.1 传统Session认证的弊端

我们知道HTTP本身是一种无状态的协议，这就意味着如果用户向我们的应用提供了用户名和密码来进行用户认证，认证通过后HTTP协议不会记录下认证后的状态，那么下一次请求时，用户还要再一次进行认证，因为根据HTTP协议，我们并不知道是哪个用户发出的请求，所以为了让我们的应用能识别是哪个用户发出的请求，我们只能在用户首次登录成功后，在服务器存储一份用户登录的信息，这份登录信息会在响应时传递给浏览器，告诉其保存为cookie，以便下次请求时发送给我们的应用，这样我们的应用就能识别请求来自哪个用户了，这是传统的基于session认证的过程

然而，传统的session认证有如下的问题：

    每个用户的登录信息都会保存到服务器的session中，随着用户的增多，服务器开销会明显增大
    由于session是存在与服务器的物理内存中，所以在分布式系统中，这种方式将会失效。虽然可以将session统一保存到Redis中，但是这样做无疑增加了系统的复杂性，对于不需要redis的应用也会白白多引入一个缓存中间件
    对于非浏览器的客户端、手机移动端等不适用，因为session依赖于cookie，而移动端经常没有cookie
    因为session认证本质基于cookie，所以如果cookie被截获，用户很容易收到跨站请求伪造攻击。并且如果浏览器禁用了cookie，这种方式也会失效
    前后端分离系统中更加不适用，后端部署复杂，前端发送的请求往往经过多个中间件到达后端，cookie中关于session的信息会转发多次
    由于基于Cookie，而cookie无法跨域，所以session的认证也无法跨域，对单点登录不适用

2.2 JWT认证的优势

对比传统的session认证方式，JWT的优势是：

    简洁：JWT Token数据量小，传输速度也很快
    因为JWT Token是以JSON加密形式保存在客户端的，所以JWT是跨语言的，原则上任何web形式都支持
    不需要在服务端保存会话信息，也就是说不依赖于cookie和session，所以没有了传统session认证的弊端，特别适用于分布式微服务
    单点登录友好：使用Session进行身份认证的话，由于cookie无法跨域，难以实现单点登录。但是，使用token进行认证的话， token可以被保存在客户端的任意位置的内存中，不一定是cookie，所以不依赖cookie，不会存在这些问题
    适合移动端应用：使用Session进行身份认证的话，需要保存一份信息在服务器端，而且这种方式会依赖到Cookie（需要 Cookie 保存 SessionId），所以不适合移动端

    因为这些优势，目前无论单体应用还是分布式应用，都更加推荐用JWT token的方式进行用户认证


# JWT结构

JWT由3部分组成：标头(Header)、有效载荷(Payload)和签名(Signature)。在传输的时候，会将JWT的3部分分别进行Base64编码后用.进行连接形成最终传输的字符串

![img_1.png](img_1.png)



Yes, the `SecretKey` in your code is indeed the key used in the HMACSHA256 algorithm to sign the JWT. The HMACSHA256 (HMAC with SHA-256) algorithm combines a secret key with the message data (in this case, the JWT header and payload) to create a unique signature that can be verified to ensure the token has not been tampered with.

### JWT Equation

A JWT consists of three parts:

1. **Header** (encoded in Base64URL)
2. **Payload** (encoded in Base64URL)
3. **Signature** (generated using the signing algorithm)

The basic structure of a JWT is:

```
JWT = Base64URL(Header) + "." + Base64URL(Payload) + "." + Base64URL(Signature)
```

### Steps to Create a JWT

1. **Header**: JSON object containing metadata, including the type of token (`JWT`) and the signing algorithm used (`HS256`).

   ```json
   {
     "alg": "HS256",
     "typ": "JWT"
   }
   ```

    - Encoded as: `Base64URL(Header)`

2. **Payload**: JSON object containing the claims (data), such as the subject, issuer, expiration time, etc.

   ```json
   {
     "sub": "123456",
     "name": "John Doe",
     "admin": true
   }
   ```

    - Encoded as: `Base64URL(Payload)`

3. **Signature**: The signature is created by hashing the encoded header and payload together with the secret key using the HMACSHA256 algorithm.

   **Signature Equation**:
   ```
   Signature = HMACSHA256(
       Base64URL(Header) + "." + Base64URL(Payload), 
       SecretKey)
   ```

    - Encoded as: `Base64URL(Signature)`

### Full JWT Example

Given the header, payload, and secret key, the JWT is constructed as:

```
JWT = Base64URL(Header) + "." + Base64URL(Payload) + "." + Base64URL(Signature)
```

For example, if we have:

- **Header**:
  ```json
  {
    "alg": "HS256",
    "typ": "JWT"
  }
  ```
- **Payload**:
  ```json
  {
    "sub": "123456",
    "name": "John Doe",
    "admin": true
  }
  ```
- **Secret Key**: `"qx"`

The resulting JWT would look something like:

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTYifQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

### Key Points:

- **SecretKey**: In your code, `SecretKey` is generated using the `generalKey()` method, which is derived from the `JWT_KEY` (`"qx"`) and is used as the key for the HMACSHA256 algorithm.

- **Equation**:
  ```
  JWT = Base64URL(Header) + "." + Base64URL(Payload) + "." + Base64URL(HMACSHA256(Base64URL(Header) + "." + Base64URL(Payload), SecretKey))
  ```




















