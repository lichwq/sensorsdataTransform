# 概述
该项目主要是将数据从gary和event转化为神策所需要的格式

整个项目使用storm进行编写。

目前已经实现了Moutan的转换，其它的还是测试阶段。

## 依赖的资源
### thrift
1. 所有的thrift文件都保存在http://arch.pages.gitlab.baixing.cn/THub/Moutan.html。
2. 下载后， 使用 thrift --? gen java project.thrift, 产生java 所需的文件。
3. 项目中使用的thirft 类都保存在com.baixing.bi.thrift。目录下。


### 其它资源
 1. 其它还有类目、城市映射文件都放在sha2hb08...sha2hb10的 /home/deoloy/stormconf目录下。
 2. 城市的数据是从 onetruehive.d_01_city中取出来的。
    ``` java
    cityId, cityNameEn, cityNameCn, shengNameCn   
    ```
 3. 类目的数据是从 onetruehive.d_00_category中取出来的。文件格式为：
    ``` java
    category_name_en,category_name_cn,top_category_name_en,top_category_name_cn
    ```

 4. Url Type 映射是使用Hive Udf 的映射文件。

 5. Area 比较麻烦，具体 sql/moutan/area.sql， 里面有两段sql，将他们的结果合并成一个文件就可以了。
 
 


## 编译命令
在对应的topology中设置是打包dev环境，test环境，还是online环境。
例如motan打包为线上环境:

```java
mvn clean install -DskipTests  -Pmoutan-online
```

## 部署

```java
scp target/sensorsdataTransform-moutan-online-1.0-SNAPSHOT.jar deploy@sha2hb08:/home/deploy/zhangjiali/ 
```

## 启动

1. 在 http://sha2hb08:18080 上停止掉对应的topology
2. 登录 deploy@sha2hb08:/home/deploy/zhangjiali/ 
3. 运行 

```java
 storm jar sensorsdataTransform-moutan-online-1.0-SNAPSHOT.jar com.baixing.bi.topology.MoutanTopology
```
