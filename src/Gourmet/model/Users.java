/******************************************************************************
*  CS5200 Milestone 4 Hello World
*  Bee
******************************************************************************/

package Gourmet.model;

import java.util.Date;

public class Users {
	protected int userId;
	protected String name;
	protected String password;
	protected Date createdSince;
	protected int reviewCount;
	protected Boolean gender;
	
	public Users(int userId, String name, String password, Date createdSince, int reviewCount, Boolean gender) {
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.createdSince = createdSince;
        this.reviewCount = reviewCount;
        this.gender = gender;
	}
	
	public Users(int userId) {
		this.userId = userId;
	}
	public Users(String name, String password, Date createdSince, int reviewCount, Boolean gender){
		this.name = name;
		this.password = password;
		this.createdSince = createdSince;
        this.reviewCount = reviewCount;
        this.gender = gender;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Date getCreatedSince() {
		return createdSince;
	}

	public void setCreatedSince(Date createdSince) {
		this.createdSince = createdSince;
	}
	
	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}
	public boolean getGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
    }
}
