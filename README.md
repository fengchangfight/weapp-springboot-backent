# weapp-springboot-backent
微信小程序springboot后台

读程序的前提是先弄懂微信小程序登录认证原理（见resources文件夹中）

然后java部分主要的逻辑是写一个拦截器（见ApiWebConfigure， ApiInterceptor），

拦截器就根据以上所述原理图，去redis中查询3rd session key是否存在，存在就放行（即通过认证），否则就拦截（即认证失败）

WxAuthController中的createSssion是用于session失效（或者首次登陆）时获取session的方法，主要逻辑就这么简单，稍微看看就明白
