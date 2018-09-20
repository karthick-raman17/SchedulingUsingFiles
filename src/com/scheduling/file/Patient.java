package com.scheduling.file;

class Patient implements java.io.Serializable {
	private String id;
	private int age;
	private String name;
	private String email;

	public Patient(String id, String name, String email, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
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

	public String getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", age=" + age + ", name=" + name + ", email=" + email + "]";
	}

}
