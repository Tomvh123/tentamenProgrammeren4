
var express = require('express');
var routes = express.Router();
var db = require('../db/filmdatabase');

//
// Geef een lijst van alle films.

routes.get('/film', function(req, res) {
    res.contentType('application/json');

    db.query('SELECT * FROM film', function(error, rows, fields) {
        if (error) {
            res.status(401).json(error);
        } else {
            res.status(200).json({ result: rows });
        };
    });

routes.get('/film/:id', function (req, res) {


})


});