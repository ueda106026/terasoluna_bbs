package jp.co.brightstar.domain.model;

import java.io.Serializable;
import java.sql.Timestamp;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostsForm implements Serializable {
	private Integer id;
	private Integer bbsId;
	
	@NotNull
	@NotEmpty(message = "名前を入力してください。")
	@Size(max = 32, message = "名前は{max}文字以内で入力してください。")
	private String name;
	
	@NotNull
	@NotEmpty(message = "メッセージが未入力です。")
	@Size(max = 1000, message = "メッセージは{max}文字以内で入力してください。")	
	private String message;
	private Timestamp createDate;
	private Timestamp updateDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBbsId() {
		return bbsId;
	}
	public void setBbsId(Integer bbsId) {
		this.bbsId = bbsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}
	
}