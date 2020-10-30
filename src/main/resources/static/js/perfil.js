$(document).ready(function ()
{
	$.ajax(
			{
				type: "GET",
				url: "api/usuarios/funcionario",
				cache: false,
				beforeSend: function (request) {
					request.setRequestHeader("Authorization", `Bearer ${getToken()}`);
			    },
				error: function error(data)
				{	
					waitingDialog.hide();
				    if(data.status == 400){
						lancarToastr("error",`${data.responseJSON.titulo}`);
					}else{
						lancarToastr("error",`${data.responseJSON.error_description}`);
					}

				},
				success: function (data)
				{
					console.log(data)
					
					$("#titulo-nome").text(data.nome);
					$("#titulo-cargo").text(data.cargo);
					
					
					$("#nome").val(data.nome);
					$("#sobrenome").val(data.sobrenome);
					$("#telefone").val(data.telefone);
					$("#dataNascimento").val(data.dataNascimento);
					
					if(data.endereco){
						$("#logradouro").val(data.endereco.logradouro);
						$("#bairro").val(data.endereco.bairro);
						$("#numero").val(data.endereco.numero);
						$("#cep").val(data.endereco.cep);
						$("#cidade").val(data.endereco.cep);
						$("#uf").val(data.endereco.uf);
					}
					if(data.usuario){
						
						$("#login").val(data.usuario.login);
						loginEdicao = data.usuario.login;
						$("#email").val(data.usuario.email);
						emailEdicao = data.usuario.email;
					}
				}
			});
	
});


function enviarForm(acao, id)
{
       
	var sendInfo = {
			id : id,
			nome : $("#nome").val(),
			telefone : $("#telefone").val(),
			dataNascimento: $("#dataNascimento").val(),
			endereco : null,
			usuario:null,
			idBarbearia: getIdBarbearia(getToken()),
			usuario : {id:$("#email").attr('idusuario'), email : $("#email").val()!=""?$("#email").val():null}
	}
	
	
	let verbo;
	if(acao == "editar"){
		verbo = 'PUT';
	}else{
		verbo= 'POST';
	}

	waitingDialog.show('Salvando cliente ...');
	$.ajax(
	{
		type : verbo,
		url: `api/clientes`,
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(sendInfo),
		beforeSend: function (request) {
			request.setRequestHeader("Authorization", `Bearer ${getToken()}`);
	    },
		error: function error(data)
		{
			waitingDialog.hide();
			if(data.status == 400){
				lancarToastr("error",`${data.responseJSON.titulo}`);
			}else{
				lancarToastr("error",`${data.responseJSON.error_description}`);
			}

		},
		//dataType: 'json',
		success: function success(data)
		{
			
			
			lancarToastr("success",`Cliente ${acao == "cadastrar" ? "salvo" : "editado"} com sucesso.`,true)


		}
	});
}




function validarForm()
{

	jQuery.validator.setDefaults({
	    errorPlacement: function (error, element) {
	        error.addClass('invalid-feedback');
	        element.closest('.form-group').append(error);
	    },
	    highlight: function (element, errorClass, validClass) {
	        $(element).addClass('is-invalid');
	        $(element).removeClass('is-valid');
	    },
	    unhighlight: function (element, errorClass, validClass) {
	        $(element).removeClass('is-invalid');
	        $(element).addClass('is-valid');
	    }
	});
	
	jQuery.validator.addMethod("verificarEmail", function(value, element,parametros) {
		
		return  !verificarEmail();
		
	},'Email já utilizado.');
	
	
	 $("#form-cliente").validate(
	{
		// Rules for form validation
		rules:
		{
			nome: {
				required: true
			},
			telefone:
			{
				required: true
			},
			email:
			{
				required: true,
				verificarEmail: true
			},
			dataNascimento:
			{
				required: true
			}
		},
		messages : {
			nome: {
				required: "Campo obrigatório"
			},
			telefone:
			{
				required: "Campo obrigatório"
			},
			dataNascimento:
			{
				required: "Campo obrigatório"
			},
			email:
			{
				required: "Campo obrigatório"
			}
		},
		submitHandler: function submitHandler(form)
		{
			enviarForm($(".btn-salvar").attr("acao"), $(".btn-salvar").attr("data-id"))

		}
	});
}






function verificarEmail(){
	
	
	var existe;
	let email = $('#email').val();
	
	
		
	if(email == emailEdicao || email == "") return false;
		$.ajax(
				
				{
					type: 'GET',
					url: `api/usuarios/verificarEmail/${email}`,
					contentType: "application/json; charset=utf-8",
					async:false,
					error: function error(data)
					{
						
						lancarToastr("error",data);

					},
					//dataType: 'json',
					success: function success(data)
					{
						
						
						existe = data;
					}
				});
		
		return existe;
	
}
