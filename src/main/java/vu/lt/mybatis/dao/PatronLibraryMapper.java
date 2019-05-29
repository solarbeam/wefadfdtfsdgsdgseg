package vu.lt.mybatis.dao;

import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;
import vu.lt.mybatis.model.PatronLibrary;

import java.util.List;

@Mapper
public interface PatronLibraryMapper {

    int deleteByPrimaryKey(@Param("patronId") Integer patronId, @Param("libraryId") Integer libraryId);

    int insert(PatronLibrary record);

    List<PatronLibrary> selectAll();
}
