For artifacts not in central repository Guicymorphic provides it's own repository.

```
<repository>
   <id>guicymorphic</id>
   <name>Guicymorphic repo</name>
   <url>http://guicymorphic.googlecode.com/svn/trunk/repository</url>
</repository>
```

Currently the following artifacts are available

~~Guicymorphic framework. E.g. generic hibernate dao, extended hibernate factory, etc~~
Not yet published.
```
<dependency>
   <groupId>guicymorphic</groupId>
   <artifactId>guicymorphic-fw</artifactId>
   <version>1</version>
</dependency>
```

A special version of Guice optimized for web environment and safe from redeploy leaks.

```
<dependency>
   <groupId>com.google.inject</groupId>
   <artifactId>guice</artifactId>
   <version>2.0-web</version>
</dependency>
```

Warp-persist compiled at the time of writing this. The revision was [r327](https://code.google.com/p/guicymorphic/source/detail?r=327).

```
<dependency>
   <groupId>com.wideplay</groupId>
   <artifactId>warp-persist</artifactId>
   <version>r327</version>
</dependency>
```

Guiceberry 3.0.0.

```
<dependency>
   <groupId>com.google</groupId>
   <artifactId>guiceberry</artifactId>
   <version>3.0.0</version>
   </dependency>
</dependencies>
```

Testing-libraries-for-java (tl4j) 1.1.1

```
<dependency>
   <groupId>com.google</groupId>
   <artifactId>tl4j</artifactId>
   <version>1.1.1</version>
   </dependency>
</dependencies>
```