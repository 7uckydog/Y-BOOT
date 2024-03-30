package com.start.yboot.notepad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class NotePadServiceImpl implements NotePadService{
    @Autowired
    private NotePadMapper mapper;

    @Override
    @Transactional
    public int insOriginDate(List<NotePadDTO> list) {
        int result = 0;
        for(int i=0; i < list.size(); i++){
            mapper.insOriginDate(list.get(i));
            result ++;
        }

        if(list.size() != result){
            System.out.println("INSERT 데이터와 LIST 길이가 맞지 않음.");
        } else {
            System.out.println("INSERT Complete");
        }

        return result;
    }


}
