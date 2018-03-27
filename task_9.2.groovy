import jenkins.model.Jenkins;
import hudson.model.FreeStyleProject;
import hudson.plugins.git.GitSCM;
import hudson.tasks.Shell;

job = Jenkins.instance.createProject(FreeStyleProject, 'JobProject_1')

def url = "https://github.com/MNT-Lab/build-principals.git/"
def gitScm = new GitSCM(url)
gitScm.branches = [new hudson.plugins.git.BranchSpec("*/ashumilov")]
job.scm = gitScm

job.buildersList.add(new Shell('Hello world!'))
job.save()
