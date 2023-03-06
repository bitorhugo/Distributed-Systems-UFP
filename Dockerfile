FROM alpine
RUN apk add --no-cache python3
RUN apk add --no-cache openjdk11
RUN apk add --no-cache bash
WORKDIR /SD    
COPY . .
WORKDIR /SD/src/edu/ufp/inf/sd/rmi/_04_diglib/runscripts
CMD ["/bin/bash", "/SD/src/edu/ufp/inf/sd/rmi/_04_diglib/runscripts/deploy.sh"]