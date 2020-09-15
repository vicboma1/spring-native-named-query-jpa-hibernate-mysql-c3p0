package es.vicboma.alfatec.sina.poc.nativesql.dao;

import java.util.List;

import es.vicboma.alfatec.sina.poc.nativesql.entities.source.User;
import es.vicboma.alfatec.sina.poc.nativesql.entities.target.UserDetail;
import org.springframework.transaction.annotation.Transactional;

/***
 * @author vicboma1
 */
public interface UserDao {


  /**
   *
   * @return
   */
  List<User> findAllCreateNativeQuery();

  /**
   *
   * @param id
   * @return
   */
  User findOneByIdCreateNativeQuery(Long id);

  /**
   *
   * @return
   */
  List<UserDetail> findAllDetailCreateNativeQuery();

  /**
   *
   * @param id
   * @return
   */
  User findOneByIdNamedNativeQuery(Long id);

  /**
   *
   * @return
   */
  List<UserDetail> findAllDetailNamedNativeQuery();

  /**
   *
   * @return
   */
  List<User> findAllNamedNativeQuery();

  /**
   *
   * @return
   */
  Long findCountUserCreateNativeQuery() ;

  /**
   *
   * @return
   */
  Long findCountUserNamedNativeQuery() ;


    /**
     *
     * @param l
     * @param name
     * @param email
     */
  @Transactional
  void insert(Long l, String name, String email);
}
