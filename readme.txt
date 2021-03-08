安装jacob
将jacob-1.19-x64.dll复制到%JAVA_HOME%\jre\bin
注意Dfile后面的路径
mvn install:install-file -DgroupId=com.jacob -DartifactId=jacob -Dversion=1.19 -Dfile=C:/workspace/zzz/apache-maven-3.6.0/离线安装包/jacob.jar -Dpackaging=jar -DgeneratePom=true -DcreateChecksum=true

运行VoiceApp.java

打开浏览器
http://localhost:8082/say?message=太好了

1、看VoiceController.java有两个方法，say()和sayRest()
2、sayRest()调用了ContextUtil.getParams，取得了前端所有的参数，然后随便处理了一下得到了一个JSON对象，然后在JSON对象中加入了传入的内容便于前端检查
3、浏览器打开【前端测试/index.html】运行，点击那按钮，看看是不是把路径这些伟了过去？

jQuery的$.ajax方法就是这样用的