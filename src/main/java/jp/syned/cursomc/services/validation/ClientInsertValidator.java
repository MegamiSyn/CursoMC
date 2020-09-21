package jp.syned.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import jp.syned.cursomc.domain.Cliente;
import jp.syned.cursomc.domain.enums.TipoCliente;
import jp.syned.cursomc.dto.ClienteNewDTO;
import jp.syned.cursomc.repositories.ClienteRepository;
import jp.syned.cursomc.resources.exception.FiledMessage;
import jp.syned.cursomc.services.validation.utils.BR;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FiledMessage> list = new ArrayList<>();

		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FiledMessage("cpfOuCnpj","CPF Invalido"));
		}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FiledMessage("cpfOuCnpj","CNPJ Invalido"));
		}
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux!=null) {
			list.add(new FiledMessage("email","Email ja existente"));
		}
		
		for (FiledMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFildName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}