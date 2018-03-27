def ACT = "pullpush"

def MY_CREDS = "jenkins:jenkins"
def MY_REPO = "NEXUS-JENKINS"
def NEXUS_PATH = "nexus"
def GROUP_ID = "group_tomcat"
def ART_ID = "artifact"
//choose your version
def VER = 32

//choose acrion (pull/push)
if("$ACT"=="pull"){
    def File = new File ("/home/vagrant/${ART_ID}-${VER}-file.tar.gz").getBytes()
    def CONNECTION = new URL("http://${NEXUS_PATH}/repository/${MY_REPO}/${GROUP_ID}/${ART_ID}/${VER}/${ART_ID}-${VER}-file.tar.gz").openConnection()
    CONNECTION.setRequestProperty("Authorization" , "Basic ${MY_CREDS}".getBytes().encodeBase64().toString())
    CONNECTION.setRequestMethod("PUT")
    CONNECTION.doOutput = true
    CONNECTION.setRequestProperty( "Content-Type", "application/x-gzip" )
    def writer = new DataOutputStream(CONNECTION.outputStream)
    writer.write(File)
    writer.close()
    println CONNECTION.responseCode
}

else {
    new File("/home/vagrant/${ART_ID}-${VER}-file.tar.gz").withOutputStream { out ->
        def url = new URL("http://${NEXUS_PATH}/repository/${MY_REPO}/${GROUP_ID}/${ART_ID}/${VER}/${ART_ID}-${VER}-file.tar.gz").openConnection()
        def remoteAuth = "Basic " + "${MY_CREDS}".bytes.encodeBase64()
        url.setRequestProperty("Authorization", remoteAuth);
        out << url.inputStream
    }
}
