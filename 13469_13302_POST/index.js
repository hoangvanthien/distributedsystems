var bodyParser = require('body-parser');
var express = require('express');
var mongoose = require('mongoose');
var server_app = express();
var port = 5000;
var currency =  {'USD' : 1, 'EUR' : 1.1304, 'VND' : 23000};

server_app.use(bodyParser.json());
server_app.use(bodyParser.urlencoded({
    extended: true
}));

mongoose.connect('mongodb://ds1920:ds1920@ds133358.mlab.com:33358/agridb', {
    useNewUrlParser: true, useUnifiedTopology: true
}, function(err, database) {
    if (err) throw err;
    console.log("Database connected!");
});

let statusschema = new mongoose.Schema({
    _id: String,
    fbName: String,
    content: String,
    likes: Number,
    comments: Number,
    img: String
});

var myschema = mongoose.model('tblstatuses', statusschema);

server_app.get('/getData', function(req, res) {
    myschema.find({}, function(err, data) {
        if (err) return console.error(err);
        res.send(data);
    });
});

server_app.post('/currency-exchange/', function(request, response){
    var source = request.body.source
    var target = request.body.target
    var amount = request.body.amount

    if (source === undefined) {
        source = 'VND'
    }
    if (target === undefined) {
        target = 'USD'
    }
    if (amount === undefined) {
        amount = 1;
    }
    
    var exchangeRatio = (currency[target] / currency[source]) 

    response.send('Source Currency: ' + source 
    + '<br/>' + 'Target Currency: ' + target 
    + '<br/>' + 'Amount: ' + amount
    + '<br/>' + '1 ' + source + ': ' + exchangeRatio + ' ' + target
    + '<br/>' + '<br/>' + 'RESULT'
    + '<br/>' + amount + ' ' + source + ': ' + (amount * exchangeRatio) + ' ' + target)
});

server_app.get('/homepage', function(request, response) {
    response.send('Welcome to the homepage!')
});

server_app.post('/testPostMethod', function(req, res) {
    var strData = req.body.para;
    res.send('Your POST parameter is: ' + strData);
});

server_app.listen(port, function(){
    console.log('Server is listening at port ' + port + ' ...')
});
