# Umamusume_Helper_Java

An experimental Umamusume Helper, based on JDK17, Scrcpy and OpenCV.

### 1. 前置条件

1. Windows环境，本程序基于X86上的Windows和各种Windows API
2. JDK17，Amazon Corretto 17 v17.0.4
3. 一个安装了《賽馬娘Pretty Derby》的安卓设备，并打开开发者设置、无线调试并能够通过ADB进行连接
4. 《賽馬娘Pretty Derby》需要安装好并登录

### 2. 进行安装本程序

#### 发布前感想：

1. 去掉了需要安装@晨钟酱的投屏控制器才能使用的前置条件。不过还是十分感谢晨钟酱提供的思路。
2. Scrcpy能够根据Windows屏幕分辨率自行将串流到电脑上的安卓设备屏幕进行缩放并缩放到一个合适的大小，太强大了这个项目

#### 参考与鸣谢：

1. https://stackoverflow.com/questions/464593/how-to-capture-selected-screen-of-other-application-using-java
2. https://github.com/Genymobile/scrcpy
3. https://www.bilibili.com/video/BV19v411T7sR
4. https://stackoverflow.com/questions/17865465/how-do-i-draw-an-image-to-a-jpanel-or-jframe
