package hudson.plugins.ws_cleanup;

import hudson.model.FreeStyleProject;
import jenkins.model.Jenkins
import hudson.model.FreeStyleProject
import hudson.tasks.Shell
import hudson.plugins.git.*
import hudson.plugins.ws_cleanup.*

job = Jenkins.instance.createProject(FreeStyleProject, 'task 1-2')
job.scm = new hudson.plugins.git.GitSCM("https://github.com/MNT-Lab/build-principals")
job.scm.branches[0].name = '*/vvolchkov'
job.buildersList.add(new Shell('echo hello world'))

PreBuildCleanup cleanup = new PreBuildCleanup(new ArrayList<Pattern>(), false, null, null);
job.getBuildWrappersList().add(cleanup);

job.save()
