package net.ict.springex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;
    private int size;
    private int total;
    private int start;  //시작페이지 번호
    private int end;    //끝페이지 번호
    private boolean prev;   // 이전 페이지의 존재 여부
    private boolean next;   // 다음 페이지의 존재 여부
    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO,
                           List<E> dtoList, int total){
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;

        // 현재페이지가 5이상일때부터 항상 현재페이지가 페이지의 가운데에 위치하도록
        // 시작페이지는 현재페이지가 5이하라면 1이고 5이상이면 현재페이지-4
        this.start = page<=5 ? 1: page-4;
        // 끝페이지는 현재페이지가 5이하면 10이고 5이상이면 현재페이지 +4
        this.end = page<=5 ? 10: page+4;

        //this.end = (int)(Math.ceil(this.page / 10.0 )) * 10;
        //this.start = this.end - 9;
        
        // 마지막 페이지는 총 게시글 수를 한페이지당 글개수로 나누어서 반올림.
        int last = (int)(Math.ceil((total/(double)size)));
        // 앞에서 구한 마지막페이지 값보다 작은경우 마지막페이지는 end
        this.end = end > last ? last: end;
        //이전 페이지는 시작페이지가 1이 아니라면 무조건  true
        this.prev = this.start > 1;
        //다음페이지는 끝페이지와 페이지당 게시글 수를 곱한 값보다 전체 게시글수가 더 많은지 체크 후 판단.
        this.next = total > this.end * this.size;

    }
}
