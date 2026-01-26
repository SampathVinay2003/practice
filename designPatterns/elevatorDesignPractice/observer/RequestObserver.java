package designPatterns.elevatorDesignPractice.observer;

import designPatterns.elevatorDesignPractice.request.ExternalRequest;
import designPatterns.elevatorDesignPractice.request.InternalRequest;

public interface RequestObserver {
    default void onExternalRequest(ExternalRequest request) {}
    default void onInternalRequest(InternalRequest request) {}
}
