package com.vti.vtiacademy.modal.dto;

import lombok.Data;

@Data
public class SearchBase {
    protected String sortType; //DESC: giảm dần, AESC: tắng dần
    protected String sortField; //Cột được sắp xếp

    protected int pageSize;
    protected int pageNumber;
}
