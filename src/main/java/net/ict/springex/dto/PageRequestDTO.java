package net.ict.springex.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

// 페이징 처리는 현재 페이지 번호(page), 한페이지당 데이터의 수 (size)를 정해줘야함.
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    private String[] types;
    private String keyword;
    private boolean finished;
    private LocalDate from;
    private LocalDate to;

    @Builder.Default    //기본값 설정.
    @Min(value = 1)   // 페이지는 최소 1페이지 존재.
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;   // 한페이지에 10개씩

    public int getSkip(){
        return (page-1)*10;  //페이지 수가 한장씩 늘어날 때마다 10개씩 Skip
    }

    public boolean checkType(String type){
        if (types==null || types.length==0){
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }
    public String getLink(){
        StringBuilder builder= new StringBuilder();
        builder.append("pag=" +this.page);
        builder.append("&size=" + this.size);
        if(finished){
            builder.append("&finished=on");
        }
        if(types !=null && types.length>0){
            for (int i=0;i< types.length;i++){
                builder.append("&types="+ types[i]);
            }
        }
        if(keyword !=null){
            try {
                builder.append("&keyword=" + URLEncoder.encode(keyword,"UTF-8"));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (from != null) {
            builder.append("&from="+from.toString());
        }
        if(to!=null){
            builder.append("&to=" + to.toString());
        }
        return  builder.toString();
    }


}
