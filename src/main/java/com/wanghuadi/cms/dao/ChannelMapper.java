/**
 * 
 */
package com.wanghuadi.cms.dao;

import java.util.List;

import com.wanghuadi.cms.domain.Channel;

/**
 * 说明:
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * @version 1.0
 *
 * 2019年3月27日 下午2:56:07
 */
public interface ChannelMapper {

	public void insert(Channel channel);

	public void deleteById(int id);

	public Channel selectById(int id);
	
	//查询所有的栏目信息
	public List<Channel> selectAll();
	
}
