import jenkins.model.Jenkins;
import hudson.model.FreeStyleProject;
import hudson.plugins.git.GitSCM;
import hudson.plugins.ws_cleanup.*
import hudson.tasks.Shell;
//creating new job
def job_name = "new-job"  
job = Jenkins.instance.createProject(FreeStyleProject, (job_name))
//git
def url = "https://github.com/MNT-Lab/build-principals.git/"
def gitScm = new GitSCM(url)
gitScm.branches = [new hudson.plugins.git.BranchSpec("*/nbuzin")]
job.scm = gitScm
//delete workspace before build starts
job.buildWrappersList.add(new PreBuildCleanup(null, true, "", ""))
//execute shell
job.buildersList.add(new Shell("echo hello world"))

job.save()
