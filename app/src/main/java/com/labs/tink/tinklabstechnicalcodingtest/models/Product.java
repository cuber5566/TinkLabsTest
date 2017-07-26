package com.labs.tink.tinklabstechnicalcodingtest.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by cuber on 2017/7/25.
 */

@Getter
@Setter
public class Product {

    public static final String TYPE_FILL = "FILL";
    public static final String TYPE_IMAGE = "IMAGE";

    private String id;
    private String title;
    private String content;
    private String image;
    private String type;
    private String updated;
    private String created;
}
