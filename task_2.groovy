import hudson.plugins.git.GitSCM;
import hudson.plugins.ws_cleanup.*
import jenkins.model.Jenkins;
import hudson.model.FreeStyleProject;
import hudson.tasks.Shell;

def url = "https://github.com/MNT-Lab/groovy-scripts.git"
def BRANCH_NAME = "*/ifilimonau"

job = Jenkins.instance.createProject(FreeStyleProject, 'Task2_JOB')

job.buildersList.add(new Shell('echo hello world'))

def gitScm = new GitSCM(url)
gitScm.branches = [new hudson.plugins.git.BranchSpec(BRANCH_NAME)]
job.scm = gitScm

job.buildWrappersList.add(new PreBuildCleanup(null, true, "", ""))

job.save()
