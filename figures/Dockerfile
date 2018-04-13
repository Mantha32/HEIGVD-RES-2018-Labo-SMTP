FROM java:openjdk-8-jre-alpine 


#
# Replace the directory with your own direroty that stands the source file
# example /tmp/mockmock/src
#
ENV DIRECTORY /tmp/mockmock/src/

ADD src DIRECTORY

WORKDIR DIRECTORY

ENTRYPOINT ["java","-jar","MockMock-1.4.0.one-jar.jar"]
