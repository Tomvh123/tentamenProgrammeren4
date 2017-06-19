/**
 * Created by tom-n on 13-6-2017.
 */
var settings = require('../config/config.json');
const moment = require('moment');
const jwt = require('jwt-simple');


function encodeToken(username) {
    const payload = {
        exp: moment().add(10, 'days').unix(),
        iat: moment().unix(),
        sub: username
    };
    return jwt.encode(payload, settings.secretKey);
}

function decodeToken(token, cb) {
    try{
        const payload = jwt.decode(token, settings.secretKey);
        //check if the token has expired.
        const now = moment().unix();

        //check exprired
        if (now > payload.exp){
            console.log('Token has expired.');
        }
        //return
        cb(null, payload);
    }catch(err){
        cb(err, null);
    };
};

module.exports = {
    encodeToken,
    decodeToken
};