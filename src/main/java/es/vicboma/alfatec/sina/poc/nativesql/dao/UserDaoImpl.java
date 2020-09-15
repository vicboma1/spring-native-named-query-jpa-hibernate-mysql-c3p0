package es.vicboma.alfatec.sina.poc.nativesql.dao;

import es.vicboma.alfatec.sina.poc.nativesql.entities.source.User;
import es.vicboma.alfatec.sina.poc.nativesql.entities.target.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/***
 * @author vicboma1
 */
@Repository
public class UserDaoImpl implements UserDao {

  final private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	@PersistenceContext
	private EntityManager em;

	public UserDaoImpl() {
    logger.info("Constructor");
  }

  @PostConstruct
  public void postConstruct() {
    logger.info("postConstruct");
  }

  @PreDestroy
  public void preDestroy() {
    logger.info("preDestroy");
  }

  @Override
	public List<User> findAllCreateNativeQuery() {
    return em.createNativeQuery("SELECT id, name, email FROM user ORDER BY email DESC", User.class)
             .getResultList();
}

  @Override
	public User findOneByIdCreateNativeQuery(Long _id) {
    final Query nativeQuery = em.createNativeQuery("SELECT id, name, email FROM user WHERE id = :_id_ LIMIT 1", User.class);
    nativeQuery.setParameter("_id_", _id);
    return (User) nativeQuery.getSingleResult();
  }

  @Override
	public List<UserDetail> findAllDetailCreateNativeQuery() {
    return em.createNativeQuery("SELECT id, CONCAT(name, '-', email) as concat From user", User.RESULT_SET_MAPPING_DETAIL)
             .getResultList();
	}	

  @Override
  public User findOneByIdNamedNativeQuery(Long id) {
    final Query nativeQuery = em.createNamedQuery(User.SELECT_FIND_BY_ID);
		nativeQuery.setParameter(1, id);
		return (User) nativeQuery.getSingleResult();
	}

  @Override
  public List<UserDetail> findAllDetailNamedNativeQuery() {
    return em.createNamedQuery(User.SELECT_FIND_ALL_DETAIL)
             .getResultList();
  }

  @Override
  public List<User> findAllNamedNativeQuery() {
    return em.createNamedQuery(User.SELECT_FIND_ALL)
            .getResultList();
  }

  @Override
  public Long findCountUserCreateNativeQuery() {
    return ((BigInteger) em.createNativeQuery("SELECT count(user.id) FROM user")
                            .getSingleResult())
                            .longValue();
  }

  @Override
  public Long findCountUserNamedNativeQuery() {
    return ((BigInteger)em.createNamedQuery(User.COUNT)
                           .getSingleResult())
                           .longValue();
  }

  @Transactional
  @Override
  public void insert(Long _id, String _name, String _email) {
    final Query query = em.createNativeQuery("INSERT INTO user (id, name, email) VALUES(?,?,?)");
    query.setParameter(1, _id);
    query.setParameter(2, _name);
    query.setParameter(3, _email);
    query.executeUpdate();
  }
}
