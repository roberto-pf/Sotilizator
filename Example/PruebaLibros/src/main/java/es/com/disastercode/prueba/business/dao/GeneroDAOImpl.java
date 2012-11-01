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
import es.com.disastercode.prueba.business.vo.GeneroVO;


/**
 * Clase GeneroDAOImpl - Implementación del interface GeneroDAO 
 */
public class GeneroDAOImpl extends HibernateDaoSupport implements GeneroDAO{

	private static Log log = LogFactory.getFactory().getInstance(GeneroDAOImpl.class);
	public GeneroVO getGenero(Long id){
		return (GeneroVO) getHibernateTemplate().get(GeneroVO.class,id);
	}

	public Long newGenero(GeneroVO genero){
		Long id = new Long(getHibernateTemplate().save(genero).toString());
		return id;
	}

	public void editGenero(GeneroVO genero){
		getHibernateTemplate().update(genero);
	}

	public void deleteGenero(Long id){
		getHibernateTemplate().delete(this.getGenero(id));
	}

	public List<GeneroVO> findGenero(GeneroVO genero){
		Criteria criteria = getSession().createCriteria(GeneroVO.class);
		if( genero.getIdGenero()!=null){
			criteria.add(Restrictions.eq("idGenero", genero.getIdGenero()));
		}
		if(!StringUtils.isBlank( genero.getNombre())) {
			criteria.add(Restrictions.like("nombre", genero.getNombre(),MatchMode.ANYWHERE));
		}
		List<GeneroVO> rVal =  (List<GeneroVO>) criteria.list();
		return rVal;
	}

}
