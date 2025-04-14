// Create multibranch pipeline job from GitHub
import org.jenkinsci.plugins.github_branch_source.*
import jenkins.branch.*

def pipelineName = env.PIPELINE_NAME ?: "github-pipeline"
def githubUser = env.GITHUB_USER
def githubRepo = env.GITHUB_REPO
def githubCredId = env.GITHUB_CREDENTIAL_ID
def jenkinsfilePath = env.JENKINSFILE_PATH ?: "Jenkinsfile"

if (!jenkins.getItem(pipelineName)) {
    println "Creating pipeline job: ${pipelineName}"
    def job = jenkins.createProject(org.jenkinsci.plugins.workflow.multibranch.WorkflowMultiBranchProject, pipelineName)

    def githubSource = new GitHubSCMSource(null, githubUser, githubRepo, githubCredId)
    githubSource.setBuildForkPRHead(true)
    githubSource.setBuildForkPRMerge(true)
    githubSource.setBuildOriginBranch(true)
    githubSource.setBuildOriginPRHead(true)
    githubSource.setBuildOriginPRMerge(true)
    githubSource.setScriptPath(jenkinsfilePath)

    def branchSource = new BranchSource(githubSource)
    job.getSourcesList().add(branchSource)
    job.save()
}

