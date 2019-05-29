package vu.lt.mybatis.dao;

import org.mybatis.cdi.Mapper;
import vu.lt.mybatis.model.Patron;

import java.util.List;

@Mapper
public interface PatronMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Patron record);

    Patron selectByPrimaryKey(Integer id);

    List<Patron> selectAll();

    int updateByPrimaryKey(Patron record);
}
