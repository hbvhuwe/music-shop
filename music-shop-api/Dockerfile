FROM gradle:4.10.0

COPY . /app
WORKDIR /app

EXPOSE 1234

USER root
RUN chown -R gradle /app
USER gradle

RUN ["gradle", "bootJar"]
