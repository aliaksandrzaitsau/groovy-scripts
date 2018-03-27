// Create repo
{
  "name": "100500",
  "content": "repository.createMavenHosted('100500')",
  "type": "groovy"
}
// Create user
{
  "name": "User",
  "content": "security.addUser('USer','blablabla','User','user@user.com',true,'paswd',['nx-admin'])",
  "type": "groovy"
}
// Change passwd
{
  "name": "User",
  "content": "def user = security.securitySystem.getUser('test');user.setEmailAddress('admin@admin.com');
	      security.securitySystem.updateUser(user);
	      security.securitySystem.changePassword('User','asfsdggssg');",
  "type": "groovy"
}
