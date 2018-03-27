//this command changes password for default admin user
security.securitySystem.changePassword('admin','adminadmin')
//json looks like
{
  "name": "ako-edituser",
  "content": "security.securitySystem.changePassword('admin','adminadmin')",
  "type": "groovy"
}
//command to upload
curl -u admin:admin123 -X POST --header 'Content-Type: application/json' \
 http://192.168.56.150:8081/service/rest/v1/script \
 -d @ako-edituser.json
//command to run
curl -u admin:admin123 -X POST --header 'Content-Type: text/plain' \
 http://192.168.56.150:8081/service/rest/v1/script/ako-edituser/run


//this command adds a new Nexus user to default nx-admin role
security.addUsersecurity.addUser('Uname','Ufirst','Ulast','uname@gmail.com',true,'pass',['nx-admin'])
//json looks like
{
  "name": "ako-newuser",
  "content": "security.addUser('Uname','Ufirst','Ulast','uname@gmail.com',true,'pass',['nx-admin'])",
  "type": "groovy"
}
//command to upload
curl -u admin:adminadmin -X POST --header 'Content-Type: application/json'; http://192.168.56.150:8081/service/rest/v1/script -d @ako-newuser.json
//command to run
curl -u admin:adminadmin -X POST --header 'Content-Type: text/plain' \
 http://192.168.56.150:8081/service/rest/v1/script/ako-newuser/run


//this command creates a new Repository named "NEWREPO" on Nexus server
repository.createMavenHosted("api-repo", "ako-blob")
//json looks like
{
  "name": "ako-newrepo",
  "content": "repository.createMavenHosted("api-repo", "ako-blob")",
  "type": "groovy"
}
//command to upload
curl -u admin:adminadmin -X POST --header 'Content-Type: application/json'; http://192.168.56.150:8081/service/rest/v1/script -d @ako-newrepo.json
//command to run
curl -u admin:adminadmin -X POST --header 'Content-Type: text/plain' \
 http://192.168.56.150:8081/service/rest/v1/script/ako-newrepo/run

