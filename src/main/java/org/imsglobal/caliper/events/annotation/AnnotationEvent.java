package org.imsglobal.caliper.events.annotation;

import org.imsglobal.caliper.actions.AnnotationActions;
import org.imsglobal.caliper.events.CaliperEvent;

import java.util.ResourceBundle;

public class AnnotationEvent extends CaliperEvent {

    private final String context;
    private final String type;
    private final String action;

    /**
     * @param builder apply builder object properties to the AnnotationEvent object.
     */
    protected AnnotationEvent(Builder<?> builder) {
        super(builder);
        this.context = builder.context;
        this.type = builder.type;
        this.action = builder.action;
    }

    /**
     * @return the context
     */
    @Override
    public String getContext() {
        return context;
    }

    /**
     * @return the type
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     * @return the action
     */
    @Override
    public String getAction() {
        return action;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> extends CaliperEvent.Builder<T>  {
        private String context;
        private String type;
        private String action;

        /**
         * Initialize with default values.
         */
        public Builder() {
            context(CaliperEvent.Context.ANNOTATION.uri());
            type(CaliperEvent.Type.ANNOTATION.uri());
        }

        /**
         * @param context
         * @return builder.
         */
        private T context(String context) {
            this.context = context;
            return self();
        }

        /**
         * @param type
         * @return builder.
         */
        private T type(String type) {
            this.type = type;
            return self();
        }

        /**
         * @param key
         * @return builder
         */
        @Override
        public T action(String key) {
            if (AnnotationActions.hasKey(key)) {
                this.action = ResourceBundle.getBundle("actions").getString(key);
                return self();
            } else {
                throw new IllegalArgumentException("Unrecognized constant: " + key);
            }
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of AnnotationEvent.
         */
        public AnnotationEvent build() {
            return new AnnotationEvent(this);
        }
    }

    /**
     *
     */
    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }

    /**
     * Static factory method.
     * @return a new instance of the builder.
     */
    public static Builder<?> builder() {
        return new Builder2();
    }
}