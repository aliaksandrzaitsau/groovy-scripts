// edit nexus user
def user = security.securitySystem.getUser('nexus')
user.setEmailAddress('nexus@nexus.com')
security.securitySystem.updateUser(user)
security.securitySystem.changePassword('nexus','nexus')
// create new user
security.addUser("burb","test", "user","test@test.com", true,"test", [ 'nx-admin' ])
// create newrepo
repository.createMavenHosted("repo_for_script", "blob_for_script")
