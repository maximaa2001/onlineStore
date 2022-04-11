//const http = require('http');
const express = require('express');
const staticRouting = require('./routing/static/staticRouting')


const PORT = 3001;

const app = express();

staticRouting.routing(app)

app.listen(PORT);