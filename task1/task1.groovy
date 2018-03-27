import groovy.json.JsonSlurper
import hudson.model.*
import jenkins.model.Jenkins

def getPassword = { username ->
    def creds = com.cloudbees.plugins.credentials.CredentialsProvider.lookupCredentials(
            com.cloudbees.plugins.credentials.common.StandardUsernamePasswordCredentials.class,
            jenkins.model.Jenkins.instance
    )

    def c = creds.findResult { it.username == username ? it : null }

    if ( c ) {
        return c.password
    } else {
        println "could not find credential for ${username}"
    }
}

passwd = getPassword("admin")
cred = "admin:${passwd}".getBytes().encodeBase64().toString()


list = []
url = "http://192.168.56.30:8081/service/rest/beta/search/assets?repository=artifacts&maven.extension=tar.gz".toURL()
connect = url.openConnection()
connect.setRequestProperty("Authorization", "Basic ${cred}")
def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText(connect.content.text)
l = object.items.downloadUrl
l.each{
    list << it.split('/').last().split('\\.')[0].split('-').last()
}

artefact_id = object.items.downloadUrl[0].split('/')[6]
group_id = object.items.downloadUrl[0].split('/')[5]

art_list = []
list_to_push = "ls -t1 /home/student/IdeaProjects/calc1/".execute().text.split()
list_to_push.each {
    art_list << it.split("\\.")[0].split("-").last()
}


if (expression[0] == "pull"){
    println list
    version = []
    Scanner scanner1 = new Scanner(System.in)
    System.out.print("Enter version number from the list: ")
    version << scanner1.nextLine()
    def filename = "${artefact_id}-${version[0]}.tar.gz"
    pull_url = "http://192.168.56.30:8081/repository/artifacts/${group_id}/${artefact_id}/${version[0]}/${filename}".toURL().openConnection()
    pull_url.setRequestProperty("Authorization", "Basic ${cred.getBytes().encodeBase64().toString()}")
    def file = new File(filename)
    file << pull_url.inputStream

}
else if (expression[0] == "push") {
    println art_list
    version1 = []
    Scanner scanner1 = new Scanner(System.in)
    System.out.print("Enter version number from the list: ")
    version1 << scanner1.nextLine()
    def filename = "${artefact_id}-${version1[0]}.tar.gz"
    def upload_file = new File ("/home/student/IdeaProjects/calc1/${filename}").getBytes()
    push_url = new URL("http://192.168.56.30:8081/repository/artifacts/${group_id}/${artefact_id}/${version1[0]}/${filename}").openConnection() as HttpURLConnection
    push_url.setRequestMethod("PUT")
    push_url.doOutput = true
    push_url.setRequestProperty("Authorization", "Basic ${cred.getBytes().encodeBase64().toString()}")
    def write = new DataOutputStream(push_url.outputStream)
    write.write (upload_file)
    write.close()
    println push_url.responseCode
}
else {
    println "enter correct action"
}


