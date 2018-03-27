import jenkins.model.Jenkins
import hudson.model.FreeStyleProject
import hudson.tasks.Shell
import hudson.plugins.git.GitSCM
import hudson.plugins.git.BranchSpec
import hudson.plugins.ws_cleanup.PreBuildCleanup

GITHUB_REPOSITORY = "https://github.com/MNT-Lab/build-principals"
JOB_NAME = "MNT-CD-module9-extcreated-job"
BRANCH_NAME = "*/kklimov"

job = Jenkins.instance.createProject(FreeStyleProject, JOB_NAME)
job.setDescription("groovy script which creates job in Jenkins")
job.scm = new GitSCM(GITHUB_REPOSITORY)
job.scm.branches = [new BranchSpec(BRANCH_NAME)]
job.buildWrappersList.add(new PreBuildCleanup(null, true, "", ""))

def gradle = new hudson.plugins.gradle.Gradle()
gradle.setTasks("clean build -PbuildNumber=\$BUILD_NUMBER")
gradle.setBuildFile("home-task/build.gradle")
gradle.setGradleName("gradle4.6")
job.buildersList.add(gradle)
job.buildersList.add(new Shell("echo hello MNT-Lab"))

job.save()

build = job.scheduleBuild2(1, new hudson.model.Cause.UserIdCause())
build.get()
