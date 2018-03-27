import jenkins.model.Jenkins;
import hudson.model.FreeStyleProject;
import hudson.plugins.git.GitSCM;
import hudson.plugins.ws_cleanup.*
import hudson.tasks.Shell;
import groovy.json.JsonSlurper

///////////////////////////////////////////////////////////

repository = 'test'
groupId = 'REL'
http_head = 'http://'
ip_nexus = '192.168.15.15'
url = '/service/rest/beta/search/assets?repository='
repo_name = 'test'
extension = '&maven.extension='
extension_type = 'war'
login = 'admin'
password = 'admin123'

///////////////////////////////////////////////////////////

def addr = http_head + ip_nexus + url + repo_name + extension + extension_type
def authString = "${login}:${password}"
def conn = addr.toURL().openConnection()
conn.setRequestProperty( "Authorization", "Basic ${authString.getBytes().encodeBase64().toString()}" )

def artf = []

def json = new JsonSlurper().parseText(conn.content.text)
json.items.each{
  artf.add("${it.downloadUrl.split('/').last()}")
}

///////////////////////////////////////////////////////////

NAME_JOB='NT-CD-module9-' + artf.last() + '-job'
BRANCH = '*/alahutsin'
URL_GIT = 'https://github.com/MNT-Lab/groovy-scripts.git'

///////////////////////////////////////////////////////////

def BRANCH_NAME = BRANCH
NEW_JOB = Jenkins.instance.createProject(FreeStyleProject, NAME_JOB)

def gitScm = new GitSCM(URL_GIT)
gitScm.branches = [new hudson.plugins.git.BranchSpec(BRANCH_NAME)]
NEW_JOB.scm = gitScm

NEW_JOB.buildWrappersList.add(new PreBuildCleanup(null, true, "", ""))
NEW_JOB.buildersList.add(new Shell('echo name_job: ' + NAME_JOB + ', from_branch:' + BRANCH + ', GIT_URL:' + URL_GIT))

///////////////////////////////////////////////////////////

NEW_JOB.buildersList.add(new Shell('
curl 'http://192.168.95.90:8080/manager/text/undeploy?path=/webapp' --user admin:admin
curl --upload-file target/hello-world-1.0-SNAPSHOT.war http://192.168.95.90:8080/manager/text/deploy?path=/webapp --user admin:admin
cd target/ 
rm -rf *.war

export http_code=$(curl -s -o /dev/null -w ''%{http_code}'' http://192.168.95.90:8080/webap/)
export old_build=$((${BUILD_NUMBER} - 1))

curl -O -u admin:admin123 http://192.168.15.15:8081/repository/test/repository/PROD/REL/FINAL/$old_build/FINAL-$old_build-APP.war

if [ $http_code != 200 ]
then
 echo "HTTP CODE: "$http_code
 sleep 5
 echo "TRY DEPLOY OLD BUILD"
 curl 'http://192.168.95.90:8080/manager/text/undeploy?path=/webapp' --user admin:admin
 curl --upload-file FINAL-$old_build-APP.war http://192.168.95.90:8080/manager/text/deploy?path=/webapp --user admin:admin
 sleep 5
	export new_count=$((${BUILD_NUMBER} - 2))
  while [ $(curl -s -o /dev/null -w ''%{http_code}'' http://192.168.95.90:8080/webapp/) != 200 ]
  do
  if [ $new_count > 0 ] 
  then
   curl -O -u admin:admin123 http://192.168.15.15:8081/repository/test/repository/PROD/REL/FINAL/$old_build/FINAL-$new_count-APP.war         
   curl 'http://192.168.95.90:8080/manager/text/undeploy?path=/webapp' --user admin:admin
   curl --upload-file FINAL-$new_count-APP.war http://192.168.95.90:8080/manager/text/deploy?path=/webapp --user admin:admin
   export new_count=$[$new_count-1]
  else
   curl FOR FAILED JOB
  fi
  done
 echo "DEPLOY OLD BUILD: OK!"
else
 echo "DEPLOY: OK!"
fi
'))
            
///////////////////////////////////////////////////////////
            
NEW_JOB.buildersList.add(new Shell('tar cvzf target/ht-' + artf.last() + '.tar.gz -C target/ *.war'))
            
///////////////////////////////////////////////////////////
            

