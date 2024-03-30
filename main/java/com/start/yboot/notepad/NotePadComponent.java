package com.start.yboot.notepad;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotePadComponent {

    @Autowired
    private NotePadService service;

    public void run() throws Exception{

        List<NotePadDTO> list = new ArrayList<>();
        String file_name = "file/2023Y03M.json";

        try{
            /* Json file read */
            ClassPathResource resource = new ClassPathResource(file_name);
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String data;
            StringBuilder sb = new StringBuilder();
            while((data = br.readLine()) != null){
                sb.append(data);
            }

            JSONArray arr = new JSONArray(sb.toString());

            for (int i = 0; i < arr.length(); i++){
                JSONObject jsonObject = arr.getJSONObject(i);

                NotePadDTO vo = new NotePadDTO();
                vo.setInfo_date(jsonObject.get("날짜").toString());
                vo.setLocation(jsonObject.get("측정소명").toString());
                vo.setLocation_code(jsonObject.get("측정소코드").toString());
                if(jsonObject.get("PM10").toString().equals("null")){
                    vo.setPm10("");
                } else {
                    vo.setPm10(jsonObject.get("PM10").toString());
                }
                if(jsonObject.get("PM2.5").toString().equals("null")){
                    vo.setPm25("");
                } else {
                    vo.setPm25(jsonObject.get("PM2.5").toString());
                }

                list.add(vo);
            }

            /* 원본 데이터 INSERT */
            if(list.size() != 0){
                int result = service.insOriginDate(list);

                if(result < 1){
                    throw new Exception("INSERT 데이터가 1건도 존재하지 않습니다.");
                }
            }

            /* TODO 평균 데이터 구하여 발령 정보 추출 및 INSERT */

            // -- NULL 체크 : 측정소 점검날
            // -- 측정소별 평균 계산 필요
            // -- 두 가지 경보에 모두 적합할 경우, 경보 > 주의보 / 초미세먼지 >  (등급표 참고)
            // -- 경보 추출 후 INSERT
            // -- INSERT한 정보 SELECT하여 TCP send
            // -- TCP 통신 시, 발생한 시간 순서대로 전송


        } catch (IOException e) {
            throw new IOException(e);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {

        }


    }
}
