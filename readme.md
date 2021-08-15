## Project Arch (3)
	- MicroService:  Java - SpringBoot
## Description
    - Send a microservice implementation using mqQueue in springboot app. 
	
## Owner

	- Carlos Roberto Medeiros de Lima

### Tools and Technologies Used ###
	
	- Docker Container
	- MQSeries inside container docker
	- SpringBoot Application 
		
	#Environment 
		In a first terminal, inside sistemaa project run:
			- docker-compose up
			
		In a second terminal, inside sistemaa-mensageria project run:
			- Inside the directory sistemaa-mensageria
			mvn package spring-boot:run
			
		IBM-MQ Console
			- https://localhost:9443/ibmmq/console
			user:admin 
			password:passw0rd	