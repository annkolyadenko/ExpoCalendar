package ua.com.expo.dto;

import java.io.Serializable;

public abstract class AbstractDto implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    private Long id;

    public AbstractDto() {
    }

    public AbstractDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
