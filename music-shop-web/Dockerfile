FROM node:10.9.0 as build-stage

WORKDIR /app
COPY ./*.json /app/
RUN npm install
COPY ./src/ /app/src/
RUN npm run build -- --output-path=./dist/out

FROM httpd:2.4.34
COPY --from=build-stage /app/dist/out/ /usr/local/apache2/htdocs/
