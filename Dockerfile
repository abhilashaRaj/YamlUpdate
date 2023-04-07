FROM openjdk:8 
EXPOSE 8080
 ADD target/yaml_update_poc.jar yaml_update_poc.jar 
 ENTRYPOINT ["java","-jar","/yaml_update_poc.jar"]