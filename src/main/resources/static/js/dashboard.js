$(document).ready(function (){
	
	graficoAnual();
	preencherInformacoes()
	

});  

function preencherInformacoes(){
	

	
	$.ajax(
			{
				type: "GET",
				url: `api/barbearias/buscarPorId/${getIdBarbearia(getToken())}`,
				cache: false,
				beforeSend: function (request) {
					request.setRequestHeader("Authorization", `Bearer ${getToken()}`);
			    },
				error: function error(data)
				{
					
					console.log(data)

				},
				success: function (data)
				{
					$("#qtd-funcionario").text(data.qtdFuncionario);
					$("#qtd-cliente").text(data.qtdCliente);
					$("#qtd-servico").text(data.qtdServico);
				}
			});
}


function graficoAnual(){
	
	$.ajax({
		url: `api/agendamentos/somaValorMensal/${getIdBarbearia(getToken())}`,
		beforeSend: function (request) {
			request.setRequestHeader("Authorization", `Bearer ${getToken()}`);
	    },
		error: function error(data)
		{
			console.log(data)
			
		},
		success: function(result){
			
			console.log(result)
			
			var meses = {
					1: 0,
					2: 0,
					3: 0,
					4: 0,
					5: 0,
					6: 0,
					7: 0,
					8: 0,
					9: 0,
					10: 0,
					11: 0,
					12: 0
				} ; 
			
			function teste(int,valor){
				
				meses[int] = valor;
				return meses;
			};
			
			
			result.forEach(function(e){
				
				teste(e.mes,e.valor)
				
			});
			
			;
			
			var mesesGrafico = [];
			var valores = [];
			
			Object.entries(meses).forEach(function(e){
				mesesGrafico.push(converterMeses(e[0]));
				valores.push(e[1]);
			});
			
			new Chart(document.getElementById("lucro-anual").getContext("2d"), {
			    type: 'bar',
			    data: {
			      labels: mesesGrafico,
			      datasets: [
			        {
			          label: "Faturamento (reais)",
			          backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
			          data: valores
			        }
			      ]
			    },
			    options: {
			      legend: { display: false },
			      title: {
			        display: true,
			        text: 'Em reais R$'
			      }
			    }
			});
			
		}
});

}

function converterMeses(mesInt){
	
	meses = {
		1:'Jan',
		2:'Fev',
		3: 'Mar',
		4: 'Abr',
		5 : 'Mai',
		6: 'Jun',
		7: 'Jul',
		8: 'Ago',
		9: 'Set',
		10: 'Out',
		11: 'Nov',
		12: 'Dez'
	}
	
	return meses[mesInt];
};


