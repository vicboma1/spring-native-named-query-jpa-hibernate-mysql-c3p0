package es.vicboma.alfatec.sina.poc.nativesql.entities.source;

import javax.persistence.*;

import es.vicboma.alfatec.sina.poc.nativesql.entities.target.UserDetail;

import java.util.Objects;

/***
 * @author vicboma1
 */
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "resultSetMappingUserDetail",
                classes = @ConstructorResult(
                        targetClass = UserDetail.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "concat")
                        })
        )
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "selectFindById",
                      query = "SELECT id, name, email FROM user WHERE id = ? LIMIT 1",
                      resultClass = User.class),
    @NamedNativeQuery(name="selectFindAll",
                      query = "SELECT id, name, email FROM user ORDER BY email DESC",
                      resultClass = User.class),
    @NamedNativeQuery(name = "countUsers",
                      query = "SELECT count(user.id) FROM user",
                      resultClass = void.class),
    @NamedNativeQuery(name = "updateUser",
                      query = "UPDATE user SET name = ? WHERE id = ?",
                      resultClass = void.class),
    @NamedNativeQuery(name="insertUser",
                      query = "INSERT INTO user (name, email) VALUES ( ?1, ?2 )",
                      resultClass = void.class),
    @NamedNativeQuery(name="deleteUser",
                      query = "DELETE FROM user WHERE id = ?",
                      resultClass = void.class),
    @NamedNativeQuery(name="deleteAllUsers",
                      query = "DELETE FROM user",
                      resultClass = void.class),
    @NamedNativeQuery(name="truncateUsers",
                query = "TRUNCATE TABLE user",
                resultClass = void.class),
    @NamedNativeQuery(name = "selectFindAllDetail",
                      query = "SELECT id, CONCAT(name, '-', email) AS concat FROM user",
                      resultSetMapping = "resultSetMappingUserDetail")
})
@Entity(name="UserEntity")
@Table(name = "user")
public class User {

    public static final String RESULT_SET_MAPPING_DETAIL = "resultSetMappingUserDetail";

    public static final String NAMED_NATIVE_SELECT_FIND_BY_ID = "selectFindById";
    public static final String NAMED_NATIVE_SELECT_FIND_ALL_DETAIL = "selectFindAllDetail";
    public static final String NAMED_NATIVE_SELECT_FIND_ALL = "selectFindAll";
    public static final String NAMED_NATIVE_COUNT = "countUsers";
    public static final String NAMED_NATIVE_UPDATE = "updateUser";
    public static final String NAMED_NATIVE_INSERT = "insertUser";
    public static final String NAMED_NATIVE_DELETE = "deleteUser";
    public static final String NAMED_NATIVE_DELETE_ALL = "deleteAllUsers";
    public static final String NAMED_NATIVE_TRUNCATE ="truncateUsers";

    public static final String NATIVE_COUNT = "SELECT count(user.id) FROM user";
    public static final String NATIVE_SELECT_FIND_ALL = "SELECT id, name, email FROM user ORDER BY email DESC";
    public static final String NATIVE_SELECT_FIND_BY_ID = "SELECT id, name, email FROM user WHERE id = :_id_ LIMIT 1";
    public static final String NATIVE_SELECT_FIND_ALL_DETAIL = "SELECT id, CONCAT(name, '-', email) AS concat FROM user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;    

	public User() {
		super();
	}

	public User(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(id, user.id) &&
            Objects.equals(name, user.name) &&
            Objects.equals(email, user.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email);
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
