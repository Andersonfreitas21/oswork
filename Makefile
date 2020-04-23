NAME = "to-be-changed"
DATE_WITH_TIME := $(shell /bin/date "+%Y%m%d%H%M%S")
FILE_DIFF = src/main/resources/db/changelog/to-be-changed.xml

liquibaseGenerateChangeLogEntity:
	mvn liquibase:diff -DdiffChangeLogFile=src/main/resources/db/changelog/changes/${DATE_WITH_TIME}-${NAME}.xml

liquibaseDiff:
ifeq ($(shell test -e $(FILE_DIFF) && echo -n yes),yes)
	> src/main/resources/db/changelog/to-be-changed.xml
endif
	mvn liquibase:diff -f pom.xml