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

    var inventory_id    = req.params.inventoryid;
    var customer_id     = req.params.id;
    var return_date     = req.body.returndate;
    var staff_id        = req.body.staffid;

    res.contentType('application/json');
    db.query('INSERT INTO rental VALUES("*", "*", ?, ?, ?, ?, null)',[inventory_id, customer_id, return_date, staff_id] ,  function(error, rows, fields) {
        if (error) {
            res.status(401).json(error);
        } else {
            res.status(200).json({ result: rows });
        };
    });
});

router.get('*', function (req, res) {

    res.contentType('application/json')
    res.json({
        "Description": "Welcome to the rental API"
    });

});

module.exports = router;