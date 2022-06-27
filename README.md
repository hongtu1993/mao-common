# Mao Common

## 更新日志

### 1.0.10

> 20220627

1. 加入DDD相关的通用类文件

## 开发配置

### Maven配置

在maven的setting.xml文件中，加入`profile`配置，如下：

```xml
<profiles>
    <profile>
      <id>github</id>
      <repositories>
        <repository>
          <id>central</id>
          <url>https://repo1.maven.org/maven2</url>
        </repository>
        <repository>
          <id>jitpack.io</id>
          <url>https://jitpack.io</url>
        </repository>
        <repository>
          <id>github</id>
          <url>https://maven.pkg.github.com/hongtu1993/*</url>
          <snapshots>
            <enabled>true</enabled>
          </snapshots>
        </repository>
      </repositories>
    </profile>
</profiles>
```

在maven的setting.xml文件中，加入`server`配置，如下：

```xml
<servers>
    <server>
      <id>github</id>
      <username>YOUR USERNAME</username>
      <password>YOUR TOKEN</password>
    </server>
</servers>
```

### 项目pom.xml配置

在项目中的pom.xml文件中，加入`repository`配置，如下：

```xml
<distributionManagement>
   <repository>
     <id>github</id>
     <name>mao</name>
     <url>https://maven.pkg.github.com/hongtu1993/mao-common</url>
   </repository>
</distributionManagement>
```

## 发布配置

https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/creating-a-personal-access-token