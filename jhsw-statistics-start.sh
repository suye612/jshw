#!/bin/bash
echo Starting application 
nohup java -server -Xms512m -Xmx512m -XX:PermSize=64M -XX:MaxNewSize=128m -XX:MaxPermSize=128m -Djava.awt.headless=true -XX:+CMSClassUnloadingEnabled -XX:+CMSPermGenSweepingEnabled -jar -Dspring.config.location=/home/jhsw/config/application.yml jhsw-statistics.jar  > /dev/null 2>&1 
