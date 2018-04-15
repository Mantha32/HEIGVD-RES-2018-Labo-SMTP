#!/bin/bash

#This script is runned in ubuntu-like OS 
#Description: build the mockmock server using the remote source hosting in github
#We assume that you have github on your work station and maven
#Author: Iando Rafidimalala

#Try it in /tmp

git clone git@github.com:Mantha32/MockMock.git

cd MockMock/

mvn clean install

