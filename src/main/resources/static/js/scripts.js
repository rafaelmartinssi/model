$(document).ready(function() {
	//start datatable
    $('#datatable').DataTable({
        "language": {
            "sEmptyTable": "Nenhum registro encontrado",
            "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando 0 até 0 de 0 registros",
            "sInfoFiltered": "(Filtrados de _MAX_ registros)",
            "sInfoPostFix": "",
            "sInfoThousands": ".",
            "sLengthMenu": "_MENU_ resultados por página",
            "sLoadingRecords": "Carregando...",
            "sProcessing": "Processando...",
            "sZeroRecords": "Nenhum registro encontrado",
            "sSearch": "Pesquisar",
            "oPaginate": {
                "sNext": "Próximo",
                "sPrevious": "Anterior",
                "sFirst": "Primeiro",
                "sLast": "Último"
            },
            "oAria": {
                "sSortAscending": ": Ordenar colunas de forma ascendente",
                "sSortDescending": ": Ordenar colunas de forma descendente"
            }
        }
    });
    
    //start modal delete
    $('#exampleModal').on('show.bs.modal', function (event) {
    	  var button = $(event.relatedTarget) 
    	  
    	  var codigo = button.data('codigo');
    	  var descricao = button.data('descricao');
    	  var acao = button.data('acao');
    	  
    	  var modal = $(this);
    	 
    	  var form = modal.find('form');
    	  
    	  form.attr('action', acao + '/' + codigo);
    	  modal.find('.modal-body span').html('Tem certeza que deseja excluir o registro <strong>' + descricao + '</strong>?');
    	  
    	  $()
    	  
    })
} );