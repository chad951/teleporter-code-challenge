package com.cortek.solutions.teleportercodechallenge.query;

import lombok.Data;
import lombok.NonNull;

@Data
public class CanITeleportFromXToYQuery {

    @NonNull
    private String teleportOriginCityName;

    @NonNull
    private String teleportDestinationCityName;
}
