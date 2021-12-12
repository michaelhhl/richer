# Multi thread
## 问题
1. spring中两个service调用同一个业务service，导致A线程log出现B线程数据。解决：修改共通service的scope为prototype。
2. 多线程条件下service中使用static变量保存数据，A线程保存的值，被B线程修改。解决：改static变量为Threadlocal保存变量。
3. 多线程打印账票时，根据部店的账号数量，每个线程打印的数量不一致。某个线程打印2万条时，出现out of memory。 pdfbox软件打印。限制directMemory的大小为1G。把2万条数据分页为2千打印一次。

# 自适应限流
## 常见方法
1. 令牌
2. 漏桶
3. 计数器
## 问题
无法根据负载情况，机器性能自动调整流量
## 自适应限流实现方式
cpu > 800 AND (Now - PrevDrop) < 1s AND (MaxPass * MinRT * windows / 1000) < InFlight
1. cpu 使用指数加权平均算法，使数据更加平滑，不受瞬时大流量的影响
cpu = cpu(t-1) * decay + cpu(t) * (1 - decay)
2. 滑动窗口方式采集数据
使用环形链表（长度100）保存每100毫秒的流量

# Shell使用
## Project: health check
1. 双机冷备，一个运行，一个不运行
2. 通过while true实现对运行服务状态进行间隔5分钟检查并打印log
3. 原理：通过ps命令检查docker服务是否启动，或调用服务接口
4. 停掉运行的机器（kill掉进程），启动另外一组，连同nginx一同启动

## 问题：
1. 服务阻塞，没有取回结果，导致误切换
2. shell中string变量存储长度越界
3. shell中主线程不能提前结束，否则取不到分线程的执行结果

# BO特点：
对服务端没有强侵入，服务端无感知

# 统一请求和响应
1. 定义返回的实体对象ResponseEntity：code，message，data
2. 类使用@ControllerAdvice,implements ResponseBodyAdvice
3. 开发环境时，返回原始数据对象；集成时，经过网关，带上标记，返回ResponseEntity。
4. 定义ExceptionHandler类，使用@RestControllerAdvice 拦截各种异常（统一异常处理）
5. @ExceptionHandler（value=CommonException）在方法上，获取异常信息和code，封装成ResponseEntity。

# 调用上下文，可以在controller，service等组件中获取request中的属性，用户ID，当前语言，token等
1. MyContextHolder.java定义静态方法
2. 从RequestContextHolder.getRequestAttributes()中获取attributes
3. 从attributes.getRequest().getHeader()中获取属性

# 国际化
1. 通过@Configuration，@Bean实例化LocalValidatorFactoryBean
  getValidator(final ResourceBoundleMessageSource)
2. messageSource.addBasenames("i18n/CommonMessages");
3. FactoryBean.addValidationMessageSource(messageSource);
4. 在controller或service中autoware messageSource

# 链路追踪
精确记录，调用链其实终止时间，ip，线程，执行sql，同步，异步
对使用者透明
对应用性能无影响。

## gateway
### TraceGlobalFilter implements GlobalFilter
1. 雪花算法生成traceId和spanId
2. 把traceId和spanId放到request header中
### AuthGlobalFilter implements GlobalFilter(过滤白名单)
1. 从header中取出traceId，放入restTemplate

## service中
### TraceInterceptor extends HandlerInterceptorAdapter
1. preHandle（）从request中取出traceId，生成spanId计入mdc和threadlocal中
2. afterCompletion（）清除mdc和threadlocal中数据
### HeaderRequestInterceptor implements Feign.RequestInterceptor
1. 如果request中没有（由MQ或定时器发起调用）traceId，则新建一个，放到header中

## RocketMQ中
1. 自定义ZdyRocketMQAutoConfiguration,import ZdyMessageConverterConfiguration, ZdyListenerContainerConfiguration
2. ZdyAutoConfigurationImportFilter implements AutoConfigurationImportFilter
把RocketMQ原自定义的autoconfiguration去掉
3. 自定义ZdyDefaultMQProducer extends TransactionMQProducer
从context holder中取出traceId放到message的tag中
4. ZdyDefaultRocketMQListenerContainer中从message的tag中取出traceId放入requestHolder和mdc中

## logback-spring.xml
pattern：添加traceId和spanId
增加AsyncAppender进行异步添加
