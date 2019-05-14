package br.reaggeou.ted.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {

	private Integer id_event;
	private String title;
	private String description;
	private String href;
	private String local;
	private LocalDate date;
	private LocalTime time;
	private String folder;
	private Category category;

	private Event_info_vendor vendor;

	public Integer getId_event() {
		return id_event;
	}

	public void setId_event(Integer id_event) {
		this.id_event = id_event;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Event_info_vendor getVendor() {
		return vendor;
	}

	public void setVendor(Event_info_vendor vendor) {
		this.vendor = vendor;
	}

}
