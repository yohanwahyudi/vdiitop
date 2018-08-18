package org.vdi.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="summary_performance")
public class SummaryPerformance {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", updatable=false,nullable=false)
	private Long id;
	private int totalTicket;
	private int totalAchieved;
	private int totalMissed;
	private float achievement;
	private String period;
	private String category;
	private short month;

	@Column(name="created_dt")
	@CreationTimestamp
	private LocalDateTime createdDate;
	
	@Column(name="updated_dt")
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	
	@Version
	private int version;

	public int getTotalTicket() {
		return totalTicket;
	}

	public int getTotalAchieved() {
		return totalAchieved;
	}

	public int getTotalMissed() {
		return totalMissed;
	}

	public float getAchievement() {
		return achievement;
	}

	public String getPeriod() {
		return period;
	}

	public String getCategory() {
		return category;
	}

	public short getMonth() {
		return month;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public int getVersion() {
		return version;
	}

	public void setTotalTicket(int totalTicket) {
		this.totalTicket = totalTicket;
	}

	public void setTotalAchieved(int totalAchieved) {
		this.totalAchieved = totalAchieved;
	}

	public void setTotalMissed(int totalMissed) {
		this.totalMissed = totalMissed;
	}

	public void setAchievement(float achievement) {
		this.achievement = achievement;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setMonth(short month) {
		this.month = month;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	
}
