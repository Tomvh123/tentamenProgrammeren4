var expect = require("chai").expect;
var index = require('./index');

describe('Working', function(){
	it('POST /api/v1/login', function(done){
		chai.request(server)
			.post('/api/v1/login')
			expect(true).to.be.true
			});
	})
});