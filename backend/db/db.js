const mysql = require('mysql')

const connection = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: 'root',
    database: 'online_store_dev'
})

connection.connect((err) => {
    if(err){
       return console.log("error")
    }
    else{
        return  console.log("success")
    }
})

module.exports = connection