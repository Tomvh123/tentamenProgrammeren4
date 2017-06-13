/**
 * Created by tom-n on 13-6-2017.
 */
var http        = require('http');
var express     = require('express');
var config      = require('./config/config.json');
var bodyParser  = require('body-parser');
var routeFilm   = require('./routes/film.routes.v1');
var routeRental = require('./routes/rental.routes.v1');
var auth        = require('./authentication/auth');
var db          = require('./db/filmdatabase')

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

app.all( new RegExp("[^(\/login)]"), function(req, res, next) {
    var token = (req.header('X-Access-Token')) || '';

    auth.decodeToken(token, function(err, payload){
        if(err){
            console.log('Error handler: ' + err.message);
            res.status((err.status || 401)).json({ error: new Error("Not authorised").message});
        }else{
            next();
        }
    });
});

app.post('/login', function(req, res) {
    console.dir(req.body);

    var username = req.body.username;
    var password = req.body.password;

    db.query('SELECT * FROM customer WHERE username=? AND password=?', [username, password], function(error, rows, fields) {
        if (error) {
            res.status(401).json(error);
        } else{
            if(!rows.length) {
                res.status(401).json({ "error": "Invalid credentials, bye"});
            }else{
                var first_name = rows[0].first_name;
                var last_name = rows[0].last_name;
                console.log(first_name + " " + last_name);
                var token = auth.encodeToken(username);
                   res.status(200).json({
                        "token": token,
                    });
            }
        }
    });


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