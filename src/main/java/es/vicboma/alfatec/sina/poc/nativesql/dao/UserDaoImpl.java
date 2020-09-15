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
    return em.createNativeQuery(User.NATIVE_SELECT_FIND_ALL, User.class)
             .getResultList();
}

  @Override
	public User findOneByIdCreateNativeQuery(Long _id) {
    final Query nativeQuery = em.createNativeQuery(User.NATIVE_SELECT_FIND_BY_ID, User.class);
    nativeQuery.setParameter("_id_", _id);
    return (User) nativeQuery.getSingleResult();
  }

  @Override
	public List<UserDetail> findAllDetailCreateNativeQuery() {
    return em.createNativeQuery(User.NATIVE_SELECT_FIND_ALL_DETAIL, User.RESULT_SET_MAPPING_DETAIL)
             .getResultList();
	}	

  @Override
  public User findOneByIdNamedNativeQuery(Long id) {
    final Query nativeQuery = em.createNamedQuery(User.NAMED_NATIVE_SELECT_FIND_BY_ID);
		nativeQuery.setParameter(1, id);
		return (User) nativeQuery.getSingleResult();
	}

  @Override
  public List<UserDetail> findAllDetailNamedNativeQuery() {
    return em.createNamedQuery(User.NAMED_NATIVE_SELECT_FIND_ALL_DETAIL)
             .getResultList();
  }

  @Override
  public List<User> findAllNamedNativeQuery() {
    return em.createNamedQuery(User.NAMED_NATIVE_SELECT_FIND_ALL)
            .getResultList();
  }

  @Override
  public Long findCountUserCreateNativeQuery() {
    return ((BigInteger) em.createNativeQuery(User.NATIVE_COUNT)
                            .getSingleResult())
                            .longValue();
  }

  @Override
  public Long findCountUserNamedNativeQuery() {
    return ((BigInteger)em.createNamedQuery(User.NAMED_NATIVE_COUNT)
                           .getSingleResult())
                           .longValue();
  }

  @Transactional
  @Override
  public void insert( String name, String email) {
    final Query query = em.createNamedQuery(User.NAMED_NATIVE_INSERT);
    query.setParameter(1, name);
    query.setParameter(2, email);
    query.executeUpdate();
  }

  @Transactional
  @Override
  public void update(Long id, String name) {
    final Query query = em.createNamedQuery(User.NAMED_NATIVE_UPDATE);
    query.setParameter(1, name);
    query.setParameter(2, id);
    query.executeUpdate();
  }

  @Transactional
  @Override
  public void delete(Long id) {
    final Query query = em.createNamedQuery(User.NAMED_NATIVE_DELETE);
    query.setParameter(1, id);
    query.executeUpdate();
  }

  @Transactional
  @Override
  public int deleteAll() {
    return em.createNamedQuery(User.NAMED_NATIVE_DELETE_ALL).executeUpdate();
  }

  @Transactional
  @Override
  public int truncate()  {
    return em.createNamedQuery(User.NAMED_NATIVE_TRUNCATE).executeUpdate();
	}

}
