package com.bao.example.thuctap.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBase {

    private Object data;

    private long total;

    private String message;

    private String code;


    public ResponseBase(Object data, long total) {
        this.message = "Yêu cầu thực hiện thành công!";
        this.code = "200";
        this.total = total;
        this.data = data;
    }


    public ResponseBase(Object data) {
        this.message = "Yêu cầu thực hiện thành công!";
        this.code = "200";
        this.data = data;
    }


}
