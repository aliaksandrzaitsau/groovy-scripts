//password for user                 
def passw = security.securitySystem.getUser('nexus')
passw.setEmailAddress('newmail@mail.com')
security.securitySystem.updateUser(passw)
security.securitySystem.changePassword('nexus','nexus')

//create user and repo
security.addUser("UserName","FirstName","LastName","email@mail.com",true,"Passw",["nx-admin"])
repository.createMavenHosted("new-repo", "default")
