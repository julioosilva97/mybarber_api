

	$(document).ready(function() {
		
		
		$(window).on("beforeunload", function(e) {
		    alert("call");
		    console.log("this will be triggered");
		});
		
		checkLogin();
		
	});
	
	 function checkLogin(){
		
		if(!sessionStorage.getItem("accessToken")){
			window.location.href = "/login";
		}
	}

	function lancarToastr(acao,mensagem,reload=false){
		
		toastr.options = {
					"closeButton": true,
					"debug": false,
					"newestOnTop": true,
					"progressBar": true,
					"positionClass": "toast-top-right",
					"preventDuplicates": true,
					"onclick": null,
					"showDuration": "300",
					"hideDuration": "300",
					"timeOut": "2000",
					"extendedTimeOut": "3000",
					"showEasing": "swing",
					"hideEasing": "linear",
					"showMethod": "fadeIn",
					"hideMethod": "fadeOut",
					"onHidden": function ()
	  				{
	  					if(reload)window.location.reload();
	  				}
				}
				toastr[acao](mensagem);
	}


	function getDateFromHours(time,dia,mes,ano) {
	mes = mes-1
	time = time.split(':');
	let now = new Date();
	if(dia && mes && ano){
		return new Date(ano,mes,dia, ...time);

	}
	}
	
	function parseJwt(token) {
	    var base64Url = token.split('.')[1];
	    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
	    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
	        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
	    }).join(''));

	    return JSON.parse(jsonPayload);
	};
	
	

	function getIdBarbearia(token){
		
		let json = parseJwt(token);
		
		return json.dadosUsuario.idBarbearia;
	}
	
	function formataStringData(data) {
		  var dia  = data.split("/")[0];
		  var mes  = data.split("/")[1];
		  var ano  = data.split("/")[2];

		  return ano + '-' + ("0"+mes).slice(-2) + '-' + ("0"+dia).slice(-2);
		  // Utilizo o .slice(-2) para garantir o formato com 2 digitos.
		}


	function getToken(){
		
		return sessionStorage.getItem("accessToken");
	}
	
	function fecharModalLoading(){
		
		setTimeout(function () {
			$('.modal-loading').modal('hide');
	   }, 1000);
	}
	
	

