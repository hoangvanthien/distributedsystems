//declare mqtt framework
var mqtt = require('mqtt')

//connect to a broker
var client = mqtt.connect('mqtt://test.mosquitto.org')

//publish temperature data
client.on('connect', function() {
	setInterval(function() {
		client.publish('13469_13302-temperature', (5 + Math.round(Math.random() * 17)).toString())
		}, 1000)
})
