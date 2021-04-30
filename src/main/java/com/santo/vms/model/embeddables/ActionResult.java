package com.santo.vms.model.embeddables;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActionResult<T> implements Serializable {
    @JsonProperty("Action")
    private String Action;

    @JsonProperty("Message")
    private String Message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;


    public ActionResult<T> buildSuccessResponse(String message) {
        this.Action = "1";
        this.Message = message;
        this.result = null;
        return this;
    }

    public ActionResult<T> buildSuccessResponse(String message, final T result) {
        this.Action = "1";
        this.Message = message;
        this.result = result;
        return this;
    }

    public ActionResult<T> buildErrorResponse(String message) {
        this.Action = "0";
        this.Message = message;
        this.result = null;
        return this;
    }

    public ActionResult<T> buildErrorResponse(String message, final T result) {
        this.Action = "0";
        this.Message = message;
        this.result = result;
        return this;
    }
}
