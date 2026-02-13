package designPatterns.splitwiseDesignPractice.strategies;

import designPatterns.splitwiseDesignPractice.entities.Split;
import designPatterns.splitwiseDesignPractice.entities.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    List<Split> splits(List<User> participants, double amount, Map<User, Double> metadata) throws Exception;
}
