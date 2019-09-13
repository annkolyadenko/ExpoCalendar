package ua.com.expo.dto;

import java.util.ArrayList;
import java.util.Collection;

public class RoleDto extends AbstractDto {

    private String type;
    //to do!
    private Collection<AbstractDto> list;

    public RoleDto() {
    }

    public RoleDto(Long id, String type) {
        super(id);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<AbstractDto> getList() {
        return list;
    }

    public void setList(Collection<AbstractDto> list) {
        this.list = list;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        RoleDto roleDto = (RoleDto) super.clone();
        roleDto.setList(cloneList(this.list));
        return roleDto;
    }

    private static Collection<AbstractDto> cloneList(Collection<AbstractDto> list) throws CloneNotSupportedException {
        Collection<AbstractDto> clone = new ArrayList<>(list.size());
        for (AbstractDto item : list) clone.add((AbstractDto) item.clone());
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("type: " + type);
        sb.append(", id: " + this.getId());
        return sb.toString();
    }
}
