DROP TABLE origin_data;
DROP TABLE send_data;
DROP TABLE monitoring_date;
DROP TABLE rank_info;
DROP SEQUENCE data_seq;


CREATE TABLE origin_data (data_seq varchar2(10),
                          location varchar2(16),
                          pm25 varchar2(4),
                          pm10 varchar2(4),
                          info_date varchar2(13),
                          location_code varchar2(6));

CREATE TABLE send_data (org_seq varchar2(10),
                        af_info_date varchar2(13),
                        af25_avg varchar2(6),
                        af10_avg varchar2(6),
                        alert_rank varchar2(1));

CREATE TABLE monitoring_date (org_seq varchar2(10),
                              location varchar2(16),
                              pm25 varchar2(4),
                              pm10 varchar2(4),
                              info_date varchar2(13),
                              location_code varchar2(6));

CREATE TABLE rank_info (alert_rank varchar2(1),
                        rank_name varchar2(27),
                        rank_info varchar2(60));

INSERT INTO RANK_INFO VALUES('1','초미세먼지 경보','가장 심각한 상태, 건강에 매우 해로움');
INSERT INTO RANK_INFO VALUES('2','미세먼지 경보','건강에 매우 해로울 수 있음');
INSERT INTO RANK_INFO VALUES('3','초미세먼지 주의보','건강에 해로울 수 있음');
INSERT INTO RANK_INFO VALUES('4','미세먼지 주의보','건강에 약간 해로울 수 있음');

CREATE SEQUENCE data_seq
    INCREMENT BY 1
    START WITH 1000000001
    MINVALUE 1000000001
    MAXVALUE 9999999999
    NOCYCLE
       NOCACHE
       NOORDER;

COMMIT;