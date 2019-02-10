package org.py.sundry.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class UploadModel implements Serializable {
	private static final long serialVersionUID = 2105614863075732061L;
	@NotEmpty(message="必须填写标题")
	private String title;
	private String smalltitle;
	@NotEmpty(message="必须填写来源")
	private String source;
	@NotEmpty(message="必须选择栏目")
	private String column;
	@NotEmpty(message="必须填写内容")
	private String content;
	public UploadModel() {
	}
	public UploadModel(String title, String smalltitle, String source, String column, String content) {
		super();
		this.title = title;
		this.smalltitle = smalltitle;
		this.source = source;
		this.column = column;
		this.content = content;
	}
	@Override
	public String toString() {
		return "UploadModel [title=" + title + ", smalltitle=" + smalltitle + ", source=" + source + ", column="
				+ column + ", content=" + content + "]";
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSmalltitle() {
		return smalltitle;
	}
	public void setSmalltitle(String smalltitle) {
		this.smalltitle = smalltitle;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}