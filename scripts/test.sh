#!/usr/bin/env bash

test_data="Fortuna - Hemingway
Fortuna - Atlantis
Hemingway - Chesterfield
Chesterfield - Springton
Los Amigos - Paristown
Paristown - Oaktown
Los Amigos - Oaktown
Summerton - Springton
Summerton - Hemingway
cities from Summerton in 1 jumps
cities from Summerton in 2 jumps
can I teleport from Springton to Atlantis
can I teleport from Oaktown to Atlantis
loop possible from Oaktown
loop possible from Fortuna"

java -jar target/teleporter-code-challenge-0.0.1-SNAPSHOT.jar "$test_data"