# 一级标题
这是一个段落  
又是一个段落

新启动的一个段落

*斜体*
**粗体**
***又写又粗体***
---
分开分割  
添加一个~~删除~~先

列表：
- 第一项
    1. 项目1
    2. 项目2
- 第二项
    > 区块项目  
    > 区块项目2
 
 代码：  
 printf()函数  
 `printf()`函数
 
 代码块：
 ```javascript
 $(document).ready(function() {
     alert('xxx');
 });
 ```
 
 这是一个通向[google][1]的链接.   
 
[1]: http://www.google.com/

创建一个表格：
| 表头  |  标题  |
|  :---:  |  :---:  |
|  1  |  2  |
|  4  |  3  |

## 二级标题
### 三级标题
#### 四级标题
##### 五级标题
###### 六级标题

ObjectMapperConfig
Enum类型,前端小写，后端大写，
前-》后，转成大写后反序列化成对象
后-》前，转换成小写后序列化
自定义EnumDeserializer/EnumSerializer类
SimpleModule.addDeserializer(Enumxxx.class, new EnumDeserializer());
SimpleModule.addDeserializer(Enumxxx.class, new EnumSerializer());
ObjectMapper.registerModule(SimpleModule);

SwaggerConfig
使用swagger3.0配置接口在线文档

AtheaConnectionConfig
配置链接属性ConectionProperties，配置RestTemplate

PlusEncodeInterceptor实现ClientHttpRequestInterceptor
在调用restTemplate时把+号变为%2B

ForestResponseErrorhandler继承DefaultResponseErrorHandler
restTemplate返回时的错误处理器

MappingJackson2HttpMessageConverter
restTemplate返回时的消息转换器

AccessLogFilter继承OncePerRequestFilter
打印请求的参数和执行时间。
PROD环境中参数加密输出。

AuthenticationPathConfig
通过YAMLFactory读取yml方式获取许可路径，放到properties文件中

Customize the Environment or ApplicationContext Before it Starts.
EnvironmentPostProcessor接口
可以对propertySource进行处理，如，对property文件中的加密字段，取出后保存以备后续使用。
@PropertySource will take place when application context is being refreshed. late.


LocaleResolver接口
可以从request中获取，并设置到LocaleContextHolder中。

LocalValidatorFactoryBean类
可以设置validationMessageSource，从而加载本地化资源。

自定义validator
自定义注解Password，再定义PasswordValidator实现ConstraintValidator。

Global异常处理中，要带有本地化的messageSource。

servlet的Interceptor，继承HandlerInterceptorAdapter类。来回拦截。
Feign的Interceptor，继承RequestInterceptor类。添加header数据等。
ResponseBodyAdvice类，发生在往客户端写数据之前的时刻，可以根据条件返回json或自定义的类型。如dev/prod返回不同数据。

自定义继承MappingJackson2HttpMessageConverter类，用于实现feign返回值的转换，基本类型，统一的包装类型等的返回。

mybatis的interceptor，设置阈值，对于慢sql打印log。

----
router
	1.通过applyContexts封装Context.Provider,把需要放到context中共享的数据添加进来。
	2.Base组件
		添加QueryClientProvider，BrowserRouter组件及children
	3.Routes组件作为Base组件的child
		在根Route节点中，用Layout组件作为element

Layout
	1.外层使用Frame组件，其中：
		定义共通的布局，tab菜单，显示时间等
		使用<Outlet/>把根Route节点下的子Route节点的element引用过来render


