# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

:java_version:1.8
:spring_version: 5.1.6.RELEASE
:project_id: nearby
:spring_boot_version: 2.1.4.RELEASE
:foursquare_version:1.0.6


== List of REST EndPoints

***Note: All endpoints would be supporting GET only and are configuered to use FourSquare API's.

(1)
http://localhost:8080/searchlocation?ll=<VALUE>
----

It will respond with JSON representation of a venue (latitude and longitude provided)
eg:http://localhost:8080/searchlocation?ll==40.7337621,-74.0095604


(2)
http://localhost:8080/filterbycategory?ll=<VALUE>&category=<VALUE>
----
It will respond back with list of venue matching given location(latitude and longitude) and category.

eg: http://localhost:8080/filterbycategory?ll=40.7337621,-74.0095604&category=mobile

(3)
http://localhost:8080/filterbycategory?name=<LOCATION>&category=<VALUE>
----
It will respond back with list of venue matching given location name and category.

eg:http://localhost:8080/filterbycategory?name=pune&category=coffee

All successful request would be responded with STATUS CODE=200 (OK). Any error due to incorrect parameter would be responded with STATUS CODE =400(BAD REQUEST)
For all application exception , STATUS CODE would be 404(NOT FOUND)  


==== JUNIT Test Case =====
Run com.group.app.ApplicationTest as JUnit program.