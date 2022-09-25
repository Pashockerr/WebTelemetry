let startButton = document.getElementById("start-button");
let stopButton = document.getElementById("stop-button");
let resetButton = document.getElementById("reset-button");
let label = document.getElementById("stopwatch-label");
let millis = 0;
let interval;

startButton.onclick = function(){
    resetButton.disabled = true;
    interval = setInterval(function(){
        millis += 100;
        label.innerText = `${Math.floor(millis/60000)}.${Math.floor((millis%60000) / 1000)}.${millis%1000/100}`;
    }, 100);
};

stopButton.onclick = function(){
    resetButton.disabled = false;
    clearInterval(interval);
};

resetButton.onclick = function(){
    millis = 0;
    label.innerText = "0.0.0";
};