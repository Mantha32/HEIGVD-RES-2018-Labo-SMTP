#!/bin/bash

#This script is runned in ubuntu-like OS
#Description: Set up the mockmock server in the docker container
#first argument: the main directory is used to host the files that we need
#to build the docker image and container
#second argument: the file .jar source path
#third argument: the docker source path 
#Author: Iando Rafidimalala

#Tips
# mainDirectory= /tmp/mockmock
# mockSourceJAR = /tmp/xxx.jar
#hold on Dockerfile in /tmp
# dockerfileSource = /tmp/Dockerfile

mainDirectory=$1
mockSourceJAR=$2
dockerfileSource=$3


subDirSource=$mainDirectory
subDirSource+="/src"
echo "1. Create the host directory and the subdirectory src: $subDirSource"

mkdir -p $subDirSource

echo "2. Copy of the jar in $subDirSource"
cp -f $mockSourceJAR $subDirSource
	
echo "3. Copy the dockerfile from the main project directory"
	
cp -f $dockerfileSource $mainDirectory

echo "4. Point in the main project directory"
   
cd $mainDirectory

echo "5. Build the mockmock server image"

docker build --no-cache -t mockmock .

echo "6. Run the mockmock server in the container"
echo "The container's name is mockmock-container"
echo "the image's name is mockmock"

# The option --name assign a name to the container
# The option -p or --publish publish a container's port to the host
#Map the TCP port 8282 in the container to port 8484 port on the docker host
#Map the TCP port 2525 in the container to port 2727 port on the docker host

docker run --name mockmock-container -p 8484:8282 -p 2727:2525 mockmock
