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
