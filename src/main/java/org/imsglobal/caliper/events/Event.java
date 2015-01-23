package org.imsglobal.caliper.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.schemadotorg.SoftwareApplication;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.foaf.Agent;
import org.imsglobal.caliper.entities.lis.Organization;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * Base class for all Caliper Events
 */
@JsonPropertyOrder({
        "@context",
        "@type",
        "actor",
        "action",
        "object",
        "target",
        "generated",
        "startedAtTime",
        "endedAtTime",
        "duration",
        "edApp",
        "group" })
public abstract class Event {

    public enum Context {
        ANNOTATION("http://purl.imsglobal.org/ctx/caliper/v1/AnnotationEvent"),
        ASSESSMENT("http://purl.imsglobal.org/ctx/caliper/v1/AssessmentEvent"),
        ASSESSMENT_ITEM("http://purl.imsglobal.org/ctx/caliper/v1/AssessmentItemEvent"),
        ASSIGNABLE("http://purl.imsglobal.org/ctx/caliper/v1/AssignableEvent"),
        EVENT("http://purl.imsglobal.org/ctx/caliper/v1/Event"),
        MEDIA("http://purl.imsglobal.org/ctx/caliper/v1/MediaEvent"),
        NAVIGATION("http://purl.imsglobal.org/ctx/caliper/v1/NavigationEvent"),
        OUTCOME("http://purl.imsglobal.org/ctx/caliper/v1/OutcomeEvent"),
        SESSION("http://purl.imsglobal.org/ctx/caliper/v1/SessionEvent"),
        VIEW("http://purl.imsglobal.org/ctx/caliper/v1/ViewEvent");

        private final String uri;

        /**
         * Private constructor
         * @param uri
         */
        private Context(final String uri) {
            this.uri = uri;
        }

        /**
         * @return URI string
         */
        public String uri() {
            return uri;
        }
    }

    public enum Type {
        ANNOTATION("http://purl.imsglobal.org/caliper/v1/AnnotationEvent"),
        ASSESSMENT("http://purl.imsglobal.org/caliper/v1/AssessmentEvent"),
        ASSESSMENT_ITEM("http://purl.imsglobal.org/caliper/v1/AssessmentItemEvent"),
        ASSIGNABLE("http://purl.imsglobal.org/caliper/v1/AssignableEvent"),
        EVENT("http://purl.imsglobal.org/caliper/v1/Event"),
        MEDIA("http://purl.imsglobal.org/caliper/v1/MediaEvent"),
        NAVIGATION("http://purl.imsglobal.org/caliper/v1/NavigationEvent"),
        OUTCOME("http://purl.imsglobal.org/caliper/v1/OutcomeEvent"),
        SESSION("http://purl.imsglobal.org/caliper/v1/SessionEvent"),
        VIEW("http://purl.imsglobal.org/caliper/v1/ViewEvent");

        private final String uri;

        /**
         * Private constructor
         * @param uri
         */
        private Type(final String uri) {
            this.uri = uri;
        }

        /**
         * @return URI string
         */
        public String uri() {
            return uri;
        }
    }

    /**
     * Required - the JSON-LD context for the Event
     */
    @JsonProperty("@context")
    private final String context;

    /**
     * Required - the type of the Event
     */
    @JsonProperty("@type")
    private final String type;

    /**
     * Learning Context
     */
    @JsonProperty("edApp")
    private SoftwareApplication edApp;

    /**
     * Learning Context
     */
    @JsonProperty("group")
    private Organization lisOrganization;

    /**
     * Required - Agent (User, System) that performed the action
     */
    @JsonProperty("actor")
    private final Agent actor;

    /**
     * Required - Action performed by Agent From Metric Profile
     */
    @JsonProperty("action")
    private final String action;

    /**
     * Required - "Activity Context" from Metric Profile
     */
    @JsonProperty("object")
    private Object object;

    /**
     * Optional - "target" from Metric Profile
     */
    @JsonProperty("target")
    private Targetable target;

    /**
     * Optional - entity "generated" as result of action - from Metric Profile
     */
    @JsonProperty("generated")
    private Generatable generated;

    /**
     * Required time in milliseconds that the event was started at
     */
    @JsonProperty("startedAtTime")
    private long startedAtTime;

    /**
     * An optional time in milliseconds that the event ended at
     */
    @JsonProperty("endedAtTime")
    private long endedAtTime;

    /**
     * An xsd:duration (http://books.xmlschemata.org/relaxng/ch19-77073.html)
     * The format is expected to be PnYnMnDTnHnMnS
     * Valid values include PT1004199059S, PT130S, PT2M10S, P1DT2S, -P1Y, or P1Y2M3DT5H20M30.123S.
     * The following values are invalid: 1Y (leading P is missing), P1S (T separator is missing),
     * P-1Y (all parts must be positive), P1M2Y (parts order is significant and Y must precede M),
     * or P1Y-1M (all parts must be positive).
     */
    @JsonProperty("duration")
    private String duration;

    /**
     * @param builder apply builder object properties to the Event object.
     */
    protected Event(Builder<?> builder) {
        this.context = builder.context;
        this.type = builder.type;
        this.edApp = builder.edApp;
        this.lisOrganization = builder.lisOrganization;
        this.actor = builder.actor;
        this.action = builder.action;
        this.object = builder.object;
        this.target = builder.target;
        this.generated = builder.generated;
        this.startedAtTime = builder.startedAtTime;
        this.endedAtTime = builder.endedAtTime;
        this.duration = builder.duration;
    }

    /**
     * @return the context
     */
    @NotNull
    public String getContext() {
       return context;
   }

    /**
     * @return the type
     */
    @NotNull
    public String getType() {
       return type;
   }

    /**
     * @return the edApp
     */
    @Nullable
    public SoftwareApplication getEdApp() {
        return edApp;
    }

    /**
     * @return the lisOrganization
     */
    @Nullable
    public Organization getLisOrganization() {
        return lisOrganization;
    }

    /**
     * @return the actor
     */
    @NotNull
    public Agent getActor() {
        return actor;
    }

    /**
     * @return the action
     */
    @NotNull
    public String getAction() {
        return action;
    }

    /**
     * @return the object
     */
    @NotNull
    public Object getObject() {
        return object;
    }

    /**
     * @return the target
     */
    @Nullable
    public Targetable getTarget() {
        return target;
    }

    /**
     * @return generated
     */
    @Nullable
    public Generatable getGenerated() {
        return generated;
    }

    /**
     * @return the startedAt time
     */
    @NotNull
    public long getStartedAtTime() {
        return startedAtTime;
    }

    /**
     * @return endedAt time
     */
    @Nullable
    public long getEndedAtTime() {
        return endedAtTime;
    }

    /*;
     * @return the duration
     */
    @Nullable
    public String getDuration() {
        return duration;
    }

    /**
     * Builder class provides a fluid interface for setting object properties.
     * @param <T> builder
     */
    public static abstract class Builder<T extends Builder<T>> {
        private String context;
        private String type;
        private SoftwareApplication edApp;
        private Organization lisOrganization;
        private Agent actor;
        private String action;
        private Object object;
        private Targetable target;
        private Generatable generated;
        private long startedAtTime;
        private long endedAtTime;
        private String duration;

        protected abstract T self();

        public Builder() {
            this.context(Context.EVENT.uri());
            this.type(Type.EVENT.uri());
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
         * @param edApp
         * @return builder.
         */
        public T edApp(SoftwareApplication edApp) {
            this.edApp = edApp;
            return self();
        }

        /**
         * @param lisOrganization
         * @return builder.
         */
        public T lisOrganization(Organization lisOrganization) {
            this.lisOrganization = lisOrganization;
            return self();
        }

        /**
         * @param actor
         * @return builder.
         */
        public T actor(Agent actor) {
            this.actor = actor;
            return self();
        }

        /**
         * @param action
         * @return builder.
         */
        public T action(String action) {
            this.action = action;
            return self();
        }

        /**
         * @param object
         * @return builder.
         */
        public T object(Object object) {
            this.object = object;
            return self();
        }

        /**
         * @param target
         * @return builder.
         */
        public T target(Targetable target) {
            this.target = target;
            return self();
        }

        /**
         * @param generated
         * @return builder.
         */
        public T generated(Generatable generated) {
            this.generated = generated;
            return self();
        }

        /**
         * @param startedAtTime
         * @return builder.
         */
        public T startedAtTime(long startedAtTime) {
            this.startedAtTime = startedAtTime;
            return self();
        }

        /**
         * @param endedAtTime
         * @return builder.
         */
        public T endedAtTime(long endedAtTime) {
            this.endedAtTime = endedAtTime;
            return self();
        }

        /**
         * @param duration
         * @return builder.
         */
        public T duration(String duration) {
            this.duration = duration;
            return self();
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
}