package w.p.j.daomain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_userorder")
public class TbUserorder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String openid;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_order_date")
    private Date userOrderDate;

    @Column(name = "user_booking")
    private Date userBooking;

    @Column(name = "user_total")
    private Float userTotal;

    @Column(name = "user_time")
    private Float userTime;

    @Column(name = "user_other")
    private String userOther;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * @param openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return user_phone
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * @param userPhone
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * @return user_address
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * @param userAddress
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    /**
     * @return user_order_date
     */
    public Date getUserOrderDate() {
        return userOrderDate;
    }

    /**
     * @param userOrderDate
     */
    public void setUserOrderDate(Date userOrderDate) {
        this.userOrderDate = userOrderDate;
    }

    /**
     * @return user_booking
     */
    public Date getUserBooking() {
        return userBooking;
    }

    /**
     * @param userBooking
     */
    public void setUserBooking(Date userBooking) {
        this.userBooking = userBooking;
    }

    /**
     * @return user_total
     */
    public Float getUserTotal() {
        return userTotal;
    }

    /**
     * @param userTotal
     */
    public void setUserTotal(Float userTotal) {
        this.userTotal = userTotal;
    }

    /**
     * @return user_time
     */
    public Float getUserTime() {
        return userTime;
    }

    /**
     * @param userTime
     */
    public void setUserTime(Float userTime) {
        this.userTime = userTime;
    }

    /**
     * @return user_other
     */
    public String getUserOther() {
        return userOther;
    }

    /**
     * @param userOther
     */
    public void setUserOther(String userOther) {
        this.userOther = userOther;
    }

    @Override
    public String toString() {
        return "TbUserorder{" +
                "id=" + id +
                ", openid='" + openid + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userOrderDate=" + userOrderDate +
                ", userBooking=" + userBooking +
                ", userTotal=" + userTotal +
                ", userTime=" + userTime +
                ", userOther='" + userOther + '\'' +
                '}';
    }
}