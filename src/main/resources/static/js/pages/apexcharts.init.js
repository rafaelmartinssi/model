var a = document.getElementById('a').value;
var b = document.getElementById('b').value;
var c = document.getElementById('c').value;
var d = document.getElementById('d').value;
var e = document.getElementById('e').value;
var options = {
		chart : {
			height : 320,
			type : "pie"
		},
		series : [ parseInt(a), parseInt(b), parseInt(c), parseInt(d), parseInt(e) ],
		labels : [ "Investigador", "Escrivão", "Delegado", "Analista", "Técnico" ],
		colors : [ "#5fd0f3", "#038edc", "#f06548", "#51d28c", "#f7b84b" ],
		legend : {
			show : !0,
			position : "bottom",
			horizontalAlign : "center",
			verticalAlign : "middle",
			floating : !1,
			fontSize : "14px",
			offsetX : 0,
			offsetY : -10
		},
		responsive : [ {
			breakpoint : 600,
			options : {
				chart : {
					height : 240
				},
				legend : {
					show : !1
				}
			}
		} ]
	};
	(chart = new ApexCharts(document.querySelector("#my-chart"), options))
			.render();