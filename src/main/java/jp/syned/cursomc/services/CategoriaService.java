package jp.syned.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.syned.cursomc.domain.Categoria;
import jp.syned.cursomc.repositories.CategoriaRepository;
import jp.syned.cursomc.services.exceptions.DataIntegrityViolationException;
import jp.syned.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:"+id+", Tipo: "+Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch (org.springframework.dao.DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Nao e possivel excluir uma categoria que possui produtos");
		}
	}
}
