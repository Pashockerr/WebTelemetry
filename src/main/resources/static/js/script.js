const voltageText = "Напряжение(В) : ";
const currentText = "Ток(А) : ";

let h1v = 0, h1c = 0, h2v = 0, h2c = 0, bv = 0, bc = 0, h1vBounds = [0, 0], h1cBounds = [0, 0], h2vBounds = [0, 0], h2cBounds = [0, 0], bvBounds = [0, 0], bcBounds = [0, 0], h1vp = 0, h1cp = 0, h2vp = 0, h2cp = 0, bvp = 0, bcp = 0; //Создание переменных, хранящих числовые значения
let connectionLostIndicator = document.getElementById("connection-lost-indicator");
/*
    h1v - Напряжение на первом ВТЭ
    h1c - Ток на первом ВТЭ
    h2v - Напряжение на втором ВТЭ
    h2c - Ток на втором ВТЭ
    bv - Напряжение на батарее
    bc - Ток на батарее

    С окончание p - Проценты прогрессбаров

    С окончанием Bounds - минимальные и максимальные значения параметров(0й эл-т - минимальное значение, 1й эл-т - максимальное значение)
*/

//Получение эл-тов прогрессбаров
//#region Элементы прогрессбаров
let h1vProgressbar = document.getElementById("hydrogen1-voltage");
let h1cProgressbar = document.getElementById("hydrogen1-current");

let h2vProgressbar = document.getElementById("hydrogen2-voltage");
let h2cProgressbar = document.getElementById("hydrogen2-current");

let bvProgressbar = document.getElementById("battery-voltage");
let bcProgressbar = document.getElementById("battery-current");
//#endregion

//#region Элементы подписей
let h1vLabel = document.getElementById("hydrogen1-voltage-label");
let h1cLabel = document.getElementById("hydrogen1-current-label");

let h2vLabel = document.getElementById("hydrogen2-voltage-label");
let h2cLabel = document.getElementById("hydrogen2-current-label");

let bvLabel = document.getElementById("battery-voltage-label");
let bcLabel = document.getElementById("battery-current-label");
//#endregion

//Обновление надписей в соответствии с числовыми значениями раз в 100мс
setInterval(function(){
    h1vLabel.innerHTML = `${voltageText}<h2>${h1v}</h2>`;
    h1cLabel.innerHTML = `${currentText}<h2>${h1c}</h2>`;

    h2vLabel.innerHTML = `${voltageText}<h2>${h2v}</h2>`;
    h2cLabel.innerHTML = `${currentText}<h2>${h2c}</h2>`;

    bvLabel.innerHTML = `${voltageText}<h2>${bv}</h2>`;
    bcLabel.innerHTML = `${currentText}<h2>${bc}</h2>`;

    h1vProgressbar.style.width = `${h1vp}%`;
    h1cProgressbar.style.width = `${h1cp}%`;

    h2vProgressbar.style.width = `${h2vp}%`;
    h2cProgressbar.style.width = `${h2cp}%`;

    bvProgressbar.style.width = `${bvp}%`;
    bcProgressbar.style.width = `${bcp}%`;
}, 100);

//Обновление значений
setInterval(function(){
    let xhr = new XMLHttpRequest();
    xhr.responseType = "json";
    xhr.open("GET", "/api/");
    xhr.onloadend = (e) =>{
        if(xhr.status == 0){
            connectionLostIndicator.style.visibility = "visible";
        }
        else if(connectionLostIndicator.style.visibility == "visible"){
            connectionLostIndicator.style.visibility = "hidden";
        }
        var json = xhr.response;

        h1v = xhr.response["hydrogen1Voltage"];
        h1vBounds[0] = h1v < h1vBounds[0] ? h1v : h1vBounds[0];
        h1vBounds[1] = h1v > h1vBounds[1] ? h1v : h1vBounds[1];

        h1c = xhr.response["hydrogen1Current"];
        h1cBounds[0] = h1c < h1cBounds[0] ? h1c : h1cBounds[0];
        h1cBounds[1] = h1c > h1cBounds[1] ? h1c : h1cBounds[1];

        h2v = xhr.response["hydrogen2Voltage"];
        h2vBounds[0] = h2v < h2vBounds[0] ? h2v : h2vBounds[0];
        h2vBounds[1] = h2v > h2vBounds[1] ? h2v : h2vBounds[1];

        h2c = xhr.response["hydrogen2Current"];
        h2cBounds[0] = h2c < h2cBounds[0] ? h2c : h2cBounds[0];
        h2cBounds[1] = h2c > h2cBounds[1] ? h2c : h2cBounds[1];

        bv = xhr.response["batteryVoltage"];
        bvBounds[0] = bv < bvBounds[0] ? bv : bvBounds[0];
        bvBounds[1] = bv > bvBounds[1] ? bv : bvBounds[1];

        bc = xhr.response["batteryCurrent"];
        bcBounds[0] = bc < bcBounds[0] ? bc : bcBounds[0];
        bcBounds[1] = bc > bcBounds[1] ? bc : bcBounds[1];

        h1vp = h1v / (Math.abs(h1vBounds[0]) + Math.abs(h1vBounds[1])) * 100;
        h1cp = h1c / (Math.abs(h1cBounds[0]) + Math.abs(h1cBounds[1])) * 100;
    
        h2vp = h2v / (Math.abs(h2vBounds[0]) + Math.abs(h2vBounds[1])) * 100;
        h2cp = h2c / (Math.abs(h2cBounds[0]) + Math.abs(h2cBounds[1])) * 100;
    
        bvp = bv / (Math.abs(bvBounds[0]) + Math.abs(bvBounds[1])) * 100;
        bcp = bc / (Math.abs(bcBounds[0]) + Math.abs(bcBounds[1])) * 100;
    };
    xhr.send();
}, 100);