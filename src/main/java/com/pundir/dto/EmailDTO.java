package com.pundir.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class EmailDTO {
    //private String from;
    @NotBlank
    @Email
    @ApiModelProperty(example = "abc@gmail.com", required = true)
    private String to;

    @ApiModelProperty(example = "write your subject (optional).")
    private String subject;

    @ApiModelProperty(example = "write your body (optional).")
    private String body;

}
