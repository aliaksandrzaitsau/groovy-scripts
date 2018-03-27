#!/tmp/Groovy/groovy-2.4.14/bin/groovy
import jenkins.model.Jenkins;
import hudson.model.FreeStyleProject;
import hudson.tasks.Shell;
import hudson.plugins.git.GitSCM;
import javaposse.jobdsl.plugin.*;
import hudson.plugins.ws_cleanup.*

def akogiturl = 'https://github.com/MNT-Lab/groovy-scripts.git'
def akojobname = 'MNT-CD-module9-extcreated-job'
def BRANCH_NAME = "*/master"

project = Jenkins.instance.createProject(FreeStyleProject, akojobname)
def gitScm = new GitSCM(akogiturl)
gitScm.branches = [new hudson.plugins.git.BranchSpec(BRANCH_NAME)]
project.scm = gitScm

//def hi = hudson.model.Hudson.instance
//hi.getItemByFullName(akojobname).doDoWipeOutWorkspace()

project.buildWrappersList.add(new PreBuildCleanup(null, true, "", ""))

project.buildersList.add(new Shell('echo This is a bash shell running from Groovy script!'))
//project.buildersList.add(new Shell('rm -rf /opt/jenkins/master/artefacts'))
