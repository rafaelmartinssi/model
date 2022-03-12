$(document).ready(function() {
	$("#formsenha").validate({
		errorClass : "my-error-class",
		validClass : "my-valid-class",
		rules : {
			currentPassword : {
				required : true,
			},
			newPassword : {
				required : true,
				maxlength : 8,
				minlength : 4
			},
			confirmPassword : {
				required : true,
				equalTo : "#newPassword"
			}
		},
        messages: {
        	currentPassword: {
                required : "Campo obrigatório"
            },
            newPassword : {
                required: "Campo obrigatório",
                minlength: "Senha deve conter no minímo 4 caracteres",
                maxlength : "Senha deve conter no máximo 8 caracteres"
            },
            confirmPassword : {
                required: "Campo obrigatório",
                equalTo : "Senha não confere"
            }
        }
	});
	$("#formpermissao").validate({
		errorClass : "my-error-class",
		validClass : "my-valid-class",
		rules : {
			authorization : {
				required : true,
			}
		},
        messages: {
        	authorization: {
                required : "Campo obrigatório"
            }
        }
	});
	$("#formfrota").validate({
		errorClass : "my-error-class",
		validClass : "my-valid-class",
		rules : {
			marca : {
				required : true,
				minlength : 2
			},
			modelo : {
				required : true,
				minlength : 2
			},
			placa : {
				required : true,
				minlength : 8
			},
			seguranca : {
				required : true,
				minlength : 8
			},
			tipo : {
				required : true,
			}
		},
        messages: {
        	marca: {
                required: "Campo obrigatório",
                minlength: "Campo deve conter no minímo 2 caracteres"
            },
            modelo : {
                required: "Campo obrigatório",
                minlength: "Campo deve conter no minímo 2 caracteres"
            },
            placa : {
                required: "Campo obrigatório",
                minlength: "Campo deve conter no minímo 8 caracteres"
            },
            seguranca : {
                required: "Campo obrigatório",
                minlength: "Campo deve conter no minímo 8 caracteres"
            },
            tipo : {
                required: "Campo obrigatório",
            }
        }
	});
	$("#formservidor").validate({
		errorClass : "my-error-class",
		validClass : "my-valid-class",
		rules : {
			nome : {
				required : true,
				minlength : 2
			},
			masp : {
				required : true,
				minlength : 7
			},
			carreira : {
				required : true,
			}
		},
        messages: {
        	nome: {
                required: "Campo obrigatório",
                minlength: "Campo deve conter no minímo 2 caracteres"
            },
            masp : {
                required: "Campo obrigatório",
                minlength: "Campo deve conter no minímo 7 caracteres"
            },
            carreira : {
                required: "Campo obrigatório"
            }
        }
	});
	$("#formuser").validate({
		errorClass : "my-error-class",
		validClass : "my-valid-class",
		rules : {
			nome : {
				required : true,
				minlength : 2
			},
			sobrenome : {
				required : true,
				minlength : 2
			},
			masp : {
				required : true,
				minlength : 7
			},
			codigo : {
				required : true,
			},
			carreira : {
				required : true,
			}
		},
        messages: {
        	nome: {
                required: "Campo obrigatório",
                minlength: "Campo deve conter no minímo 2 caracteres"
            },
        	sobrenome: {
                required: "Campo obrigatório",
                minlength: "Campo deve conter no minímo 2 caracteres"
            },
            masp : {
                required: "Campo obrigatório",
                minlength: "Campo deve conter no minímo 7 caracteres"
            },
            codigo : {
                required: "Campo obrigatório"
            },
            carreira : {
                required: "Campo obrigatório"
            }
        }
	});
	$("#formunidade").validate({
		errorClass : "my-error-class",
		validClass : "my-valid-class",
		rules : {
			nome : {
				required : true,
				minlength : 2
			},
			titular : {
				required : true,
				minlength : 2
			},
			endereco : {
				required : true,
				minlength : 5
			},
			contatos : {
				required : true,
				minlength : 10
			},
			codigo : {
				required : true,
			}
		},
        messages: {
        	nome: {
                required: "Campo obrigatório",
                minlength: "Campo deve conter no minímo 2 caracteres"
            },
            titular: {
                required: "Campo obrigatório",
                minlength: "Campo deve conter no minímo 2 caracteres"
            },
            endereco : {
                required: "Campo obrigatório",
                minlength: "Campo deve conter no minímo 5 caracteres"
            },
            contatos : {
                required: "Campo obrigatório"
                minlength: "Campo deve conter no minímo 10 caracteres"
            },
            codigo : {
                required: "Campo obrigatório"
            }
        }
	});
});
