/*
 * Copyright (c) 2015 - 10 - 2  4 : 3 :$second.second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * Created by liuzh on 2014/12/11.
 */
@Service
public abstract class BaseService<T> implements IService<T> {
    @Autowired
    protected Mapper<T> mapper;

    @Override
    public T selectByKey(Object key) {

        return mapper.selectByPrimaryKey(key);
    }

    public int save(T entity) {
        return mapper.insert(entity);
    }

    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    //TODO 其他...
}
