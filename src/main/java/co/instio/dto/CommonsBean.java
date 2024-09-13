package co.instio.dto;


import co.instio.utils.JsonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public abstract class CommonsBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    @JsonIgnore
    public String toString() {
        return JsonUtil.toString(this);
    }


}
