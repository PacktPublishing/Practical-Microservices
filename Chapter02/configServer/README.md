<h1>Config Server</h1>

Config server is responsible to give configuration files to the calling service. 
It expect 2 parameters  profile name and application name. 
These 2 proeprties shouold be set in the bootstrap.properties of calling component <br>
spring.application.name=service<br>
spring.profiles.active=dev<br>

but one has to set the location of config server also in property file.<br>
spring.cloud.config.uri=http://localhost:8888
