Aliyun-CompletionAutoSnapshotPolicy
---------

```
          _ _                     _______          _ 
    /\   | (_)                   |__   __|        | |
   /  \  | |_ _   _ _   _ _ __      | | ___   ___ | |
  / /\ \ | | | | | | | | | '_ \     | |/ _ \ / _ \| |
 / ____ \| | | |_| | |_| | | | |    | | (_) | (_) | |
/_/    \_\_|_|\__, |\__,_|_| |_|    |_|\___/ \___/|_|
               __/ |                                 
              |___/                                  

```

Aliyun-CompletionAutoSnapshotPolicy 是一款自动化脚本，加入定时任务可对平台内未添加自动快照策略的硬盘添加策略，免除手动添加的烦恼。
整合了阿里云-专有云V3.12 官方 ASAPI SDK（0.0.8.1）


## 更新记录

* 2021-09-21 `Release v1.0.0` 初稿完成

## 准备动作

编写配置文件放置入脚本JAR包同目录中

```
conf.json

{
    "RegionId" : "XXX",
    "ApiGateWay" : "XXX",
    "AccessKeyId" : "XXX",
    "AccessKeySecret" : "XXX"
}
```

注意事项：

1、配置文件名必须为conf.json

2、该脚本调用阿里云（专有云）的ASAPI进行快照策略添加工作，其中*ApiGateWay*可用以下方法获得

天基(tianji)  -》 报表  -》 服务注册变量  -》筛选*ASAPI*  -》 endpoint



### 使用

```

java -jar Aliyun-CompletionAutoSnapshotPolicy-*.jar

```



#### 自动加载
```
job.sh

#!/bin/bash

result=$(java -jar Aliyun-CompletionAutoSnapshotPolicy-*.jar )

echo ${result}



crontab -e


* 19 * * * ./job.sh



```




## License
除 “版权所有（C）阿里云计算有限公司” 的代码文件外，遵循 [MIT license](http://opensource.org/licenses/MIT) 开源。


