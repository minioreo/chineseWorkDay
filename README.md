# chineseWorkDay
基于阿里云Serverless函数计算框架实现的工作日服务，提供工作日（正常工作日+法定节假日调休形成的工作日），节假日（包含法定节假日）。可部署于阿里云函数计算。

- 2019数据来源:http://www.gov.cn/zhengce/content/2018-12/06/content_5346276.htm

# NodeJs版本
由于Java运行时在阿里云上冷启动速度较慢，且运行时占用内存大，运行费用是按照运行时间乘以占用内存来计算的，所以NodeJS版本更具优势，冷启动速度快，运行费用小。见[nodejs版本](https://github.com/minioreo/chineseWorkDayNode)
目前线上实例冷启动，java版本的从启动到运行结束需要9s，nodejs版本的需要1s.内存使用上，java版本基本是node版本的4倍以上。
