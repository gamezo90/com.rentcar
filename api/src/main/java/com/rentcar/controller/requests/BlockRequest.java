package com.rentcar.controller.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * This request need to software remove or block an entity.
 * If the isDeleted parameter is null - temporary blocking;
 * true - software remove;
 * false - unblock.
 */
@Getter
@Setter
public class BlockRequest {

    @ApiModelProperty(required = true, allowableValues = "false", dataType = "boolean")
    Boolean isDeleted;
}
