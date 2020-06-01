var lowerThreshold = 85;
var upperThreshold = 95;
var pump = 0;

//import framework
var mqtt = require('mqtt')

//declare the online broker
var client = mqtt.connect('mqtt://test.mosquitto.org')

//subscribe the humidity topic
client.on('connect', function() {
	client.subscribe('13469_13302-humidity')
})

//message handling
client.on('message', function(topic, message) {
	console.log("current humidity: " + message.toString() + " %")
	if (message < lowerThreshold) {
		if (pump == 1) {
			console.log("pump status: " + pump)
		} 
		else {
			pump = 1
			console.log("pump set to on, pump status: " + pump)
		}
	}
	else if (message >= lowerThreshold && message < upperThreshold) {
		console.log("pump status: " + pump)
	}
	else {
		if (pump == 0) {
			console.log("pump status: " + pump)
		}
		else {
			pump = 0
			console.log("light set to off, pump status: " + pump)
		}
	}
})
