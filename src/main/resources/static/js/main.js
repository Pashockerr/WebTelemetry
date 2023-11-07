var container = document.getElementById("container")

function updateValues(){
    let xhr = new XMLHttpRequest()
    xhr.open("GET", "/api/") 
    xhr.responseType = "json"
    xhr.send()
    xhr.onload = ()=>{
        let data = xhr.response
        els = []
        for(let i = 0; i < 2; i++){
            for(let j = 0; j < 4; j++){
                els.push(document.getElementById(`adc${i}_${j}`))
            }
        }

        els[0].innerText = data.adc1.a0
        els[1].innerText = data.adc1.a1
        els[2].innerText = data.adc1.a2
        els[3].innerText = data.adc1.a3

        els[4].innerText = data.adc2.a0
        els[5].innerText = data.adc2.a1
        els[6].innerText = data.adc2.a2
        els[7].innerText = data.adc2.a3
    }
}

setInterval(updateValues, 100)