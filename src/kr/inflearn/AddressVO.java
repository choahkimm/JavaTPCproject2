package kr.inflearn;

public class AddressVO {
    private String roadAddress;
    private String jibunAddress;
    private String x;
    private String y;

    public AddressVO(String roadAddress, String jibunAddress, String x, String y) {
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.x = x;
        this.y = y;
    }

    public AddressVO() {

    }

    public String getRoadAddress() {
        return roadAddress;
    }

    public void setRoadAddress(String roadAddress) {
        this.roadAddress = roadAddress;
    }

    public String getJibunAddress() {
        return jibunAddress;
    }

    public void setJibunAddress(String jibunAddress) {
        this.jibunAddress = jibunAddress;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    // 정보를 한번에 문자열로 리턴
    @Override
    public String toString() {
        return "AddressVO{" +
                "roadAddress='" + roadAddress + '\'' +
                ", jibunAddress='" + jibunAddress + '\'' +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                '}';
    }
}
