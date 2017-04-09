package grsu.epam.utilitycompany.app.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "demand")
public class Demand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEMAND_ID")
	private Long demandId;

	@ManyToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private Client client;

	@ManyToOne(targetEntity = Crew.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "CREW_ID")
	private Crew crew;

	@OneToMany(targetEntity = Task.class, mappedBy = "demand", fetch = FetchType.LAZY)
	private List<Task> tasks;

	@Column(name = "STATUS")
	@Enumerated(EnumType.ORDINAL)
	private Status status;
	
	
	@DateTimeFormat(pattern = "yy/MM/dd HH:mm")
	@Column(name = "PREFERENCE_TIME")
	private Date preferenceTime;
	
	
	@DateTimeFormat(pattern = "yy/MM/dd HH:mm")
	@Column(name = "BEGIN_TIME")
	private Date beginTime;

	
	@DateTimeFormat(pattern = "yy/MM/dd HH:mm")
	@Column(name = "END_TIME")
	private Date endTime;

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Crew getCrew() {
		return crew;
	}

	public void setCrew(Crew crew) {
		this.crew = crew;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getPreferenceTime() {
		return preferenceTime;
	}

	public void setPreferenceTime(Date preferenceTime) {
		this.preferenceTime = preferenceTime;
	}

	public Long getDemandId() {
		return demandId;
	}

	public void setDemandId(Long demandId) {
		this.demandId = demandId;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}
