package com.lixm.animationdemo.listener;

public interface PostReceiverDataListener {
	
	/**
	* @param @param flag  
	* @param @param action
	* @param @param dataObj    设定文件 
	* @return void    返回类型 
	 */
	public void postReceiverData(int flag, int action, Object dataObj);

}
