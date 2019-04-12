package com.maze

import org.scalatest.FunSuite

class UserRepositoryTests extends FunSuite{
  test("adding user should succeed") {
    val repo = UserRepository
    val user = repo.addDummy(1)
    assert(user.id == 1)
  }

  test("fetching user should succeed") {
    val repo = UserRepository
    repo.addDummy(1)
    val user = repo.get(1)
    assert(user.id == 1)
  }
}
