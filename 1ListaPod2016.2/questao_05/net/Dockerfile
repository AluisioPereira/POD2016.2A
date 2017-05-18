#
#  -----------------------------------------------
#  Commands
#  -----------------------------------------------
#  For build container:
#  $ docker build --tag=pod:<version> .
#
#  For run app in container
#  $ docker run -d -p <host_port>:<app_port> -t pod:<version>   
#  -----------------------------------------------
#
FROM maven:3.3.9-jdk-8

# ----------                                                                                                 
# OPERATIONS                                                                                                  
# ----------                                                                                   
ADD . /opt/app
RUN cd /opt/app && mvn compile package -DskipTests=true
RUN chmod +x /opt/app/start.sh

CMD touch /opt/shared/sum.txt && touch /opt/shared/diff.txt && /opt/app/start.sh