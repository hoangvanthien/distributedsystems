//declare mqtt framework
var mqtt = require('mqtt')

//connect to a broker
var client = mqtt.connect('mqtt://test.mosquitto.org')

//publish humidity data
client.on('connect', function() {
	console.log("Connected!");
	setInterval(function() {
		var x = 80 + Math.round(Math.random() * 20);
		process.stdout.write(x + ' ');
		client.publish('13469_13302-humidity', x.toString())
	}, 1000)
})
