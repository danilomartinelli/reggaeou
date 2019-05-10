package br.reaggeou.ted.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {

	private Integer id_event;
	private String title;
	private String description;
	private String href;
	private String local;
	private LocalDate data;
	private LocalTime time;
	private String folder;
	private Integer id_category;

	private event_info_vendor vendor;

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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
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

	public Integer getId_category() {
		return id_category;
	}

	public void setId_category(Integer id_category) {
		this.id_category = id_category;
	}
	
	public event_info_vendor getVendor() {
		return vendor;
	}

	public void setVendor(event_info_vendor vendor) {
		this.vendor = vendor;
	}

}
