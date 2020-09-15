package es.vicboma.alfatec.sina.poc.nativesql.dao;


import es.vicboma.alfatec.sina.poc.nativesql.entities.source.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.vicboma.alfatec.sina.poc.nativesql.ApplicationContext;

import java.util.List;

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

	@Test
	public void _1_findOneByIdCreateNativeQuery() {
    final long actual = userDao.findOneByIdCreateNativeQuery(19000L).getId().longValue();
    assertEquals(19000, actual);
	}

	@Test
	public void _1_findOneByIdNamedNativeQuery() {
    final long actual = userDao.findOneByIdNamedNativeQuery(19000L).getId().longValue();
    assertEquals(19000, actual);
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
    final Long expected = 19999L;
    final Long count = userDao.findCountUserCreateNativeQuery();
    assertEquals(expected, count);
  }

  @Test
  public void _4_findCountUserNamedNativeQuery() {
    final Long expected = 19999L;
    final Long count = userDao.findCountUserNamedNativeQuery();
    assertEquals(expected, count);
  }

	@Test
  public void testInsert(){
    for (long i = 3; i < 20000; i++ ) {
   //   userDao.insert(i, "user"+i, "user"+i+"@gmail.com");
    }
  }
}
