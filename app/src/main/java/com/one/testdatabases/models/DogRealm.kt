package com.one.testdatabases.models

import io.realm.RealmObject

open class DogRealm(
    var dogID: Long = 1L,
    var name: String = ""
) : RealmObject()