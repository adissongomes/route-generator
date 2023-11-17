# Route Generator

Project for study purposes that will be used as event generator
for another service.

## Technologies
- Java 17
- SpringBoot
- Kafka + Spring Kafka

## Solution

The routes will be created by threads along with some Thread#sleep calls
to simulate the interval between each event until the route reach the last state `COMPLETED`.

There is a simple mechanism to do a rollback for the route status to
force a scenario of "delayed events" or "events out of order". As said before, for study purpose, 
it could be used for a scenario of a idempotent consumer. 

## Running

[//]: # (TODO)