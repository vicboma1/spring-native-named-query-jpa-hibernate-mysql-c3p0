package es.vicboma.alfatec.sina.poc.nativesql.dao;


import es.vicboma.alfatec.sina.poc.nativesql.entities.source.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.vicboma.alfatec.sina.poc.nativesql.ApplicationContext;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

/***
 * @author vicboma1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationContext.class })
//@Transactional
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	public void UserDaoTest() {

  }

  @Before
  public void before() {
    final int size = 100;

    if(userDao.findCountUserNamedNativeQuery() <= 0)
      insert(size);
  }

  @After
  public void after() {
    userDao.truncate();
  }

	@Test
	public void _1_findOneByIdCreateNativeQuery() {
    final List<User> allCreateNativeQuery = userDao.findAllCreateNativeQuery();
    final long expected = allCreateNativeQuery.get(0).getId();
    final long actual = userDao.findOneByIdCreateNativeQuery(expected).getId().longValue();
    assertEquals(expected, actual);
	}

	@Test
	public void _1_findOneByIdNamedNativeQuery() {
    final List<User> allNamedNativeQuery = userDao.findAllNamedNativeQuery();
    final long expected = allNamedNativeQuery.get(0).getId();
    final long actual = userDao.findOneByIdNamedNativeQuery(expected).getId().longValue();
    assertEquals(expected, actual);
	}

  @Test
  public void _2_findAllDetailCreateNativeQuery() {
    final boolean empty = userDao.findAllDetailCreateNativeQuery().isEmpty();
    assertFalse(empty);
  }

  @Test
	public void _2_findAllDetailNamedNativeQuery() {
    final boolean empty = userDao.findAllDetailNamedNativeQuery().isEmpty();
    assertFalse(empty);
	}

  @Test
  public void _3_findAllCreateNativeQuery() {
    final List<User> allCreateNativeQuery = userDao.findAllCreateNativeQuery();
    assertFalse(allCreateNativeQuery.isEmpty());
  }

  @Test
  public void _3_findAllNamedNativeQuery() {
    final List<User> allCreateNativeQuery = userDao.findAllNamedNativeQuery();
    assertFalse(allCreateNativeQuery.isEmpty());
  }

  @Test
  public void _4_findCountUserCreateNativeQuery() {
    final Long count = userDao.findCountUserCreateNativeQuery();
    assertNotNull(count);
  }

  @Test
  public void _4_findCountUserNamedNativeQuery() {
    final Long count = userDao.findCountUserNamedNativeQuery();
    assertNotNull(count);
  }

  /**
   *
   */
  @Test
  public void _5_testInsertTransactional(){
    final Long expected = userDao.findCountUserCreateNativeQuery();
    final int size = 2000;
    insert(size);

    assertTrue(Objects.equals(expected,(userDao.findCountUserCreateNativeQuery() - size)));
  }

  private void insert(int size){
    for (long i = 0 ; i < size  ; i++ ) {
      final long time = System.currentTimeMillis();
      userDao.insert("user"+ time, "user"+time+"@gmail.com");
    }
  }

  @Test
  public void _6_testUpdateNamedTransactional(){
      final List<User> allNamedNativeQuery = userDao.findAllNamedNativeQuery();
      for (int i = 0; i < allNamedNativeQuery.size(); i++ ) {
      final long time = System.currentTimeMillis();
      userDao.update(allNamedNativeQuery.get(i).getId(), "userUpdate"+time+"@gmail.com");
    }
  }

  @Test
  public void _7_testDeleteNamedTransactional(){
    final List<User> allNamedNativeQuery = userDao.findAllNamedNativeQuery();
    for (int i = 0; i < allNamedNativeQuery.size(); i++ ) {
      try {
        userDao.delete(allNamedNativeQuery.get(i).getId());
      }catch (Exception e)
      {
        fail(e.getMessage());
      }
    }
  }

  @Test
  public void _8_testDeleteAllNamedTransactional(){
    try {
      userDao.deleteAll();
    }catch (Exception e)
    {
      fail(e.getMessage());
    }
  }

  @Test
  public void _9_testTruncate(){
      userDao.truncate();
  }
}
