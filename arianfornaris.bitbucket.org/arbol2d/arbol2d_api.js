
var funcNames = [
'ball',
'canvas',
'canvas.width',
'canvas.height',
'degree',
'evalTree',
'height',
'makeTreeFunc',
'maybe',
'maybeEval',
'maybeEvalFunc',
'oneInRange',
'oneOf',
'pop',
'push',
'rotate',
'stick',
'style',
'up',
];

var canvas;
var ctx;
var state;
var stack;
var maxDepth = 15;

const DEGREE_FACTOR = Math.PI/180;

function createCanvasContext(canvasName) {
	canvas = document.getElementById(canvasName);
	ctx = canvas.getContext("2d");
	ctx.style = 'black';
}

function paintTree(code) {
	init();

	var cx = canvas.width / 2;
	var cy = canvas.height;
	
	ctx.fillStyle = 'white';
	ctx.fillRect(0, 0, canvas.width, canvas.height);
	
	ctx.translate(cx, cy);
	ctx.rotate(degree(180));
	
	eval(code);
	
	ctx.rotate(-degree(180));
	ctx.translate(-cx, -cy);
}

function degree(d) {
	return d * DEGREE_FACTOR;
}

function maybe(p) {
	return Math.random() <= p;
}

function maybeEval(p, code) {
	if (maybe(p)) {
		eval(code);
	}
}

function maybeEvalFunc(p, f) {
	if (maybe(p)) {
		f();
	}
}

function push() {
	stack.push({x:state.x, y:state.y, angle:state.angle, style:state.style});
}

function pop() {
	state = stack.pop();
}

function oneOf(opts) {
	var i = Math.floor(Math.random() * opts.length);
	return opts[i];
}


function init() {
	state = {x:0, y:0, angle:0, style:'black'};
	stack = [];
	ctx.moveTo(0, 0)
}

function up(len) {
	var x = len * Math.sin(degree(state.angle));
	var y = len * Math.cos(degree(state.angle));
	
	x += state.x;
	y += state.y;
	
	ctx.moveTo(state.x, state.y);
	
	state.x = x;
	state.y = y;
}

function stick(len) {
	var x = len * Math.sin(degree(state.angle));
	var y = len * Math.cos(degree(state.angle));
	
	x += state.x;
	y += state.y;
	
	ctx.strokeStyle = state.style;
	
	ctx.beginPath();
	ctx.moveTo(state.x, state.y);
	ctx.lineTo(x, y);
	ctx.stroke();
	ctx.closePath();
	
	state.x = x;
	state.y = y;
}

function ball(r) {
	ctx.fillStyle = state.style;
	ctx.arc(state.x, state.y, r, 0, Math.PI * 2);
	ctx.fill();
}


function style(s) {
	state.style = s;
}

function rotate(a) {
	state.angle += a;
}
function oneInRange(min, max) {
	return min + Math.random() * (max - min);
}

function makeTreeFunc(name) {
	var prog;
	
	for(var i = 0; i < progs.length; i++) {
		var p = progs[i];
		if (p.getAttribute('name') == name) {
			prog = p.textContent;
			break;
		}
	}
	if (prog) {
		return function () {
			push();
			eval(prog);
			pop();
		}
	} else {
		alert('El árbol "' + name + '" no ha sido definido. Compruebe el nombre.');
		return function() { };
	}
}

function evalTree(name) {
	var f = makeTreeFunc(name);
	f();
	return f;
}