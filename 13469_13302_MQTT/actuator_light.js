var lowerThreshold = 800;
var upperThreshold = 1500;
var light = 0;

//import framework
var mqtt = require('mqtt')

//declare the online broker
var client = mqtt.connect('mqtt://test.mosquitto.org')

//subscribe the luminosity topic
client.on('connect', function() {
	client.subscribe('13469_13302-luminosity')
})

//message handling
client.on('message', function(topic, message) {
	console.log("current luminosity: " + message.toString() + " LUX")
	if (message < lowerThreshold) {
		if (light == 1) {
			console.log("light status: " + light)
		}
		else {
			light = 1
			console.log("light set to on, light status: " + light)
		}
	}
	else if (message >= lowerThreshold && message < upperThreshold) {
		console.log("light status: " + light)
	}
	else {
		if (light == 0) {
			console.log("light status: " + light)
		}
		else {
			light = 0
			console.log("light set to off, light status: " + light)
		}
	}
	//publish light status
	client.publish('13469_13302-light', light.toString())
})
