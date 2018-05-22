initCanvas();
var lineColor = null;
var lineWidth = null;
var boardFlag = true;

function initCanvas() {
    var oC = document.getElementById('c1');
    var oGC = oC.getContext('2d');
    //	var from = null;
    var to = null;
    var type = null;
    var msg = null;

    oC.onmousedown = function(ev) {
        var ev = ev || window.event;
        oGC.beginPath();
        oGC.moveTo(ev.clientX - oC.offsetLeft, ev.clientY - oC.offsetTop);
        document.onmousemove = function(ev) {
            var ev = ev || window.event;
            var x = ev.clientX-oC.offsetLeft;
            var y = ev.clientY-oC.offsetTop;

            if($(".friend-title").is(":visible") && lineColor != null  && boardFlag == true){
                var obj = {
                    to : $("#friendName").text(),
                    msg : x + '_' + y,
                    type : 0,
                    lineColor:lineColor,
                    lineWidth:lineWidth
                }

                oGC.strokeStyle = lineColor;
                oGC.lineWidth = lineWidth;
                oGC.lineTo(x,y);
                oGC.stroke();
                //将发送的json数据转成字符串
                var str = JSON.stringify(obj);
                ws.send(str);
            }
        };
        document.onmouseup = function() {
            boardFlag = true;
            var obj = {
                to : $("#friendName").text(),
                msg : "",
                type : 100,

            }
            oGC.beginPath();
            oGC.strokeStyle = null;
            var str = JSON.stringify(obj);
            //当画图点移开时向后台发送数据，告知画图点已经改变，重新划线，使线段不连续
            ws.send(str);
            document.onmousemove = null;
            document.onmouseup = null;
        };
    };
}
//白板划线颜色选择
var availableColors = [];
colorContainers = new Array(3 * 3 * 3);
for (var i = 0; i < colorContainers.length; i++) {
    var colorContainer = colorContainers[i] =
        document.createElement("div");
    var color = availableColors[i] =
        [
            Math.floor((i % 3) * 255 / 2),
            Math.floor((Math.floor(i / 3) % 3) * 255 / 2),
            Math.floor((Math.floor(i / (3 * 3)) % 3) * 255 / 2),
            1.0
        ];
    colorContainer.setAttribute("style","background-color: "+rgb(color));
    colorContainer.setAttribute("class","color-box");

    colorContainer.addEventListener("mousedown", (function(ix) {
        return function() {
            //console.log(ix);
            setColor(ix);
            lineColor = $(this).css("background-color");
        };
    })(i), false);
    var colorList = $("#colorList");
    colorList.append(colorContainer);
}

function rgb(color) {
    return "rgba(" + color[0] + "," + color[1] + ","
        + color[2] + "," + color[3] + ")";
}
function setColor(colorIndex) {
    if (typeof currentColorIndex !== "undefined")
        colorContainers[currentColorIndex]
            .style.borderColor = "#000";
    currentColorIndex = colorIndex;
    colorContainers[currentColorIndex]
        .style.borderColor = "#d08";
}

// 白板划线浓度选择
var availableThicknesses = [1,2, 3, 5, 8,10, 16, 22, 28 ,40, 50];

var thicknessContainersBox = document.createElement("div");
$("#boardList").append(thicknessContainersBox);

thicknessContainers = new Array(availableThicknesses.length);
for (var i = 0; i < thicknessContainers.length; i++) {
    var thicknessContainer = thicknessContainers[i] =
        document.createElement("div");
    thicknessContainer.setAttribute("class","width-box");
    thicknessContainer.appendChild(document.createTextNode(
        String(availableThicknesses[i])));
    thicknessContainer.addEventListener("mousedown", (function(ix) {
        return function() {
            lineWidth = $(this).text();
            setThickness(ix);
        };
    })(i), false);

    thicknessContainersBox.appendChild(thicknessContainer);
}

function setThickness(thicknessIndex) {
    if (typeof currentThicknessIndex !== "undefined")
        thicknessContainers[currentThicknessIndex]
            .style.borderColor = "#000";
    currentThicknessIndex = thicknessIndex;
    thicknessContainers[currentThicknessIndex]
        .style.borderColor = "#d08";
}