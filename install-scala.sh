#! /bin/bash
SCALA_VERSION="2.12.8"
wget http://downloads.typesafe.com/scala/${SCALA_VERSION}/scala-${SCALA_VERSION}.tgz
tar -xzvf scala-${SCALA_VERSION}.tgz
rm -rf scala-${SCALA_VERSION}.tgz
echo "export SCALA_HOME=/home/ec2-user/scala-${SCALA_VERSION}" >> ~/.bashrc
echo "export PATH=$PATH:/home/ec2-user/scala-${SCALA_VERSION}/bin" >> ~/.bashrc
source ~/.bashrc