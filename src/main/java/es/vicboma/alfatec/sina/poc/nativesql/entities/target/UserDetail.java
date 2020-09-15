package es.vicboma.alfatec.sina.poc.nativesql.entities.target;

import java.math.BigInteger;

/***
 * @author vicboma1
 */
public class UserDetail {

	private BigInteger id;
	private String details;

	public UserDetail() {
		super();
	}

	public UserDetail(BigInteger id, String details) {
		super();
		this.id = id;
		this.details = details;
	}
	
	public UserDetail(Long id, String details) {
		super();
		this.id = BigInteger.valueOf(id);
		this.details = details;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

  @Override
  public int hashCode() {
    return super.hashCode()+getId().intValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    final UserDetail ud = (UserDetail) o;
    return id.equals(ud.id) && details.equals(ud.details);
  }

  @Override
  public String toString() {
    return "UserDetail{" + "id=" + id + ", details='" + details + '\'' + '}';
  }
}
