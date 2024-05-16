public class Coupons {
    private final String platform;
    private final String coupon;

    // constructor to set the platform and coupon
    public Coupons(String platform, String coupon) {
        this.platform = platform;
        this.coupon = coupon;
    }

    // overridden method to get string representation of platform-coupon/object;
    @Override
    public String toString() {
        return platform + " : " + coupon;
    }
}
