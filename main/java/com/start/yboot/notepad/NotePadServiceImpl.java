package com.start.yboot.notepad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotePadServiceImpl implements NotePadService{
    @Autowired
    private NotePadMapper mapper;

    @Override
    @Transactional
    public int insOriginDate(List<NotePadDTO> list) throws Exception {
        if(list.size() < 1){
            throw new Exception("INSERT용 데이터가 존재하지 않습니다.");
        }

        int result = 0;
        if(list.size() != 0){
            for(int i=0; i < list.size(); i++){
                mapper.insOriginDate(list.get(i));
                result ++;
            }
        }

        // INSERT 확인
        if(result < 1){
            throw new Exception("INSERT 데이터가 1건도 존재하지 않습니다.");
        }

        // INSERT한 데이터 갯수와 List 갯수 비교
        if(list.size() != result){
            throw new Exception("INSERT 데이터와 LIST 길이가 맞지 않음.");
        }

        return result;
    }

    @Override
    public List<NotePadDTO> checkNulllData(List<NotePadDTO> list){
        List<NotePadDTO> nullList =  new ArrayList<>();

        for(int i = 0; i < list.size(); i++) {
            String pm10 = list.get(i).getPm10();
            String pm25 = list.get(i).getPm25();

            if(pm10 == null || pm25 == null || pm10.equals("") || pm25.equals("")){
                nullList.add(list.get(i));
            }
        }

        // TODO nullList를 가지고 측정소 점검날 데이터 INSERT

        return nullList;
    }

    @Override
    public int insertFitDate(List<NotePadDTO> list){
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
            int result = mapper.insertFitDate(locationList.get(i));
            System.out.println(locationList.get(i) + "의 총 INSERT 데이터 갯수: " + result);
            total_result = total_result + result;
        }

        System.out.println("총 추출 데이터 INSERT 건: " + total_result + "건");

        return total_result;
    }
}
