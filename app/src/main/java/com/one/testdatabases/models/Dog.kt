package com.one.testdatabases.models


class Dog(
    val dogID: Long,
    var name: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dog

        if (dogID != other.dogID) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = dogID.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

}