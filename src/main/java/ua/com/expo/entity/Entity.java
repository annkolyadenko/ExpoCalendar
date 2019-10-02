package ua.com.expo.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    protected Long id;

    public Entity() {
    }

    public Entity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
