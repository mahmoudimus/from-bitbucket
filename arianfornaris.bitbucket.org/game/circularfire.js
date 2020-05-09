function CircularFireGame(canvas) {
	var canvas = canvas;		
	var ctx = canvas.getContext('2d');
	var world = [ new BouncingObj(100, 100) ];
	
	
	function BouncingObj(x, y) {
		this.x = x;
		this.y = x;
		this.dx = 10;
		this.dy = 15;
		this.live = 4;
	
		this.paint = function() {
			ctx.fillStyle = 'green';
			ctx.fillRect(this.x, this.y, 5, 5);
		}
		
		this.nextFrame = function() {
			if (this.x < 0 || this.x > canvas.width) {
				this.dx = -this.dx;
				this.live--;
			}
			
			if (this.y < 0 || this.y > canvas.height) {
				this.dy = -this.dy;
				this.live--;
			}
			
			this.x += this.dx;
			this.y += this.dy;
		}
	}
	
	paintGame = function() {
		var tmp = [];
		
		for(var i = 0; i < world.length; i++) {
			var obj = world[i];
			if (obj.live > 0) {
				tmp.push(obj);
			}
		}
		
		world = tmp;
		ctx.fillStyle = 'lightGray';
		ctx.fillRect(0, 0, canvas.width, canvas.height);
		
		for(var i = 0; i < world.length; i++) {
			var obj = world[i];
			obj.paint();
		}
	}
	
	this.nextFrame = function() {
		for(var i = 0; i < world.length; i++) {
			var obj = world[i];
			obj.nextFrame();
		}
		
		paintGame();
	}
}