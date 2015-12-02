# How to do it #

Easiest way using Java Code

See schema exporter [SchemaExporter.java](http://code.google.com/p/guicymorphic/source/browse/trunk/contacts/src/guicymorphic/contacts/tools/SchemaExporter.java).
```
public static void main(String[] args) {
        Properties hibernateProperties = Properties2.loadProperties("hibernate-production-db.properties");
        Injector injector = Guice.createInjector(new ContactsServerModule(hibernateProperties));
        Configuration configuration = injector.getInstance(Configuration.class);

//        You can call generate schema directly on the Configuration and use the strings as you please
//        String[] strings = configuration.generateSchemaCreationScript(new HSQLDialect());
        SchemaExport export = new SchemaExport(configuration);

//           This will print on stdout the schema as configured by the hibernate Configuration object
        export.create(true,true);

//        This will get shutdown via the Runtime hook anyway but it is nicer this way
        injector.getInstance(PersistenceService.class).shutdown();
}
```

there is ant and maven plugins for this but they require that you specify the entities in the xml configuration. We are dynamically adding the entities by scanning packages therefore it will not work with ant/maven tools.

You can run the class via ant with e.g.

```
   <target name="export.schema" depends="javac" description="Export the schema">
        <copy file="conf/log4j-disabled.properties" tofile="conf/log4j.properties" overwrite="true"/>
        <delete file="build/schema.sql"/>
        <java failonerror="true" fork="true" classname="guicymorphic.contacts.tools.SchemaExporter"
              output="build/schema.sql">
            <classpath>
                <pathelement location="src"/>
                <pathelement location="../fw/src"/>
                <path refid="project.class.path"/>
            </classpath>
            <jvmarg value="-Xmx256M"/>
        </java>
    </target>
```

**Important** For shared development databases: After using the schema to create the database structure you should only allow the hibernate user on the database to do inserts and updates. If your novice coworker accidentally sets the hibernate.hbm2ddl.auto to create it will not empty/drop your database as the db user will not have the appropriate rights.