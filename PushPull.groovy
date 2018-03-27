/*
@Grab(group='org.codehaus.gpars', module='gpars', version='1.2.1')
@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7.1' )
import groovy.grape.*
import groovyx.net.http.*
import static groovyx.net.http.ContentType.*
import static groovyx.net.http.Method.*
import java.util.regex.*
import hudson.model.*
*/

// !!!!! Please, never send your passwords over Internet as a plain text !!!!!
def GROUP_NAME = 'mbean-groovy'
def USER_CREDS = 'ako:ako'.getBytes().encodeBase64().toString()

//used my own repository, user and artifact instead of project-releases, nexus-service-user and ARTIFACT_SUFFIX-BUILD_NUMBER
def NEXUS_URL = 'http://192.168.56.150:8081'
def REPO_NAME = 'AKO-maven2-hosted-repo'
def GROUP = 'mbean-groovy'
def ARTEFACT = 'AKO-artifact'
def TYPE = 'tar.gz'

def PARAM1 = args[0]
def PARAM2 = args[1]

if("$PARAM1"=="PUSH"){
  def File = new File ("${PARAM2}").getBytes()
  def version1 = PARAM2.substring(PARAM2.lastIndexOf("-")+1, PARAM2.indexOf("."))
  def opened1 = new URL("${NEXUS_URL}/repository/${REPO_NAME}/${GROUP}/${ARTEFACT}/${version1}/${ARTEFACT}-${version1}.${TYPE}").openConnection()
  opened1.setRequestProperty("Authorization" , "Basic ${USER_CREDS}")
  opened1.setRequestMethod("PUT")
  opened1.doOutput = true
  opened1.setRequestProperty( "Content-Type", "application/x-gzip" )
  def added = new DataOutputStream(opened1.outputStream)
  added.write(File)
  added.flush()
  added.close()
  println opened1.responseCode
}

else if("$PARAM1"=="PULL"){
  new File("${PARAM2}").withOutputStream { out ->
    def authorization = "Basic " + "${USER_CREDS}"
    def version2 = PARAM2.substring(PARAM2.lastIndexOf("-")+1, PARAM2.indexOf("."))
    def opened2 = new URL("${NEXUS_URL}/repository/${REPO_NAME}/${GROUP}/${ARTEFACT}/${version2}/${ARTEFACT}-${version2}.${TYPE}").openConnection()
    opened2.setRequestProperty("Authorization", authorization);
    out << opened2.inputStream
    }
}

else {
  println 'Exception: This type of "${PARAM1}" is not recognized. Please, use PUSH or PULL for the first parameter.'
}

