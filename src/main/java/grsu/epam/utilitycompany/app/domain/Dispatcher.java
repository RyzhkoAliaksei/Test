package grsu.epam.utilitycompany.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "dispatcher")
@PrimaryKeyJoinColumn(name = "USER_ID")
public class Dispatcher extends UserPrincipal {

	@DateTimeFormat(pattern = "yy/MM/dd")
	@Column(name = "JOINING_DATE")
	private Date joiningDate;

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

}
