def path = "192.168.1.11:8081"
def group = "groups"
def artifact = "ARTIFACT"
def version = "102"
def repo = "Nexus-Release"
def com = "push" 
def log_pass = "nexus:nexus"


if("$com"=="push"){
    def File = new File ("/tmp/${artifact}-${version}.tar.gz").getBytes()
    def con = new URL("http://${path}/repository/${repo}/${group}/${artifact}/${version}/${artifact}-${version}.tar.gz").openConnection()
    con.setRequestMethod("PUT")
    con.doOutput = true
    con.setRequestProperty( "Content-Type", "application/x-gzip" )
    def writer = new DataOutputStream(con.outputStream)
    writer.write(File)
    writer.close()
    println con.responseCode
}

else {
    new File("/tmp/${ART_ID}-${version}.tar.gz").withOutputStream { out ->
        def url = new URL("http://${path}/repository/${repo}/${group}/${artifact}/${VER}/${artifact}-${version}.tar.gz").openConnection()
        out << url.inputStream
    }
}
