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
            service.insOriginDate(list);

            /* NULL data 리스트 추출 후, INSERT : 측정소 점검날 */
            // NULL data 리스트 추출 완료
            // TODO INSERT
            service.checkNulllData(list);

            // -- 측정소 리스트 출력 후, 측정소 별 추출 데이터 INSERT
            service.insertFitDate(list);

            // -- INSERT한 데이터 SELECT하여 TCP send
            // -- TCP 통신 시, 발생한 시간 순서대로 전송


        } catch (IOException e) {
            throw new IOException(e);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {

        }


    }
}
