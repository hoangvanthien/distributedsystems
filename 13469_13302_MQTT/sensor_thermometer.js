//declare mqtt framework
var mqtt = require('mqtt')

//connect to a broker
var client = mqtt.connect('mqtt://test.mosquitto.org')

//publish temperature data
client.on('connect', function() {
	console.log("Connected!");
	setInterval(function() {
		var x = (5 + Math.round(Math.random() * 17));
		process.stdout.write(x + ' ');
		client.publish('13469_13302-temperature', x.toString())
	}, 1000)
})
