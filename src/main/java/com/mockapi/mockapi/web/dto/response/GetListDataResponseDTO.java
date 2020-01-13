package com.mockapi.mockapi.web.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetListDataResponseDTO<T> extends BaseResponseDTO {
   private List<T> datas;
    private Long totalRows = 0L;
    private Integer totalPages = 0;

    private void setSuccess(List<T> datas, Long totalRows, Integer totalPages) {
        super.setSuccess();
        this.datas = datas;
        this.totalRows = totalRows;
        this.totalPages = totalPages;
    }

    public void setResult(List<T> datas, Long totalRows, Integer totalPages) {
        if (datas != null) {
            this.setSuccess(datas, totalRows, totalPages);
        } else {
            super.setFailed();
        }
    }

    public void value(List<T> datas) {
        super.setSuccess();
        this.datas = datas;
    }
    public void setValue(List<T> datas) {
       if(datas !=null){
           this.value(datas);
       }else {
           super.setFailed();
       }
    }

    @Override
    public String toString() {
        return "GetListDataResponse { " +
                "datas = " + datas +
                ", totalRows = " + totalRows +
                ", totalPage = " + totalPages +
                " }";
    }

}
