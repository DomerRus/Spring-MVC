FROM maven
RUN mkdir --parents /usr/src/app
ADD . /usr/src/app
WORKDIR /usr/src/app
RUN mvn clean install
CMD mvn jetty:run