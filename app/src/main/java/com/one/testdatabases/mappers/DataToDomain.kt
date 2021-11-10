package com.one.testdatabases.mappers

import com.one.testdatabases.models.Dog
import com.one.testdatabases.models.DogRealm

fun DogRealm.toDomain(): Dog {
    return Dog(
        dogID,
        name
    )
}