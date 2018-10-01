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
    private String cityName1;

    @NonNull
    private String cityName2;

    public boolean containsCityName(String cityName) {

        return (cityName1.equals(cityName) || cityName2.equals(cityName));
    }

    public String findDirectTeleportCityName(String cityName) {
        return cityName1.equals(cityName) ? cityName2 : cityName1;
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

        return new EqualsBuilder().append(cityName1, simpleRoute.getCityName1())
                .append(cityName2, simpleRoute.getCityName2())
                .isEquals();
    }

    @Override
    public int hashCode() {

        return new HashCodeBuilder(17, 37).
                append(cityName1).
                append(cityName2).
                toHashCode();
    }
}
