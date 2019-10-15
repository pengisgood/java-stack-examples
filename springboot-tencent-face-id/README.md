# Tencent Face Id Demo

需要提前申请腾讯云的人脸核身服务，然后获取`appId`和`appSecret`。

## How to run

```
../gradlew bootRun
```

then visit [http://localhost:8081](http://localhost:8081)

## Tencent face id documentation

完整的文档可以[参考官网](https://cloud.tencent.com/document/product/1007/31069)

本demo里实现了两种集成方式：

* [独立H5接入](https://cloud.tencent.com/document/product/1007/35883)（可以通过微信扫描二维码的形式进行人脸核身）
* [PC端H5接入](https://cloud.tencent.com/document/product/1007/35893)
