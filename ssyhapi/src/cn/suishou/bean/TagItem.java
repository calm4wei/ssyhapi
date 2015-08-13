package cn.suishou.bean;

public class TagItem {
	public String itemId;
	public String title;
	public String img;
	public String recom;
	public double priceLow;
	public double priceHeigh=-1; //无原价
	public int market;
	public int isBaoYou;
	public int isPxj;
	public int num;
	public int fromChannel;	
	public int isFlashSell;
	public String flashSellStartTime;
	public String flashSellEndTime;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getRecom() {
		return recom;
	}
	public void setRecom(String recom) {
		this.recom = recom;
	}
	public double getPriceLow() {
		return priceLow;
	}
	public void setPriceLow(double priceLow) {
		this.priceLow = priceLow;
	}
	public double getPriceHeigh() {
		return priceHeigh;
	}
	public void setPriceHeigh(double priceHeigh) {
		this.priceHeigh = priceHeigh;
	}
	public int getMarket() {
		return market;
	}
	public void setMarket(int market) {
		this.market = market;
	}
	public int getIsBaoYou() {
		return isBaoYou;
	}
	public void setIsBaoYou(int isBaoYou) {
		this.isBaoYou = isBaoYou;
	}
	public int getIsPxj() {
		return isPxj;
	}
	public void setIsPxj(int isPxj) {
		this.isPxj = isPxj;
	}
	public int getFromChannel() {
		return fromChannel;
	}
	public void setFromChannel(int fromChannel) {
		this.fromChannel = fromChannel;
	}
	public int getIsFlashSell() {
		return isFlashSell;
	}
	public void setIsFlashSell(int isFlashSell) {
		this.isFlashSell = isFlashSell;
	}
	public String getFlashSellStartTime() {
		return flashSellStartTime;
	}
	public void setFlashSellStartTime(String flashSellStartTime) {
		this.flashSellStartTime = flashSellStartTime;
	}
	public String getFlashSellEndTime() {
		return flashSellEndTime;
	}
	public void setFlashSellEndTime(String flashSellEndTime) {
		this.flashSellEndTime = flashSellEndTime;
	}
	
	
	
	
}
