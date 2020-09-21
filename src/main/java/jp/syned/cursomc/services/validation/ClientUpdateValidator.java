package jp.syned.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import jp.syned.cursomc.domain.Cliente;
import jp.syned.cursomc.domain.enums.TipoCliente;
import jp.syned.cursomc.dto.ClienteDTO;
import jp.syned.cursomc.dto.ClienteNewDTO;
import jp.syned.cursomc.repositories.ClienteRepository;
import jp.syned.cursomc.resources.exception.FiledMessage;
import jp.syned.cursomc.services.validation.utils.BR;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String,String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer urlId = Integer.parseInt(map.get("id"));
		
		List<FiledMessage> list = new ArrayList<>();		
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux!=null && !aux.getId().equals(urlId)) {
			list.add(new FiledMessage("email","Email ja existente"));
		}
		
		for (FiledMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFildName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}