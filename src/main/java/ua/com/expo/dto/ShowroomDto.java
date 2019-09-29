package ua.com.expo.dto;

public class ShowroomDto extends AbstractDto{

    private String name;
    private String info;

    public ShowroomDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
