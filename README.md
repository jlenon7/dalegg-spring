# Dale.gg 🎮

> MVC project with Spring using Riot API

[![GitHub followers](https://img.shields.io/github/followers/jlenon7.svg?style=social&label=Follow&maxAge=2592000)](https://github.com/jlenon7?tab=followers)
[![GitHub stars](https://img.shields.io/github/stars/jlenon7/dalegg-spring.svg?style=social&label=Star&maxAge=2592000)](https://github.com/secjs/base/stargazers/)

<p>
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/secjs/base?style=for-the-badge&logo=appveyor">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/secjs/base?style=for-the-badge&logo=appveyor">

  <img alt="License" src="https://img.shields.io/badge/license-MIT-brightgreen?style=for-the-badge&logo=appveyor">
</p>

The intention behind this repository is to test `Spring MVC` projects consuming Riot API.

<img src=".github/images/dalegg.png" width="200px" align="right" hspace="30px" vspace="100px">

## IMPORTANT

> You need to create an account in Riot developer portal to get an API Key and set it inside of RiotApiServiceImpl

[Click here to create you account in Riot API!](https://developer.riotgames.com/)

<p align="center">
    <img src=".github/images/api-key.png" width="400px">
</p>

## Running project local

> First run an instance of PostgreSQL, you can use this command to run with docker.

```bash
docker run --name postgresql -e POSTGRES_PASSWORD=root -p 5433:5432 -d postgres
```

> Then build the project using Maven

```bash
mvn clean package
```

> Then run the .jar file created by Maven

```bash
java -jar target/dale-gg-1.0.0-SNAPSHOT.jar
```

---

## Running project with docker-compose

> First change the url of the database inside application.properties to dalegg-db instead of localhost

<p align="center">
    <img src=".github/images/app-properties.png" width="600px">
</p>

> Then run the project using docker-compose

```bash
docker-compose up -d --build
```

---

## Project home example

<p align="center">
    <img src=".github/images/project.png" width="1000px">
</p>

---

Made with 🖤 by [jlenon7](https://github.com/jlenon7) :wave:
