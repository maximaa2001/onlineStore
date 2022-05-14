const db = require('../db/db')

module.exports.getCategories = (req, resp) => {
    db.query('SELECT * FROM `category_ref`', (err, row, field) => {
        resp.status(200)
        resp.setHeader('Content-Type', 'application/json');
        resp.end(JSON.stringify(row));
    })
   
}

module.exports.getCities = (req, resp) => {
    db.query('SELECT * FROM `city_ref`', (err, row, field) => {
        resp.status(200)
        resp.setHeader('Content-Type', 'application/json');
        resp.end(JSON.stringify(row));
    })
   
}