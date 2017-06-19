

var chai = require('chai');
var chaiHttp = require('chai-http');
var server = require('/index');
var chould = chai.should();

chai.use(chaiHttp);

describe('Working', function(){
	it('POST /api/v1/login', function(done){
		chai.request(server)
			.post('/api/v1/login')
			.end( function(err, res){
				res.should.have.status(200);
				res.body.should.be.a('object');
				res.body
				done();
			});
	})
});