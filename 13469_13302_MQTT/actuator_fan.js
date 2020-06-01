var lowerThreshold = 10;
var upperThreshold = 17;
var fan = 0;

//import framework
var mqtt = require('mqtt')

//declare the online broker
var client = mqtt.connect('mqtt://test.mosquitto.org')

//subscribe the temperature topic
client.on('connect', function() {
	client.subscribe('13469_13302-temperature')
})

//message handling
client.on('message', function(topic, message) {
	console.log("current temperature: " + message.toString() + " C")
	if (message < lowerThreshold) {
		if (fan == 0) {
			console.log("fan status: " + fan)
		}
		else {
			fan = 0
			console.log("fan set to off, fan status: " + fan)
		}
	}
	else if (message >= lowerThreshold && message < upperThreshold) {
		console.log("fan status: " + fan)
	}
	else {
		if (fan == 1) {
			console.log("fan status: " + fan)
		}
		else {
			fan = 1
			console.log("fan set to on, fan status: " + fan)
		}
	}
})
