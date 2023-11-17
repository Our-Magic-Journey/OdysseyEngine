package xyz.nebulaquest.event;

import java.util.function.Consumer;

public interface ParameterizedEventGetter<T> extends Subscribable<Consumer<T>> {}
