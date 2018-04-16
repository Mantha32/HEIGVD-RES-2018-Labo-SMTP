# latest LTS
FROM ubuntu:latest

# for SMTP
EXPOSE 25 
# for the web interface
EXPOSE 8282

RUN \
apt-get update && apt-get install -y \
apt-utils \
wget \
openjdk-8-jre 

RUN cd /opt/ && \
    mkdir mockmock && \
    cd mockmock && \
    wget https://github.com/tweakers-dev/MockMock/blob/master/release/MockMock.jar?raw=true -O MockMock.jar

CMD java -jar /opt/mockmock/MockMock.jar