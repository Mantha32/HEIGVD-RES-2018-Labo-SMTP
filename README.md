# HEIGVD-RES-2018-Labo-SMTP

## The project description 
This project is made for RES course at HES-SO University of Applied Sciences Western Switzerland.
we are intending for implementing SMTP client side. This project uses a partial implementation of the SMTP protocol. 
It allows you to send mail per group that you can costumize the message and the umber group itself.
Each group contains one sender and the leftover is the recipient. This application automatize the process.

## set up a mock SMTP server with Docker
### Instruction


## How to configure our tool 

## How to run a prank campaign


## Implmentation description of the client side
### Set up the client side using the configuration file

### Protocol based on SMTP protocol

### The smtp message repsonse server handler

### the mail message

### The main class smtp client







### comdande utilisé pour l'initialisation connexion
Dans l'ordre
#### Protocole TCP 
-  EHLO nom --> nomd du domaine
-  MAIL FROM: source venir d'une certain pers , pas verifier --> SMTP command
-  RCPT TO: destinataire, !! adresse de la destinataire, == une personne reçoit le message

-  DATA : les données, corps du messages ou il n'y pas de contrôle
From: mail@gmial.com
To: destinataire@gmail.com
Subject: blabla


Envoyer le username et mot de passe en base 64!!
coder en base 64  un char: en ligne de commande: echo -n 'monmessage' | base64
