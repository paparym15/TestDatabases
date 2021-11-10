package com.one.testdatabases.mappers

import com.one.testdatabases.models.Dog
import com.one.testdatabases.models.DogRealm

fun Dog.toRealm(): DogRealm {
    return DogRealm(
        dogID = dogID,
        name = name
    )
}