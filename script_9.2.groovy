import jenkins.model.Jenkins;
import hudson.model.FreeStyleProject;
import hudson.plugins.git.GitSCM;
import hudson.tasks.Shell;
import hudson.plugins.ws_cleanup.*

//Get instance of Jenkins
def parent = Jenkins.getInstance()

//Define a job name
//def jobName = "Job"

//Instantiate a new project
//def project = new FreeStyleProject(parent, jobName);

//Define a branch name
def branchName = "*/azaitsau"

//Create a parameter for the project
job = Jenkins.instance.createProject(FreeStyleProject, 'jobName')
def url = "https://github.com/MNT-Lab/groovy-scripts.git"
def gitScm = new GitSCM(url)
gitScm.branches = [new hudson.plugins.git.BranchSpec(branchName)]
job.scm = gitScm

job.buildWrappersList.add(new PreBuildCleanup(null, true, "", ""))
job.buildersList.add(new Shell('echo hello'))

parent.reload()
