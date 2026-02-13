package designPatterns.splitwiseDesignPractice.strategies;

import designPatterns.splitwiseDesignPractice.entities.Split;
import designPatterns.splitwiseDesignPractice.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SplitStrategyPercentage_Impl implements SplitStrategy {
    @Override
    public List<Split> splits(List<User> participants, double amount, Map<User, Double> metaData) throws Exception {
        double totalPercent = participants.stream().mapToDouble(user->metaData.get(user)).sum();
        if(totalPercent != 100.0){
            throw new Exception("100% it hsould be total");
        }
        List<Split> splits = new ArrayList<>();
        for(User user: participants){
            splits.add(new Split((metaData.get(user)*amount)/100, user));
        }
        return splits;
    }
}
