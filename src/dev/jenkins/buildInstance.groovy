package dev.jenkins
import groovy.json.JsonSlurper
import hudson.scm.SCM
import jenkins.model.Jenkins
import jenkins.plugins.git.GitSCMSource
import org.jenkinsci.plugins.workflow.libs.*
import org.jenkinsci.plugins.workflow.libs.LibraryConfiguration
import org.jenkinsci.plugins.workflow.libs.SCMSourceRetriever


class buildInstance implements Serializable{
    public String branch
    
    
    


     buildInstance(String team,String Branch) {
        def EnvJason = new JsonSlurper().parseText(LoadEnvRes)    
        someglobal = EnvJason.Branch.keyname
        



    }
    public void init() {
        //println("Initiated Class")
    }
}







 
