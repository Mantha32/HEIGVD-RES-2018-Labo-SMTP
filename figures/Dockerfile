FROM java:openjdk-8-jre-alpine 


LABEL description="Create the mockmock server mimics the smtp server \
Using this server instead of the real one is safe to test the smtp client side \
You can change the port expose by the mockmock as you want to communicate with the smtp server.\
The default smtp port is 2525. Like smpt port , you can change the http port as you will.\
The defualt port is 8282. "
LABEL authors="Yosra Harbaoui, Iando Rafidimalala"
LABEL version="1.0"


#
# Replace the directory with your own directory that stands the source file
# example /tmp/mockmock/src
#
ENV DIRECTORY /tmp/mockmock/src/

ADD src DIRECTORY

WORKDIR DIRECTORY

#The container listen in this specific ports
#The port 2525/tcp is used to smtp server
#The port 8282/tcp is uses to http
EXPOSE 2525 
EXPOSE 8282

#The default argument for the ENTREPOINT
#The argument "-p 2525" stand for the port the mockmock listen to do the smtp conversation with the smpt client
# The other argument "-h 8282" stand for the port the mockmock listen to provide the statement of the mockmock server

ENTRYPOINT ["java","-jar","MockMock-1.4.0.one-jar.jar", "-p", "2525", "-h 8282"]
