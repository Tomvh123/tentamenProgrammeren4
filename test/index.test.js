process.env.NODE_ENV = 'test';
process.env.SECRET_KEY = 'test';
var chai = require('chai');
var chaiHttp = require('chai-http');
var server = require('../server');
var should = chai.should();

chai.use(chaiHttp);

describe('Register, login and deleting customers', function () {

    it('should return an error on GET at /api/v1/login in a json object including a error', function (done) {
        chai.request(server)
            .get('/api/v1/login')
            .auth('thisisaemail@gmail.com', 'thisisapassword')
            .end(function (err, res) {
                res.should.have.status(401);

                res.should.be.json;
                res.body.should.be.an('object');
                res.body.should.have.property('error').that.is.a('string');
                res.body.error.should.equal('Invalid credentials');

                done();
            });
    });

    it('should return an error on POST at /api/v1/login for an invalid email', function (done) {
        chai.request(server)
            .post('/api/v1/register')
            .send({email: 'notaemailaddress', password: 'thispassword'})
            .end(function (err, res) {
                res.should.have.status(401);
                res.should.be.json;
                res.body.should.be.a('object');
                res.body.should.have.property('error').that.is.a('string');
                res.body.error.should.equal('Invalid credentials');
                done();
            });

    });

    it('should return an error on POST at /api/v1/login when given a invalid password', function (done) {
        chai.request(server)
            .post('/api/v1/register')
            .send({email: 'thisisaemail@gmail.com', password: ''})
            .end(function (err, res) {
                res.should.have.status(401);
                res.should.be.json;
                res.body.should.be.a('object');
                res.body.should.have.property('error').that.is.a('string');
                res.body.error.should.equal('Invalid credentials');
                done();
            });

    });

    it('should return an object on POST at /api/v1/register when given valid email and password', function (done) {
        chai.request(server)
            .post('/api/v1/register')
            .send({email: 'thisisaemail@gmail.com', password: 'thisisapassword'})
            .end(function (err, res) {
                res.should.have.status(200);
                res.should.be.json;
                res.body.should.be.a('object');
                res.body.should.have.property('message').that.is.a('string');
                res.body.message.should.equal('Successfully created user');
                res.body.should.have.property('token').that.is.a('string');
                res.body.should.have.property('email').that.is.a('string');
                res.body.email.should.equal('thisisaemail@gmail.com');
                done();
            });
    });


    it('should return an GET object', function (done) {
        chai.request(server)
            .get('/api/v1/login')
            .auth('mail@gmail.com', 'thispassword')
            .end(function (err, res) {
                res.should.have.status(200);
                res.should.be.json;
                res.body.should.be.an('object');
                res.body.should.have.property('token').that.is.a('string');
                res.body.should.have.property('email').that.is.a('string');
                res.body.email.should.equal('mail@gmail.com');
                done();
            });

    });

    it('should return an object on DELETE', function (done) {
        chai.request(server)
            .delete('/api/v1/delete')
            .auth('thisisaemail@gmail.com', 'thisisapassword')
            .end(function (err, res) {
                res.should.have.status(200);
                res.should.be.json;
                res.body.should.be.an('object');
                res.body.should.have.property('message').that.is.a('string');
                res.body.message.should.equal('Customer successfully deleted');
                done();
            });

    });

    it('should return an error on GET at /api/v1/login in a json object including a error', function (done) {
        chai.request(server)
            .get('/api/v1/login')
            .auth('thisisaemail@gmail.com', 'thisisapassword')
            .end(function (err, res) {
                res.should.have.status(401);
                res.should.be.json;
                res.body.should.be.an('object');
                res.body.should.have.property('error').that.is.a('string');
                res.body.error.should.equal('Invalid credentials');
                done();
            });
    });
});

describe('Films endpoints', function () {
    it('should return a json array with movies', function (done) {
        chai.request(server)
            .get('/api/v1/films')
            .end(function (err, res) {
                res.should.have.status(200);
                res.should.be.json;
                res.body.should.be.an('object');
                res.body.should.have.property('result').that.is.a('array');

                done();
            });
    });

    it('should return a json array with 1 movie', function (done) {
        chai.request(server)
            .get('/api/v1/films/1')
            .end(function (err, res) {
                res.should.have.status(200);
                res.should.be.json;
                res.body.should.be.an('object');
                res.body.should.have.property('result').that.is.a('array');
                res.body.result.should.have.length(1);

                done();
            });
    });
});