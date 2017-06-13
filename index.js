/**
 * Created by tom-n on 13-6-2017.
 */
var http        = require('http');
var express     = require('express');
var config      = require('./config/config.json');
var bodyParser  = require('body-parser');
var routeFilm   = require('./routes/film.routes.v1');
var routeRental = require('./routes/rental.routes.v1');

//create application
var app = express();

app.use(bodyParser.urlencoded({'extended':'true'}));
app.use(bodyParser.json());
app.use(bodyParser.json({type: 'application/vnd.api+json'}));

app.set('PORT', config.webPort);

app.all('*', function (req, res, next) {
    console.log(req.method + " " + req.url);
    next();
});

app.use('/api/v1/films', routeFilm);
app.use('/api/v1/rentals', routeRental);

app.get('*', function (req, res, next) {
    res.contentType('application/json');
    res.json({
        "filmRoute"  : "/api/v1/films/",
        "rentalRoute": "/api/v1/rentals/"
    })

});

var port = process.env.PORT || app.get('PORT');

app.listen(port, function () {
    console.log('The magic happens at http://localhost:' + port);
});

module.exports = app;