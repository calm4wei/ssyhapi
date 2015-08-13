package cn.suishou.ramdata;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.bean.PushMessage;
import cn.suishou.common.X;
import cn.suishou.redis.executor.JedisKeyManager;

public class PushMessageCacher {
	private static PushMessageCacher instance;
	private static String cacheKey = X.CachePrefix.PUSH_MESSAGE;
		
	public static PushMessageCacher getInstance(){
		return instance == null ? new PushMessageCacher() :instance;
	}

	public void insertMsg(String uid,PushMessage msg){		
		GsonBuilder gb = new GsonBuilder();
		gb.disableHtmlEscaping();
		Gson gson = gb.create();		
		
		List<PushMessage> list = getMsg(uid);
		list.add(msg);
		new JedisKeyManager(cacheKey+uid).set(gson.toJson(list));
	}
	
	public void insertMsg(HashSet<String> uidSet,PushMessage msg){
		for(String uid : uidSet){
			insertMsg(uid,msg);
		}	
	}
	
	@SuppressWarnings("unchecked")
	public List<PushMessage> getMsg(String uid){
		GsonBuilder gb = new GsonBuilder();
		gb.disableHtmlEscaping();
		Gson gson = gb.create();		

		String json = new JedisKeyManager(cacheKey+uid).get();
		ArrayList<PushMessage> list = null;
		if(json != null){
			list = gson.fromJson(json, ArrayList.class);
		}else{
			list = new ArrayList<PushMessage>();
		}		
		return list;	
	}
	
	public void resetMsgList(String uid, List<PushMessage> msgList){
		GsonBuilder gb = new GsonBuilder();
		gb.disableHtmlEscaping();
		Gson gson = gb.create();
		new JedisKeyManager(cacheKey+uid).set(gson.toJson(msgList));
	}
	
	
}
