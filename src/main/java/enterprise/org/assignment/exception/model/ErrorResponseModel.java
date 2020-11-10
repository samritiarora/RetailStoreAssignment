package enterprise.org.assignment.exception.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@lombok.Getter
@lombok.Builder
@ApiModel(description = "Model representing the error response")
public class ErrorResponseModel {

    @ApiModelProperty(notes = "Identifier for the enterprise error code", required = true)
    private String code;

    @ApiModelProperty(notes = "Gives proper message")
    private String message;

}
