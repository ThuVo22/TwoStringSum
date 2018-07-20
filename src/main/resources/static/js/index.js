//Change user interface 
var changeUI = function(data) {
	// set value for input[id="result"] is data.result
	$("#result").val(data.result);
	$("#progress-bar").css("width", data.percentage + "%");
	$("#progress-text").html(data.percentage + "%");
	//Get Array of elements in numberA input
	var arrNumberA = $("#numberA").val().split("");
	//Get Array of elements in numberB input
	var arrNumberB = $("#numberB").val().split("");
	//Index of current position of element which calculated in numberA 
	var indexA = arrNumberA.length - data.index - 1;
	//Index of current position of element which calculated in numberB 
	var indexB = arrNumberB.length - data.index - 1;
	//Set default for current element which calculated in numberA
	var charA = "0";
	//Set default for current element which calculated in numberA
	var charB = "0";

	//Show number is calculating
	if (indexA >= 0) {
		charA = arrNumberA[indexA];
		arrNumberA[indexA] = '<h4 style="display: inline-block;line-height: 0; color:red;">'
				+ arrNumberA[indexA] + '</h4>';
	}
	if (indexB >= 0) {
		charB = arrNumberB[indexB];
		arrNumberB[indexB] = '<h4 style="display: inline-block;line-height: 0; color:red;">'
				+ arrNumberB[indexB] + '</h4>';
	}

	$("#text-numberA").html(arrNumberA.join(""));
	$("#text-numberB").html(arrNumberB.join(""));
	// Format string for result text
	var result = '<b>Step ' + (data.index + 1)
			+ ': </b><br/>a + b + carry<sub>n-1</sub>' + '= ' + charA + ' + '
			+ charB + ' + ' + data.lastCarry + '<br> <b>Result: </b><br/>'
			+ 'sum = ' + data.sum + ', carry: ' + data.carry;
	//Calculation done
	if (data.percentage == "100.00") {
		$("#btn-next").attr("disabled", true);
		$("#btn-submit").removeAttr("disabled");
		if (data.carry > 0) {
			$("#result").val(data.carry + $("#result").val());
		}
	}
	// Append result value into input has class "result-text"
	$(".result-text").append(result + '<hr>');
	// Set scroll bar position to button
	$(".result-text").scrollTop($(".result-text")[0].scrollHeight);
}
$(document).ready
{

	// submit button click
	$("#btn-submit").on("click", function() {
		var numberA = $("#numberA").val();
		var numberB = $("#numberB").val()
		//Validate input numberA
		if (numberA == "") {
			Alert.render('Enter numberA');
		}
		//Validate input numberB
		else if (numberB == "") {
			Alert.render('Enter numberB');
		} else {
			// Ajax; submit form to controller mapping mapping with
			// GetMapping("/")
			// in controller
			$(".result-text").html("");			
			// Post data of form[id = "main_form"] to server using ajax
			$.ajax({
				// Set type is post
				type : "post",
				// Set url is value of attribute "action" of
				// form[id="main_form"]
				url : $("#main_form").attr("action"),
				// Set data(or parameter) is value serialize of main_form
				data : $("#main_form").serialize(),
				// Handle data is returned from the server after a successful post
				// With data is object{carry, index, lastCarry, result, sum}
				// mapping with ResultObject in server
				success : function(data) {
					// Set value for input[id="result"] is data.result
					$("#result").val(data.result);
					// Disable Submit button
					$("#btn-next").removeAttr("disabled");
					// Enable Next button
					$("#btn-submit").attr("disabled", true);					
					// Call ChangeUI function with parameter is data variable
					changeUI(data);
				},
				error: function(){
					Alert.render('Error');
				}
			});
		}
	});
	// Next button click
	$("#btn-next").on("click", function() {
		// Call ajax
		$.ajax({
			// Set ajax type is post
			type : "post",
			// Set url is /next maaping with @RequestMapping("/") annotation in
			// server
			url : "/next",
			success : function(data) {
				// Call ChangeUI function with parameter is data variable
				changeUI(data);
			},
			error: function(){
				Alert.render('Enter numberA');
			}
		});
	});
}

// Validate input
function validate(evt) {
	// get event
	var theEvent = evt || window.event;
	// get key code form event
	var key = theEvent.keyCode || theEvent.which;
	// get char value form key code "key"
	key = String.fromCharCode(key);
	// Regex pattern mapping with number form 1 to 9
	var regex = /[0-9]|\./;
	// if char input not mapping with number form 1 to 9
	// alert message error and delete the just char input
	if (!regex.test(key)) {
		theEvent.returnValue = false;
		if (theEvent.preventDefault)
			theEvent.preventDefault();
		Alert.render('Please enter digit from \'0\' to \'9\'');
	}
	// The case: input char is 1-9 enable submit button
	else {
		$("#btn-submit").removeAttr("disabled");
		$("#btn-next").attr("disabled", true);
	}
}