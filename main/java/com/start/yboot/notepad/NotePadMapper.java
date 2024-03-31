package com.start.yboot.notepad;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotePadMapper {
    int insOriginData(NotePadDTO vo);

    int insertNullData();

    int insertFitData(String location);
}
