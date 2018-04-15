#!/bin/bash

#This script is runned in ubuntu-like OS 
#Description: Stop and delete the container. Delete the image
#We assume that you have github on your work station and maven
#Author: Iando Rafidimalala

container-name = $1
image-name = $2

docker stop $container-name

docker rm $container-name

docker rmi $image-name

