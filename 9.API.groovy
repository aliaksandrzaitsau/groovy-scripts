import jenkins.model.*
import hudson.security.*
import org.sonatype.nexus.blobstore.api.BlobStoreManager
import org.sonatype.nexus.repository.storage.WritePolicy
import org.sonatype.nexus.repository.maven.VersionPolicy
import org.sonatype.nexus.repository.maven.LayoutPolicy
import org.sonatype.nexus.repository.Repository
import groovy.json.*
import org.sonatype.nexus.repository.config.Configuration
//
//
def user = security.securitySystem.getUser('admin')
user.setEmailAddress('admin@admin.com')
security.securitySystem.updateUser(user)
security.securitySystem.changePassword('admin','admin123')
//
//
def instance = Jenkins.getInstance()
//
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount("User","pswd")
instance.setSecurityRealm(hudsonRealm)
instance.save()
//
jsonSlurper = new JsonSlurper()

//JSON used to recreate new config each time we need because Map used as Configuration constructor argument points to the same objects
defaultRepoconfig = JsonOutput.toJson([
repositoryName: repoName,
recipeName: "maven234-test",
online: true,
attributes: [
maven : [
versionPolicy: VersionPolicy.RELEASE,
layoutPolicy : LayoutPolicy.STRICT
],
proxy : [
//remoteUrl: url,
contentMaxAge: 1440.0,
metadataMaxAge: 1440.0
],
httpclient: [
blocked: false,
autoBlock: true,
//authentication: authentication,
connection: [
useTrustStore: false
]
],
storage: [
//blobStoreName: "Blob-Repo-Internal-Maven",
strictContentTypeValidation: true,
writePolicy: WritePolicy.ALLOW_ONCE
],
negativeCache: [
enabled: true,
timeToLive: 1440.0
]
]
])

def reposConfig = [
[
repositoryName : "Repo-Internal-Maven-InHouseCompos-Common-Releases"
]
];

def response = [];

reposConfig.each {
Repository repo = null;
try{

if(repository.getRepositoryManager().get(it.repositoryName)) {
repository.getRepositoryManager().delete(it.repositoryName)
}

def config = getConfiguration(it)
repo = repository.getRepositoryManager().create(config)
response << config

}catch(Exception e){
log.error("ALM Error : " + e.message)
}
}


return response;


Configuration getConfiguration(repo) {
config = jsonSlurper.parseText(defaultRepoconfig)
def test = (config as ConfigObject).merge( repo as ConfigObject )
def configuration = new Configuration(test)

if(repo.repositoryName =~ "(?i).*?Maven.*") {
configuration.recipeName = "maven2-"
} else if(repo.repositoryName =~ "(?i).*?NuGet.*") {
configuration.recipeName = "nuget-"
} else if(repo.repositoryName =~ "(?i).*?Npm.*") {
configuration.recipeName = "npm-"
}
if(repo.repositoryName =~ "(?i)Repo-External.*") {
configuration.recipeName += "proxy"
} else if(repo.repositoryName =~ "(?i)Repo-Internal.*") {
configuration.recipeName += "hosted"
} else if(repo.repositoryName =~ "(?i)Group-.*") {
configuration.recipeName += "group"
}

if(repo.repositoryName =~ "(?i).*?Maven.*?Snapshot.*") {
configuration.attributes.maven.versionPolicy = VersionPolicy.SNAPSHOT
}

def blobStoreName = repo.repositoryName.replaceAll(/([^-]+-[^-]+-[^-]+).*/) {fullMatch, extract ->
return "Blob-$extract"
}



configuration.attributes.storage.blobStoreName = blobStoreName

return configuration;

}
