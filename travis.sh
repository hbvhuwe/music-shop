#!/bin/bash
#
# Script for continuous integration verification

# Building api image
( cd music-shop-api; docker build -t ms-api . )

# Building web application image
( cd music-shop-web; docker build -t ms-web . )

# Building desktop application

( cs music-shop-client; ./gradlew build )
