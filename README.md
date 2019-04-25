# Coding challenge Follower Maze

The [instructions.md](instructions.md) contains all the details for the task.

My solution implements a client server app.

![diagram](diagram.jpg)

## Prerequisites

The implementation was done using SCALA with the following tools:

| Tools | Version |
|---|---|
| Scala | 2.12 |
| IntelliJ | Community Edition 2018.3.1 |

Run the Scala Build Tools (SBT) to execute individual tasks:

| Command | Task |
|---|---|
|`build`| to create an executable JAR file |
|`test`| to run the implemented unit tests |
|`package`| to package everything into a JAR file |
|`run`| to execute the application |

Finally you can run the application by typing:

```
scala target/scala-2.12/follower-maze-scala_2.12-0.1.jar
```
to run the application.

The implementation was made on a Windows 10 notebook and I tested it on an EC2 AWS instance running Linux.

## Making an SSH connection to your EC2 instance

```
ssh -i .\my-emkay-dev-ec2-instance-key-pair.pem ec2-user@xxx-xx-xx-xxx-xxx.compute-1.amazonaws.com
```

## JAVA

The Java implementation can be found under [https://github.com/makomweb/follower-maze-java](https://github.com/makomweb/follower-maze-java).
