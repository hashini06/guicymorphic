# Introduction #

It is very simile to get jetty running. Basically you can do it

  * via Java Code
  * via Ant
  * via Scripts


# via Java Code #

```
public class OneWebApp
{
    public static void main(String[] args) throws Exception
    {
        String jetty_home = System.getProperty("jetty.home","..");
 
        Server server = new Server(8080);
 
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(jetty_home+"/webapps/test.war");
        server.setHandler(webapp);
 
        server.start();
        server.join();
    }
}
```

For more see [Embedding Jetty Tutorial](http://wiki.eclipse.org/Jetty/Tutorial/Embedding_Jetty).

# via Ant #
You need Jetty libs on the classpath. I recommend using the Webtide distribution for one it has the JSP support. See bellow.

```
 <path id="jetty.class.path">

        <!--add jetty libs to classpath this includes the servet-2.5.jar-->
        <fileset dir="../jetty/lib" includes="*.jar"/>
        <!--And the jsp support libs on the CP-->
        <fileset dir="../jetty/lib/jsp" includes="*.jar"/>
        <!--The jetty ant definition-->
        <fileset dir="../lib/ant" includes="*.jar"/>
    </path>

    <taskdef classpathref="jetty.class.path" name="jetty" classname="org.mortbay.jetty.ant.JettyRunTask"/>


  <target name="examples.eventbus.run.jetty" depends="examples.eventbus.war.optional">
        <jetty tempDirectory="jetty-temp">
            <webapp name="examples" warfile="build/tutorial.war" contextpath="/examples"/>
        </jetty>
    </target>
```

For more on the Ant plugin see [Jetty Ant Plugin](http://docs.codehaus.org/display/JETTY/Ant+Jetty+Plugin).

Note the ant plugin is not part of the official distribution. You can get it from maven repository [jetty-ant maven directory](http://repo1.maven.org/maven2/org/mortbay/jetty/jetty-ant/7.0.1.v20091125/).
# via Scripts #
```
wget http://dist.codehaus.org/jetty/jetty-hightide-7.0.1/jetty-hightide-7.0.1.v20091125.tar.gz
tar xfz jetty-hightide-7.0.1.v20091125.tar.gz
cd jetty-hightide-7.0.1.v20091125
java -jar start.jar
```
For more see [Downloading Jetty](http://wiki.eclipse.org/Jetty/Starting/Downloads).