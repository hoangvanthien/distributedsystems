//declare mqtt framework
var mqtt = require('mqtt')

//connect to a broker
var client = mqtt.connect('mqtt://test.mosquitto.org')

//publish luminosity data
client.on('connect', function() {
	console.log("Connected!");
	setInterval(function() {
		var x = 700 + Math.round(Math.random() * 900);
		process.stdout.write(x + ' ');
		client.publish('13469_13302-luminosity', x.toString())
	}, 1000)
})
