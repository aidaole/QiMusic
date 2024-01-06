# AiMusic

一款音乐app，练手项目。[项目地址](https://github.com/aidaole/AiMusic)

**做此项目的目标**

使用Hilt，Navigation，Lifecycle组件等Jetpack组件，使用MVVM，单Activity架构，使用协程，FLow加载做异步操作，探索各种组件的最佳实践。

**服务端接口说明**

项目中使用的服务端接口均来自于 [NeteaseCloudMusicApi](https://docs.neteasecloudmusicapi.binaryify.com/#/) , 感谢作者的无私分享。

## 使用方法很简单

搭建服务端接口

```shell
// 安装
$ git clone git@github.com:Binaryify/NeteaseCloudMusicApi.git
$ cd NeteaseCloudMusicApi
$ npm install

// 运行
$ node app.js
```

代码中的 baseurl配置改成电脑的ip地址+端口，即可通过手机访问

```kotlin
const val BASE_URL = "http://192.168.31.148:3000"
```

## 内容展示

![内容展示](./images/aimusic/2024-01-06-20-39-10.gif ':size=300')

