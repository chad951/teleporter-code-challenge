package com.cortek.solutions.teleportercodechallenge.route;

import lombok.*;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class SimpleRoute {

    public static final String NULL_ROUTE_INPUT_LINE_MESSAGE = "Route inputLine cannot be null";

    public static final String BLANK_ROUTE_INPUT_LINE_MESSAGE = "Route inputLine cannot be blank";

    @NonNull
    private String originCityName;

    @NonNull
    private String destinationCityName;

    public boolean containsCityName(String cityName) {

        return (originCityName.equals(cityName) || destinationCityName.equals(cityName));
    }

    public static SimpleRoute createSimpleRoute(String routeInputLine) {

        Validate.notNull(routeInputLine, NULL_ROUTE_INPUT_LINE_MESSAGE);
        Validate.notBlank(routeInputLine.trim(), BLANK_ROUTE_INPUT_LINE_MESSAGE);

        String[] parsedRoute = routeInputLine.replaceAll(" ", "").split("-");

        return new SimpleRoute(parsedRoute[0], parsedRoute[1]);
    }

    @Override
    public boolean equals(Object o) {

        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof SimpleRoute)) return false;
        SimpleRoute simpleRoute = (SimpleRoute)o;

        boolean directComparision = new EqualsBuilder().append(originCityName, simpleRoute.getOriginCityName())
                .append(destinationCityName, simpleRoute.getDestinationCityName())
                .isEquals();

        boolean inverseComparison = new EqualsBuilder().append(originCityName, simpleRoute.getDestinationCityName())
                .append(destinationCityName, simpleRoute.getOriginCityName())
                .isEquals();

        return directComparision || inverseComparison;

    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(17, 37).
                append(originCityName).
                append(destinationCityName).
                toHashCode();
    }
}
