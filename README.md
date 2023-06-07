# Cinema management application

This application allows the managment of resources such as auditoriums, films, snack and other things in a cinema.
It handles different roles to execute tasks and the main activity is purchasing ticket to watch movies in the cinema this is achieved with the user role when this actions is from internet or from a sales role when the user is buying directly in the cinema.
There are actions related to maintenance and those happen when the auditoriums or the chairs require any fix in the physical behavior.

## Technology Stack

- SBT 1.8.1
- Scala 2.13.10
- Scala Test 3.2.16
- Mockito 3.2.15.0
- Log4J 2.19.0

### Management Project
- Akka HTTP 10.5.1
- Cats 2.9.0
- SprayJson 10.5.1

## Endpoints
All endpoints are under the `/api/v1` context, and it has a subcontext according to the subdomain:

- Security Subdomain `/ias/`
  - [POST] Sign On
  - [POST] Sign In
  - [POST] Logout
  - [GET]  Search Roles
  - [POST] Validate token
  - [POST] Validate permissions
- Management Subdomain `/management/`
  - [POST] Add Snack
  - [POST] Send chair to maintenance
  - [POST] Create Auditorium
  - [GET]  Search Auditoriums
  - [POST] Request cleaning Auditorium
  - [POST] Add new Film
  - [GET]  Search films
  - [POST] Add Schedule
  - [GET]  Find schedules
- Booking Subdomain `/booking/`
  - [GET]  Search Auditoriums
  - [GET]  Search Films
  - [POST] Book Film
  - [GET]  Search schedules
  - [POST] Book Schedule
  - [GET]  Search Snacks
  - [POST] Book a Ticket
- Billing Subdomain `/payments/`
  - [GET]  Get Membership information
  - [POST] Pay a Ticket