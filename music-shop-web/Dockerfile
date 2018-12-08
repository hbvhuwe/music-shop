FROM node:10.13.0 as build-stage

WORKDIR /app
COPY ./*.json /app/
RUN npm install
COPY ./src/ /app/src/
RUN npm run build -- --output-path=./dist/out

FROM nginx:1.15.7
COPY --from=build-stage /app/dist/out/ /usr/share/nginx/html
