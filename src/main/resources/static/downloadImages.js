#! /bin/node

const http = require('http'),
    Stream = require('stream').Transform,
    fs = require('fs/promises');

let dataObject = {}

;(async () => {
  const promises = []

  async function download(url, path, name) {
    return new Promise((resolve, reject) => {
      http.request(url, function(response) {
        var data = new Stream();

        response.on('data', function(chunk) {
          data.push(chunk);
        });

        response.on('end', function() {
          resolve(fs.writeFile(`${path}/${name}`, data.read()));
        });
      })
          .end()
          .on('error', err => {
            reject(err)
          });
    })
  }

  async function getJson(path) {
    return new Promise((resolve, reject) => {
      http.request(path, async function(response) {
        const dataStream = new Stream()

        response.on('data', function(chunk) {
          dataStream.push(chunk)
        });

        response.on('end', function() {
          resolve(JSON.parse(dataStream.read().toString()))
        })
      })
          .end()
          .on('error', err => {
            reject(err)
          });
    })
  }

  const url = 'http://ddragon.leagueoflegends.com/cdn/11.12.1/data/en_US/champion.json'
  dataObject = (await getJson(url)).data

  Object.keys(dataObject).forEach(key => {
    const championKey = `${dataObject[key].key}.png`
    const imgName = dataObject[key].image.full
    const championImg = `http://ddragon.leagueoflegends.com/cdn/11.12.1/img/champion/${imgName}`
    const path = '/home/jlenon7/Downloads/demo-mvc/src/main/resources/static/image/champions/'

    promises.push(download(championImg, path, championKey))
  })

  await Promise.all(promises)

  process.stdout.write('All images saved to path!')
})()
