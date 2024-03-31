package com.start.yboot.notepad;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotePadResultDTO {
    private String location;
    private String pm25;
    private String pm10;
    private String info_date;
    private String location_code;

    private String af25_avg;
    private String af10_avg;
    private String af_info_date;
    private String alert_rank;
    private String rank_name;
    private String rank_info;
    private String avg_info;
}
