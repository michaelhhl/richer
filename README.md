# Multi thread
## 问题
1. spring中两个service调用同一个业务service，导致A线程log出现B线程数据。解决：修改共通service的scope为prototype。
2. 多线程条件下service中使用static变量保存数据，A线程保存的值，被B线程修改。解决：改static变量为Threadlocal保存变量。
3. 多线程打印账票时，根据部店的账号数量，每个线程打印的数量不一致。某个线程打印2万条时，出现out of memory。 pdfbox软件打印。限制directMemory的大小为1G。把2万条数据分页为2千打印一次。
