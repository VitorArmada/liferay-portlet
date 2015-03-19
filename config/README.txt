-------------------------------------------------------------------------------
---- README - Impresa Site Configuration
-------------------------------------------------------------------------------

Copy files to:
-------------------------------------------------------------------------------

${TOMCAT_HOME}/conf/context.xml
${TOMCAT_HOME}/webapps/ROOT/WEB-INF/liferay-web.xml
${TOMCAT_HOME}/webapps/ROOT/WEB-INF/classes/portal-ext.properties
${TOMCAT_HOME}/webapps/ROOT/WEB-INF/classes/portal-developer.properties

For DEV MODE use:

portal-ext.properties-DEV  (change file name to portal-ext.properties)


Copy file to:
-------------------------------------------------------------------------------
Repository info (maven settings):

~/.m2/settings.xml


Change the plugins/pom.xml
-------------------------------------------------------------------------------
...
<liferay.auto.deploy.dir>PATH<liferay.auto.deploy.dir>
...
