package designPatterns.splitwiseDesignPractice;

import designPatterns.splitwiseDesignPractice.entities.Group;
import designPatterns.splitwiseDesignPractice.entities.User;
import designPatterns.splitwiseDesignPractice.enums.SplitType;
import designPatterns.splitwiseDesignPractice.service.GroupService;

import java.util.List;

public class Client {
    public static void main(String[] a) throws Exception {
        try {
            User sampath = new User("Sampath");

            User surya = new User("Surya");
            User vishnu = new User("Vishnu");
            GroupService groupService = new GroupService();
            Group goaGroup = groupService.createNewGroup("Goa trip");
            groupService.addMember(goaGroup, vishnu);
            groupService.addExpense(goaGroup, "Lunch_day-1", List.of(sampath, surya, vishnu), SplitType.Equal, surya, 100);
            groupService.addExpense(goaGroup, "Lunch_day-2", List.of(sampath, surya, vishnu), SplitType.Equal, sampath, 100);
            
            // Display balance sheets for all users
            groupService.showBalanceSheets(goaGroup);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
