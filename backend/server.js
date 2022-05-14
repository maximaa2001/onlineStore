//const http = require('http');
const express = require('express');
const staticRouting = require('./routing/static/staticRouting')
const cors = require('cors');


const PORT = 3001;

const app = express();

app.use(cors());

staticRouting.routing(app)

app.listen(PORT);