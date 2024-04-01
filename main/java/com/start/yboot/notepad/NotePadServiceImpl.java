package com.start.yboot.notepad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotePadServiceImpl implements NotePadService{
    @Autowired
    private NotePadMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(NotePadServiceImpl.class);

    @Override
    @Transactional
    public void insOriginData(List<NotePadDTO> list) throws Exception {
        if(list.size() < 1){
            throw new Exception("INSERT용 데이터가 존재하지 않습니다.");
        }

        int result = 0;
        if(list.size() != 0){
            for(int i=0; i < list.size(); i++){
                mapper.insOriginData(list.get(i));
                result ++;
            }
        }

        // INSERT 확인
        if(result < 1){
            throw new Exception("INSERT 데이터가 1건도 존재하지 않습니다.");
        }

        // INSERT한 데이터 갯수와 List 갯수 비교
        if(list.size() != result){
            throw new Exception("INSERT 데이터와 LIST 길이가 맞지 않습니다.");
        }
        logger.info("총 " + result + "건의 원본 데이터 INSERT");

    }

    @Override
    public void checkNulllData(List<NotePadDTO> list){
        int result = mapper.insertNullData();
        logger.info("총 " + result + "건의 NULL(점검) 데이터 INSERT");
    }

    @Override
    public int insertFitData(List<NotePadDTO> list){
        int total_result = 0;

        // 측정소 리스트 추출
        List<String> locationList =  new ArrayList<>();

        for(int i = 1; i < list.size(); i++){
            if(!list.get(i).getLocation().equals(list.get(i-1).getLocation())){
                locationList.add(list.get(i-1).getLocation());
            }
        }

        // 측정소별 데이터 충족 체크 및 INSERT
        for(int i = 1; i < locationList.size(); i++){
            int result = mapper.insertFitData(locationList.get(i));
            logger.info(locationList.get(i) + "의 총 INSERT 데이터 갯수: " + result);
            total_result = total_result + result;
        }

        logger.info("총 추출 데이터 INSERT 건: " + total_result + "건");

        return total_result;
    }

    @Override
    public List<NotePadResultDTO> selectSendData(){
        List<NotePadResultDTO> list = mapper.selectSendData();
        logger.info("size = " + list.size());
        return list;
    }
}
