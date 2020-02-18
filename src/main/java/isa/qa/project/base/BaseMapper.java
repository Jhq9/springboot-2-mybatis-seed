package isa.qa.project.base;

import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * 基本增删改查方法整合
 *
 * @author May
 * @version 1.0
 * @date 2018/4/22 9:49
 */
public interface BaseMapper<T> extends Mapper<T>, InsertListMapper<T> {
	//FIXME 特别注意，该接口不能被扫描到，否则会出错
}
