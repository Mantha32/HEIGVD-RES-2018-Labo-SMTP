# Client application TCP  in JAVA

SMTP-Prank is a Java application to communicate with a SMTP server using the Socket API. It sends forged mails to a group of persons (victims). The list of victims and the messages to send are defined in external files. 

## Instructions

This project is a **IDEA Project**.

The user will be able to send forged e-mails to a **group of victims**. The list of victims' e-mails can be modified in `victims.txt`file and the messages to send in `messages.txt` file.

E-mails can be sent either using a **MockMock server** (for tests) or a real **SMTP server**.
If you want to test it before sending real mails, you can use [mockmok](https://github.com/dc55028/MockMock).
The `config.proprieties` contains the **server address**, the **SMTP port**, the **number of groups** and **CC reciver**. 

### Installation
* Clone this repos.
* Run the **MockMock server**.
* Run the application after modifing files listing above. 

Now you are able to send e-mails to the group of victims. The sender will be chosen randmoly.
