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
import es.com.disastercode.prueba.business.vo.AutorVO;


/**
 * Clase AutorDAOImpl - Implementación del interface AutorDAO 
 */
public class AutorDAOImpl extends HibernateDaoSupport implements AutorDAO{

	private static Log log = LogFactory.getFactory().getInstance(AutorDAOImpl.class);
	public AutorVO getAutor(Long id){
		return (AutorVO) getHibernateTemplate().get(AutorVO.class,id);
	}

	public Long newAutor(AutorVO autor){
		Long id = new Long(getHibernateTemplate().save(autor).toString());
		return id;
	}

	public void editAutor(AutorVO autor){
		getHibernateTemplate().update(autor);
	}

	public void deleteAutor(Long id){
		getHibernateTemplate().delete(this.getAutor(id));
	}

	public List<AutorVO> findAutor(AutorVO autor){
		Criteria criteria = getSession().createCriteria(AutorVO.class);
		if( autor.getIdAutor()!=null){
			criteria.add(Restrictions.eq("idAutor", autor.getIdAutor()));
		}
		if(!StringUtils.isBlank( autor.getNombre())) {
			criteria.add(Restrictions.like("nombre", autor.getNombre(),MatchMode.ANYWHERE));
		}
		if(!StringUtils.isBlank( autor.getApellidos())) {
			criteria.add(Restrictions.like("apellidos", autor.getApellidos(),MatchMode.ANYWHERE));
		}
		if( autor.getFechaNacimiento()!=null){
			criteria.add(Restrictions.eq("fechaNacimiento", autor.getFechaNacimiento()));
		}
		if(!StringUtils.isBlank( autor.getTelefono())) {
			criteria.add(Restrictions.like("telefono", autor.getTelefono(),MatchMode.ANYWHERE));
		}
		if( autor.getMujer()!=null && autor.getMujer() ){
			criteria.add(Restrictions.eq("mujer", autor.getMujer()));
		}
		if( autor.getHombre()!=null && autor.getHombre() ){
			criteria.add(Restrictions.eq("hombre", autor.getHombre()));
		}
		List<AutorVO> rVal =  (List<AutorVO>) criteria.list();
		return rVal;
	}

}
