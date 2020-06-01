//declare mqtt framework
var mqtt = require('mqtt')

//connect to a broker
var client = mqtt.connect('mqtt://test.mosquitto.org')

//publish humidity data
client.on('connect', function() {
	setInterval(function() {
		client.publish('13469_13302-humidity', 'humidity')
		}, 1000)
})
