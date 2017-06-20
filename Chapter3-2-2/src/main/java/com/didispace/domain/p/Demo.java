package com.didispace.domain.p;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
public class Demo {
	@Id
	@GeneratedValue
	private long id;
	private boolean tBo;
	@NotEmpty(message = "{demo.name}")
	private String name;
	@NotEmpty(message = "{demo.pass}")
	@Length(min = 6, message = "{demo.pass1}")
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean istBo() {
		return tBo;
	}

	public void settBo(boolean tBo) {
		this.tBo = tBo;
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

	@Override
	public String toString() {
		return "Demo [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
}
