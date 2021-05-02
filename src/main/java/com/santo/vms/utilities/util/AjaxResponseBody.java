package com.santo.vms.utilities.util;

import lombok.Data;

@Data
public class AjaxResponseBody {
    String responseCode;
    String narrative;
    Object object;

}