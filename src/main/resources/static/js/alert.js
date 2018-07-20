function CustomAlert() {
			this.render = function(dialog) {
				var dialogoverlay = document.getElementById('dialogoverlay');

				var winH = window.innerHeight;

				var dialogbox = document.getElementById('dialogbox');
				dialogoverlay.style.display = "block";
				dialogoverlay.style.height = winH + "px";
				var winW = dialogoverlay.clientWidth;
				var bodyWid = winW * .8 > 600 ? 600 : winW * .8;
				dialogbox.style.left = (winW / 2) - (bodyWid * .5) + "px";
				dialogbox.style.top = "100px";
				dialogbox.style.display = "block";
				document.getElementById('dialogboxhead').innerHTML = "Error";
				document.getElementById('dialogboxbody').innerHTML = dialog;
				document.getElementById('dialogboxfoot').innerHTML = '<button class="btn btn-default" onclick="Alert.ok()">OK</button>';
			}
			this.ok = function() {
				document.getElementById('dialogbox').style.display = "none";
				document.getElementById('dialogoverlay').style.display = "none";
			}
		}
		var Alert = new CustomAlert();

		