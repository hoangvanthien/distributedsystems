//declare mqtt framework
var mqtt = require('mqtt')

//connect to a broker
var client = mqtt.connect('mqtt://test.mosquitto.org')

//publish luminosity data
client.on('connect', function() {
	setInterval(function() {
		client.publish('13469_13302-luminosity', (700 + Math.round(Math.random() * 900)).toString())
		}, 1000)
})
