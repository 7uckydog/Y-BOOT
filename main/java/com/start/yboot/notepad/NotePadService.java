package com.start.yboot.notepad;

import java.util.List;

public interface NotePadService {

    /**
     * @param list
     * @return insert 후 결과값 (성공 여부)
     * @author ybseo
     * @version : 1.0
     *
     * 원본 데이터 INSERT
     * *** INSERT ALL 사용 시, 다량의 데이터로 인해 오류 발생
     *   : for문 이용하여 하나씩 INSERT
     *
     */
    void insOriginData(List<NotePadDTO> list) throws Exception;

    /**
     * @param list
     * @return list (null 데이터 포함 데이터 추출)
     * @author ybseo
     * @version : 1.0
     *
     * NULL 포함 데이터 추출 후 INSERT
     *
     */
    void checkNulllData(List<NotePadDTO> list);

    /**
     * @param list
     * @return insert 후 결과값 (성공 여부)
     * @author ybseo
     * @version : 1.0
     *
     * 전체 데이터를 이용하여 측정소별 발령 데이터 추출 후 INSERT
     *
     */
    void insertFitData(List<NotePadDTO> list);


}




