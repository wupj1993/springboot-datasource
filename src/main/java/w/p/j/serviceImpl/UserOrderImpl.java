/*
 * Copyright (c) 2015 - 10 - 1  9 : 11 :$second.second
 * @author wupeiji It will be
 * @Email wpjlovehome@gmail.com
 */

package w.p.j.serviceImpl;

import com.github.pagehelper.PageHelper;
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
 */
@Service("userOrderImpl")
public class UserOrderImpl extends BaseService<TbUserorder>  implements UserOrder {
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
}
