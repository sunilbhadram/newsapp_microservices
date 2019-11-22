FROM node:12.8.1
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY dist /usr/src/app/dist
RUN npm install -g http-server
CMD ["http-server","dist/Newsapp","-p 4200"]
