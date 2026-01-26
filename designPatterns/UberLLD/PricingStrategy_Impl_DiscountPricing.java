public class PricingStrategy_Impl_DiscountPricing implements PricingStrategy {
    private double discountRate;
    public PricingStrategy_Impl_DiscountPricing(double discountRate) { this.discountRate = discountRate; }
    public double apply(double fare) { return fare - (fare * discountRate); }
}
