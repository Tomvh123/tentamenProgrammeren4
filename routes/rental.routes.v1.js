var express = require('express');
var router = express.Router();
var db = require('../db/filmdatabase');

router.get('/rental/:id', function (req, res) {

    var rental_id = req.params.id;

    res.contentType('application/json');

    db.query('SELECT * FROM rental WHERE rental_id =?', [rental_id], function(error, rows, fields) {
        if (error) {
            res.status(401).json(error);
        } else {
            res.status(200).json({ result: rows });
        };
    });
});

router.post('/rental/:id/:inventoryid', function(req, res) {

    var user_id = req.params.id;
    var inventory_id = req.params.inventoryid;
    var query = {
        sql: 'INSERT INTO `rental` WHERE(`user_id, inventory_id`) VALUES (?, ?)',
        values: [user_id, inventory_id],
        timeout: 2000 // 2secs
    };

    console.dir(rental);
    console.log('Onze query: ' + query.sql);

    res.contentType('application/json');
    db.query(query, function(error, rows, fields) {
        if (error) {
            res.status(401).json(error);
        } else {
            res.status(200).json({ result: rows });
        };
    });
});