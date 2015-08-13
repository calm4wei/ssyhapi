package cn.suishou.bean;

public class Activity {	
	private String id ;
	private String name = "";
	private String description = "";
	private int frequency;
	private int jfNum;
	private String key;
	private String startTime;
	private String endTime;
	private String createTime;
	private String img = "";
	
	public Activity(){}
	
	public Activity(String id, String name,  int jfNum) {
		super();
		this.setId(id);
		this.name = name;
		this.jfNum = jfNum;
	}
	
	public Activity(String id, String name, String description, int frequency, int jfNum, String startTime, String endTime) {
		super();
		this.setId(id);
		this.name = name;
		this.description = description;
		this.frequency = frequency;
		this.jfNum = jfNum;	
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public int getJfNum() {
		return jfNum;
	}
	public void setJfNum(int jfNum) {
		this.jfNum = jfNum;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
