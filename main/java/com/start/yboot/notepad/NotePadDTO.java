package com.start.yboot.notepad;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotePadDTO {
    private String location;
    private String pm25;
    private String pm10;
    private String info_date;
    private String location_code;

    private String before_pm25;
    private String after_pm25;
    private String before_pm10;
    private String after_pm10;
}
