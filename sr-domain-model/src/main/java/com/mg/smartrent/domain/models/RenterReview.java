package com.mg.smartrent.domain.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = false)
public class RenterReview extends BizItem {

    @NotNull
    @Size(min = 1, max = 100)
    private String userTID;

    @NotNull
    @Size(min = 1, max = 100)
    private String renterTID;

    @NotNull
    @Size(min = 1, max = 1000000)
    private String review;

    @Min(1)
    @Max(5)
    private Integer rating;


    public RenterReview() {

    }
}
