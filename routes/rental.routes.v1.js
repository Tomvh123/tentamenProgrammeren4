var express = require('express');
var router = express.Router();
var db = require('../db/filmdatabase');

router.get('/:userid', function (req, res) {

    var user_id = req.params.userid;

    res.contentType('application/json');

    db.query('SELECT film.film_id, film.title, film.description, film.release_year, film.language_id, film.original_language_id, film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features, film.last_update, inventory.inventory_id ' +
        'FROM customer ' +
        'INNER JOIN rental ' +
        'ON customer.customer_id = rental.customer_id ' +
        'INNER JOIN inventory ' +
        'ON rental.inventory_id = inventory.inventory_id ' +
        'INNER join film ' +
        'ON film.film_id = inventory.film_id ' +
        'WHERE customer.customer_id =?', [user_id], function(error, rows, fields) {
        if (error) {
            res.status(401).json(error);
        } else {
            res.status(200).json({ result: rows });
        };
    });
});

router.post('/:userid/:inventoryid', function(req, res) {

    var inventory_id    = req.params.inventoryid;
    var customer_id     = req.params.userid;
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

router.put('/:userid/:inventoryid', function(req, res) {

    var inventory_id    = req.params.inventoryid;
    var customer_id     = req.params.userid;
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

router.delete('/:userid/:inventoryid', function(req, res) {

    var inventory_id    = req.params.inventoryid;
    var customer_id     = req.params.userid;
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



module.exports = router;