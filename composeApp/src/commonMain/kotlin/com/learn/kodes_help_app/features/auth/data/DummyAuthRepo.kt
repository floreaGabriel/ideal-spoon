package com.learn.kodes_help_app.features.auth.data

class DummyAuthRepo : AuthRepository {

    private val users = mutableMapOf<String, String>() // map with users -> (username, password)

    override suspend fun login(username: String, password: String): Result<Unit> =
        if (users[username] == password) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("Invalid credentials"))
        }

    override suspend fun register(username: String, password: String): Result<Unit> =
        if (users.containsKey(username)) Result.failure(Exception("User already exists"))
        else {
            users[username] = password
            Result.success(Unit)
        }
}