package designPatterns.splitwiseDesignPractice.strategies;

import designPatterns.splitwiseDesignPractice.entities.Split;
import designPatterns.splitwiseDesignPractice.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SplitStrategyEqual_Impl implements SplitStrategy{

    @Override
    public List<Split> splits(List<User> participants, double amount, Map<User, Double> metadata) {
        int n = participants.size();
        double eachShare =  amount/n;
        List<Split> splits = new ArrayList<>();
        participants.forEach(participant ->splits.add( new Split(eachShare, participant)));
        return splits;
    }
}
