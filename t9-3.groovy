//creating user
security.addUser("new_user",
                 "first_name",
                 "last_name",
                 "user@email.com",
                 true,"password",
                 ["nx-anonymous"])
//creating repository 
repository.createMavenHosted("new_repository", "default", false)
//changing password of new_user                  
def new_user = security.securitySystem.getUser("new_user")
security.securitySystem.changePassword("new_user","new_password")

