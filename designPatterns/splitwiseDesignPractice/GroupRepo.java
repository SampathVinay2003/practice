package designPatterns.splitwiseDesignPractice;

import designPatterns.splitwiseDesignPractice.entities.Group;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class GroupRepo {
    private Map<Long, Group> groupMap = new ConcurrentHashMap<>();

    public Group findGroupById(String id) {
        if (groupMap.containsKey(id)) return groupMap.get(id);
        return null;
    }

    public void addGroup(Group group) {
        groupMap.put(Long.parseLong(group.getId()), group);
    }
}
