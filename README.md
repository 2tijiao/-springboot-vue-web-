2022级网络工程马镜瑶202230444405 
代码介绍： 
一、 
在main分支中为后端springboot代码，配置文件涉及私密配置故不展示，其他系统文件也不展示。 
主要分为Administer、User、Goods、Order、Trolly五个模块，分别编写Controller层、Service层、Mapper层代码，对应的类与数据库的每个表属性一一对应 
其余文件还包括拦截器文件、token文件、线程文件 
二、 
在vue分支中为前端vue代码，主界面为Login文件 其余页面展示分为管理员端和用户端两个分别建立主框架、商品展示、购物车（用户）、订单管理、用户管理（管理员）等 
向后端进行请求的函数定义在api文件夹中 
其余文件包括页面切换由router控制见router文件，使用axios向后端进行请求见request文件，token设置见token文件
