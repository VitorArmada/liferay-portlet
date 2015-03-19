#!/bin/bash

mvn clean install -f pom.xml

mvn liferay:deploy -f hooks/hook-common-resources/pom.xml
mvn liferay:deploy -f hooks/hook-content-load/pom.xml
mvn liferay:deploy -f hooks/hook-url-intercept/pom.xml

mvn liferay:deploy -f services/service-content/pom.xml

mvn liferay:deploy -f portlets/portlet-content/pom.xml

mvn liferay:deploy -f impresa-theme/pom.xml
