package dengtao.Model.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class Gasfield{
	@JSONField(name="气田编号", ordinal = 1)
	private Integer id;
	@JSONField(name="气田名", ordinal = 2)
	private String name;
	@JSONField(name="气区", ordinal = 3)
	private String area;
	@JSONField(name="气藏类型", ordinal = 4)
	private String type;
	@JSONField(name="埋深类型", ordinal = 5)
	private String depthType;
	@JSONField(name="储量", ordinal = 6)
	private Float reserves;
	
	
	public Gasfield() {
		super();
	}


	public Gasfield(Integer id, String name, String area, String type, String depthType, Float reserves) {
		super();
		this.id = id;
		this.name = name;
		this.area = area;
		this.type = type;
		this.depthType = depthType;
		this.reserves = reserves;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getDepthType() {
		return depthType;
	}


	public void setDepthType(String depthType) {
		this.depthType = depthType;
	}


	public Float getReserves() {
		return reserves;
	}


	public void setReserves(Float reserves) {
		this.reserves = reserves;
	}


	@Override
	public String toString() {
		return "Gasfield [id=" + id + ", name=" + name + ", area=" + area + ", type=" + type + ", depthType="
				+ depthType + ", reserves=" + reserves + "]";
	}
	
	
}
