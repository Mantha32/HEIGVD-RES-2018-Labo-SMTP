# HEIGVD-RES-2018-Labo-SMTP

## The project description 
This project is an application designed for RES course at HES-SO University of Applied Sciences Western Switzerland.
It is a Java application to communicate with an SMTP server using Socket API. 
It sends forged mails to a group of persons (victims). The list of victims and the messages to send are defined in external files.
Each group contains one sender and the leftover is the recipient. 

## Instruction
This project is a **IDEA Project**.

The user will be able to send forged e-mails to a **group of victims**. The list of victims' e-mails can be modified in `victims.txt`file and the messages to send in `messages.txt` file.

E-mails can be sent either using a **MockMock server** (for tests) or a real **SMTP server**.

#### MockMock Server
If you want to test the application before sending real mails, you can use [mockmok](https://github.com/dc55028/MockMock).
To run the `MockMock Server`, you can follow the instruction on below. Therefore, we have prepared a [docker](https://www.docker.com/) image.
In order to run it, all you have to do is to run these scripts: docker-build.sh and docker-run.sh

### Real SMTP Server
If you are sure to not to spam your SMTP Server, you can just change the `config.proprieties`. It  contains the **server address** and the **SMTP port**.

### How to configure our tool 
There are three files to configure: 
* `victims.txt` : the list of persons (e-mails) 
* `messages.txt` : the list of messages to be sent to the victims. 
* `config.proprieties` : the configuration of the SMTP Server (`server address` , `SMTP Pott`, `number of groups` and `CC reciver`)

## Installation
* Clone this repos.
* Run the **MockMock server**.
* Run the application after modifing files listing above. 

Now you are able to send e-mails to the group of victims. The sender will be chosen randmoly.
