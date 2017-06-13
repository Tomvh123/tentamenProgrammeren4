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

router.put('/rental/:id/:inventoryid', function(req, res) {

    var inventory_id    = req.params.inventoryid;
    var customer_id     = req.params.id;
    var rental_date     = req.body.rentaldate;
    var return_date     = req.body.returndate;


    res.contentType('application/json');
    db.query('UPDATE rental SET rental_date=? , return_date =? WHERE inventory_id =? AND customer_id =?;  ',[rental_date, return_date, inventory_id, customer_id],  function(error, rows, fields) {
        if (error) {
            res.status(401).json(error);
        } else {
            res.status(200).json({ result: rows });
        };
    });
});

router.delete('/rental/:id/:inventoryid', function(req, res) {

    var inventory_id    = req.params.inventoryid;
    var customer_id     = req.params.id;
    res.contentType('application/json');
    db.query('DELETE FROM rental WHERE inventory_id=? AND customer_id=? ',[inventory_id, customer_id] ,  function(error, rows, fields) {
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

router.post('/login', function(req, res) {

    console.dir(req.body);

    var username = req.body.username;
    var password = req.body.password;


    if (username == _dummy_username && password == _dummy_password) {
        var token = auth.encodeToken(username);
        res.status(200).json({
            "token": token,
        });
    } else {
        console.log('Input: username = ' + username + ', password = ' + password);
        res.status(401).json({ "error": "Invalid credentials, bye" })
    }

});

module.exports = router;