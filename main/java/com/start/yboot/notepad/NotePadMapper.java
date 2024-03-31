package com.start.yboot.notepad;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NotePadMapper {
    int insOriginDate(NotePadDTO vo);

    int insertFitDate(String location);
}
