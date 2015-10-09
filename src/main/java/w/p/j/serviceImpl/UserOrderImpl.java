/*
 * Copyright (c) 2015 - 10 - 1  9 : 11 :$second.second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.serviceImpl;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;
import w.p.j.dao.TbUserorderMapper;
import w.p.j.daomain.TbUserorder;
import w.p.j.service.BaseService;
import w.p.j.service.UserOrder;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by WPJ587 on 2015/10/1.
 *
 */
@Service("userOrderImpl")
public class UserOrderImpl extends BaseService<TbUserorder> implements UserOrder {
    private Logger logger= LoggerFactory.getLogger(this.getClass().getName());
    @Resource
    private TbUserorderMapper tbUserorderMapper;

    @Override
    public List<TbUserorder> selectByCountry(TbUserorder tbUserorder, int page, int rows) {
        Example example = new Example(TbUserorder.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(tbUserorder.getUserAddress())) {
            criteria.andLike("countryname", "%" + tbUserorder.getUserAddress() + "%");
        }
        if (StringUtil.isNotEmpty(tbUserorder.getUserName())) {
            criteria.andLike("countrycode", "%" + tbUserorder.getUserName() + "%");
        }
        if (tbUserorder.getId() != null) {
            criteria.andEqualTo("id", tbUserorder.getId());
        }
        //分页查询
        PageHelper.startPage(page, rows);
        return tbUserorderMapper.selectAll();
    }

    /**
     * 使用具体实现类具有缓存的效果
     * 直接使用父类的没有缓存
     * @param key
     * @return
     */
    @Override
    @Cacheable(value = "myCache",key = "#key")
    public TbUserorder selectByKey(Object key) {
        logger.debug("---haveCache---",key);
        return tbUserorderMapper.selectByPrimaryKey(key);
    }

    @Override
    @Cacheable(value = "myCache")
    public List<TbUserorder> selectByExample(Object example) {

        return tbUserorderMapper.selectByExample(example);
    }
}
