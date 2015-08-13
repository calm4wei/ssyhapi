package cn.suishou.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cn.suishou.bean.SelfItem;
import cn.suishou.ramdata.SelfItemCacher;

public class SkuUtil {
	private static Logger logger = Logger.getLogger(SkuUtil.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static SelfItem updateSelfItemSku(SelfItem selfItem, String sku, int num){
		ArrayList list = new ArrayList();
		HashMap map = new HashMap();
		int stock = 0;
		try {
			JSONObject skus = selfItem.getDetails();
			
			if("others".equals(selfItem.getTemplate())){
				stock = selfItem.getStock()-num;
				selfItem.setStock(stock);
			}else if("standard".equals(selfItem.getTemplate())){
				JSONArray array = skus.getJSONArray("standard");			
				for(int i=0 ; i<array.size(); i++){ 
					JSONArray array2 = (JSONArray)array.get(i);			
					List list2 = new ArrayList();
					String tmpStandard = array2.getString(0);
					int tmpstock = array2.getInt(1);
					if(tmpStandard.equals(sku)){
						tmpstock -= num;
					}
					list2.add(tmpStandard);						
					list2.add(String.valueOf(tmpstock));
					stock += tmpstock; 
					list.add(list2);				
				}
				map.put("standard", list);				
				selfItem.setDetails(JSONObject.fromObject(map));
				selfItem.setStock(stock);
			}else if("color_size".equals(selfItem.getTemplate())){
				String color = sku.split(",")[0];
				String size = sku.split(",")[1];
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
				JSONArray array = skus.getJSONArray("color_size");
				for(int i=0 ; i<array.size(); i++){ 
					JSONObject jsonObject = (JSONObject)array.get(i);
					Map<String,Object> tmpMap = gson.fromJson(jsonObject.toString(), Map.class);
					Map.Entry<String, Object> entry = tmpMap.entrySet().iterator().next();
					String tmpColor = entry.getKey();
					Object content = entry.getValue();
					
					JSONArray array2 = JSONArray.fromObject(content);
					HashMap map2 = new HashMap();
					List list2 = new ArrayList();
					for(int j=0 ; j<array2.size(); j++){ 
						JSONArray array3 = (JSONArray)array2.get(j);			
						List list3 = new ArrayList();
						String tmpSize = array3.getString(0);
						int tmpstock = array3.getInt(1);
						if(tmpColor.equals(color) && tmpSize.equals(size)){
							tmpstock -= num;
						}
						list3.add(tmpSize);
						list3.add(String.valueOf(tmpstock));
						stock += tmpstock; 
						list2.add(list3);				
					}
					map2.put(tmpColor, list2);
					list.add(map2);
			
				}
			
				map.put("color_size", list);				
				selfItem.setDetails(JSONObject.fromObject(map));
				selfItem.setStock(stock);
			}		
			
			return selfItem;
		} catch (Exception e) {		
			logger.error("error stack",e);
			return selfItem;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static boolean checkSelfItemSkuStock(SelfItem selfItem, String sku, int num){	
		int skuStock = 0;
		try {
			JSONObject skus = selfItem.getDetails();
			
			if("others".equals(selfItem.getTemplate())){
				skuStock = selfItem.getStock()-num;				
			}else if("standard".equals(selfItem.getTemplate())){
				JSONArray array = skus.getJSONArray("standard");			
				for(int i=0 ; i<array.size(); i++){ 
					JSONArray array2 = (JSONArray)array.get(i);	
					String tmpStandard = array2.getString(0);
					int tmpstock = array2.getInt(1);
					if(tmpStandard.equals(sku)){
						skuStock = tmpstock - num;
					}
							
				}			
			}else if("color_size".equals(selfItem.getTemplate())){
				String color = sku.split(",")[0];
				String size = sku.split(",")[1];
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();	
				JSONArray array = skus.getJSONArray("color_size");
				for(int i=0 ; i<array.size(); i++){ 
					JSONObject jsonObject = (JSONObject)array.get(i);
					Map<String,Object> tmpMap = gson.fromJson(jsonObject.toString(), Map.class);
					Map.Entry<String, Object> entry = tmpMap.entrySet().iterator().next();
					String tmpColor = entry.getKey();
					Object content = entry.getValue();
					
					JSONArray array2 = JSONArray.fromObject(content);
				
					for(int j=0 ; j<array2.size(); j++){ 
						JSONArray array3 = (JSONArray)array2.get(j);			
						
						String tmpSize = array3.getString(0);
						int tmpstock = array3.getInt(1);
						if(tmpColor.equals(color) && tmpSize.equals(size)){
							skuStock = tmpstock - num;
						}								
					}	
				}			
			}		
			
			if(skuStock>=0){
				return true;
			}else{
				return false;
			}	
		} catch (Exception e) {		
			logger.error("error stack",e);
			return false;
		}
	}
	
    public static void main(String[] args) 	{    
   // 	SelfItem selfItem = SelfItemCacher.getInstance().getItem("141203023747329");
    	SelfItem selfItem = SelfItemCacher.getInstance().getItem("141211104117357");
    	selfItem = updateSelfItemSku(selfItem, "红色,l", 1);
    	System.out.println("---"+selfItem.getDetails().toString());
    	

    }

}
