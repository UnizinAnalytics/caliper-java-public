package org.imsglobal.caliper.events.assessment;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.imsglobal.caliper.actions.AssessmentActions;
import org.imsglobal.caliper.events.CaliperEvent;

import java.util.ResourceBundle;

public class AssessmentEvent extends CaliperEvent {

    @JsonProperty("@context")
    private final String context;

    @JsonProperty("@type")
    private final String type;

    @JsonProperty("action")
    private final String action;

    /**
     * @param builder apply builder object properties to the AssessmentEvent object.
     */
    protected AssessmentEvent(Builder<?> builder) {
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
            context(CaliperEvent.Context.ASSESSMENT.uri());
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
            if (AssessmentActions.hasKey(key)) {
                this.action = ResourceBundle.getBundle("actions").getString(key);
                return self();
            } else {
                throw new IllegalArgumentException("Unrecognized constant: " + key);
            }
        }

        /**
         * Client invokes build method in order to create an immutable object.
         * @return a new instance of AssessmentEvent.
         */
        public AssessmentEvent build() {
            return new AssessmentEvent(this);
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