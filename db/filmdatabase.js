/**
 * Created by tom-n on 13-6-2017.
 */
var mysql = require('mysql');
var config = require('./../config/config.json')

var connection = mysql.createConnection({
    host     : process.env.DB_HOST || config.dbHost,
    user     : process.env.DB_USER || config.dbUser,
    password : process.env.DB_PASSWORD,
    database : process.env.DB_DATABASE || config.dbDatabase
});



connection.connect(function (error) {
    if(error){
        console.log(error);
    }else{
        console.log("Connected to " + config.dbHost + ": " + config.dbDatabase);
    };
});

module.exports = connection;