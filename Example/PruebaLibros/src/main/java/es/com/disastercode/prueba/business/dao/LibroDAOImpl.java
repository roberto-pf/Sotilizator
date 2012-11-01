package es.com.disastercode.prueba.business.dao;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import es.com.disastercode.prueba.business.vo.LibroVO;


/**
 * Clase LibroDAOImpl - Implementación del interface LibroDAO 
 */
public class LibroDAOImpl extends HibernateDaoSupport implements LibroDAO{

	private static Log log = LogFactory.getFactory().getInstance(LibroDAOImpl.class);
	public LibroVO getLibro(Long id){
		return (LibroVO) getHibernateTemplate().get(LibroVO.class,id);
	}

	public Long newLibro(LibroVO libro){
		Long id = new Long(getHibernateTemplate().save(libro).toString());
		return id;
	}

	public void editLibro(LibroVO libro){
		getHibernateTemplate().update(libro);
	}

	public void deleteLibro(Long id){
		getHibernateTemplate().delete(this.getLibro(id));
	}

	public List<LibroVO> findLibro(LibroVO libro){
		Criteria criteria = getSession().createCriteria(LibroVO.class);
		if( libro.getIdLibro()!=null){
			criteria.add(Restrictions.eq("idLibro", libro.getIdLibro()));
		}
		if( libro.getAutor()!=null && libro.getAutor().getIdAutor()!=null){
			criteria.add(Restrictions.eq("autor.idAutor", libro.getAutor().getIdAutor()));
		}
		if( libro.getGenero()!=null && libro.getGenero().getIdGenero()!=null){
			criteria.add(Restrictions.eq("genero.idGenero", libro.getGenero().getIdGenero()));
		}
		if(!StringUtils.isBlank( libro.getIsbn())) {
			criteria.add(Restrictions.like("isbn", libro.getIsbn(),MatchMode.ANYWHERE));
		}
		if(!StringUtils.isBlank( libro.getTitulo())) {
			criteria.add(Restrictions.like("titulo", libro.getTitulo(),MatchMode.ANYWHERE));
		}
		if(!StringUtils.isBlank( libro.getDescripcion())) {
			criteria.add(Restrictions.like("descripcion", libro.getDescripcion(),MatchMode.ANYWHERE));
		}
		if(!StringUtils.isBlank( libro.getResumen())) {
			criteria.add(Restrictions.like("resumen", libro.getResumen(),MatchMode.ANYWHERE));
		}
		if( libro.getImporteRecaudado()!=null){
			criteria.add(Restrictions.eq("importeRecaudado", libro.getImporteRecaudado()));
		}
		List<LibroVO> rVal =  (List<LibroVO>) criteria.list();
		return rVal;
	}

}
