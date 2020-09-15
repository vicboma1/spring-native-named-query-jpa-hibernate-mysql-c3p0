package es.vicboma.alfatec.sina.poc.nativesql.entities.source;

import javax.persistence.*;

import es.vicboma.alfatec.sina.poc.nativesql.entities.target.UserDetail;

import java.math.BigInteger;

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
        ),
        @SqlResultSetMapping(
                name = "resultSetMappingCountFindUsers",
                classes = @ConstructorResult(
                        targetClass = Long.class,
                        columns = {}
                )
        )
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "selectFindById",
                      query = "SELECT id, name, email FROM user WHERE id = ? LIMIT 1",
                      resultClass = User.class),
    @NamedNativeQuery(name="selectFindAll",
                      query = "SELECT id, name, email FROM user ORDER BY email DESC",
                      resultClass = User.class),
    @NamedNativeQuery(name = "countFindUsers",
                      query = "SELECT count(user.id) FROM user",
                      resultClass = void.class /* resultSetMapping = "resultSetMappingCountFindUsers"*/),
    @NamedNativeQuery(name = "selectFindAllDetail",
                      query = "SELECT id, CONCAT(name, '-', email) AS concat FROM user",
                      resultSetMapping = "resultSetMappingUserDetail")
})
@Entity
@Table(name = "user")
public class User {

    public static String RESULT_SET_MAPPING_DETAIL = "resultSetMappingUserDetail";

    public static String SELECT_FIND_BY_ID = "selectFindById";
    public static String SELECT_FIND_ALL_DETAIL = "selectFindAllDetail";
    public static String SELECT_FIND_ALL = "selectFindAll";
    public static String COUNT = "countFindUsers";

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
  

    
}
