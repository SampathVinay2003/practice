public class PricingStrategy_Impl_SurgePricing implements PricingStrategy {
    private double multiplier;
    public PricingStrategy_Impl_SurgePricing(double multiplier) { this.multiplier = multiplier; }
    public double apply(double fare) { return fare * multiplier; }
}
