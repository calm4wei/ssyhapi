package cn.suishou.bean;

import cn.suishou.utils.FenHongUtil;

public class UserAccount {
	private String uid ;
	private int acAddJF;
	private int fhAddJF;	
	private int shiftJF;
	private int payJF;
	private int yueJF;
	private String updatetime;	
	private int flAddJF;
	private double flXJ;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public int getAcAddJF() {
		return acAddJF;
	}
	public void setAcAddJF(int acAddJF) {
		this.acAddJF = acAddJF;
	}
	public int getFlAddJF() {
		return flAddJF;
	}
	public void setFlAddJF(int flAddJF) {
		this.flAddJF = flAddJF;
	}
	
	public int getFhAddJF() {
		return fhAddJF;
	}
	public void setFhAddJF(int fhAddJF) {
		this.fhAddJF = fhAddJF;
	}
	public double getFlXJ() {
		return flXJ;
	}
	public void setFlXJ(double flXJ) {
		this.flXJ = flXJ;
	}
	public int getPayJF() {
		return payJF;
	}
	public void setPayJF(int payJF) {
		this.payJF = payJF;
	}
	public int getShiftJF() {
		return shiftJF;
	}
	public void setShiftJF(int shiftJF) {
		this.shiftJF = shiftJF;
	}
	public int getYueJF() {
		return yueJF;
	}
	public void setYueJF(int yueJF) {
		this.yueJF = yueJF;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public int getLevel(){
		int level = 0;
		int jf = (int)flXJ*100 + flAddJF + fhAddJF;
		level = FenHongUtil.getLevelByJF(jf);
		return level;
	}
	

}
