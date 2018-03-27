def user = security.securitySystem.getUser('valera')
//set new email
user.setEmailAddress('example@gmail.com')
security.securitySystem.updateUser(valera)
//change the password of current user
security.securitySystem.changePassword('valera','32fsrf3fr43')
//the proccess of adding user
security.addUser("some_user","some_user", "some_user_surname","lab@lab.com", true,"test-attempt", [ 'nx-admin' ])
repository.createMavenHosted("some_new_repo")
