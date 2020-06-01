var mqtt = require("mqtt");

var client = mqtt.connect("mqtt://test.mosquitto.org");
var data = {"temperature": null, "humidity": null, "luminosity": null};


client.on("connect", function() {
    // console.log("Connected");
    client.subscribe("13469_13302-temperature");
    client.subscribe("13469_13302-humidity");
    client.subscribe("13469_13302-luminosity");
});

function writeToConsole() {
    if (data["temperature"] == null) return;
    if (data["humidity"] == null) return;
    if (data["luminosity"] == null) return;
    var content = data["temperature"].padEnd(20) + data["humidity"].padEnd(20) + data["luminosity"].padEnd(20);
    console.log(content);
    data["temperature"] = data["humidity"] = data["luminosity"] = null;
};
var headers = "Temperature".padEnd(20) + "Humidity".padEnd(20) + "Luminosity".padEnd(20);
console.log(headers);

client.on("message", function(topic, message) {
    // console.log("Receive something");
    // console.log(topic.toString());
    // console.log(message.toString());
    var key = topic.toString().split("-").pop();
    data[key] = message.toString();
    writeToConsole();
});