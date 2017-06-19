
var express = require('express');
var router = express.Router();
var db = require('../db/filmdatabase');

//
// Geef een lijst van alle films.

router.get('/film', function(req, res) {
    res.contentType('application/json');

    db.query('SELECT * FROM film', function (error, rows, fields) {
        if (error) {
            res.status(401).json(error);
        } else {
            res.status(200).json({result: rows});
        };
    });
});

router.get('/:id', function (req, res) {

    var film_id = req.params.id;

    res.contentType('application/json');

    db.query('SELECT film.film_id, film.title, inventory.inventory_id, rental.rental_id, rental.return_date, film.rental_duration, film.rental_rate ' +
        'FROM film ' +
        'INNER JOIN inventory ' +
        'ON film.film_id = inventory.film_id ' +
        'LEFT JOIN rental ' +
        'ON rental.inventory_id = inventory.inventory_id ' +
        'WHERE film.film_id =?', [film_id], function(error, rows, fields) {
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
            "Description": "Welcome to the film API"
        });

    });

module.exports = router;

