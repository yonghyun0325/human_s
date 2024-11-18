package com.human.hms.vo;


import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

@Data
public class ProductLocalVO {
		private Header header;
		private Body body;
    
    @Data
    public static class Header{
    	private String resultCode;
    	private String resultMsg;
    	private String requestParameter;
    }
    
    @Data
    public static class Body{
    	private Items items;
    }
    
    @Data
    public static class Items{
    	@JacksonXmlElementWrapper(useWrapping = false) // 'item' 배열 감싸는 태그 무시
        @JacksonXmlProperty(localName = "item")  
    	private List<ProductLocal> item;
    	private int numOfRows;
    	private int pageNo;
    	private int totalCount;
    }
    
    @Data
    public static class ProductLocal {
        private String areaNm;	 //지역명
        private String cntntsNo; //콘텐츠 번호
        private String cntntsSj; //콘텐츠 제목
        private String imgUrl;	 //이미지 URL
        private String linkUrl;  //관련사이트 URL
        private String svcDt;	 //등록일
    }
    
}
