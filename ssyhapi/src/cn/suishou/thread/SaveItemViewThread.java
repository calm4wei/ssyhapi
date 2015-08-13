package cn.suishou.thread;

import cn.suishou.bean.ItemView;
import cn.suishou.dao.ItemViewDAO;

public class SaveItemViewThread extends Thread{
	ItemView itemView;
	
	public SaveItemViewThread(ItemView itemView){
		this.itemView = itemView;
	}
	
	public void run(){	
			ItemViewDAO.getInstance().add(itemView);
	}


	
}
