package cn.suishou.tuiguang;

import java.sql.Timestamp;

public interface TuiGuang {
	public boolean callback(String idfa,Timestamp activatetime,String adalias,String activateip,String code,String openudid);
}
