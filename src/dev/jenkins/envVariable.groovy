package dev.jenkins

import groovy.json.JsonSlurper
import hudson.scm.SCM
import jenkins.model.Jenkins
import jenkins.plugins.git.GitSCMSource
import org.jenkinsci.plugins.workflow.libs.*
import org.jenkinsci.plugins.workflow.libs.LibraryConfiguration
import org.jenkinsci.plugins.workflow.libs.SCMSourceRetriever

class envVariable implements Serializable {
    public String branch
    
    private static printSHELL(script,String Message,boolean WinSystem) {

        if ( WinSystem ) {
            //Windows
            script.bat("echo " + Message)
        }
        else {
            script.sh("echo " + Message)
        }

    }
    private static execSHELL(script,String cmd,boolean WinSystem) {
        if ( WinSystem ) {
            //Windows
            script.bat(cmd)
        }
        else {
            script.sh(cmd)
        }
    }
    public printInformation(script) {
        printSHELL(script,"BRANCH " + this.branch,false)
        


    }
    
    public envVariable(script,String EnvJson, String VarsJson, String Branch) {
        branch = Branch
        def EnvJason = new JsonSlurper().parseText(EnvJson)
        def VarsJason = new JsonSlurper().parseText(VarsJson)

       


        EnvJason.each{ branch,data ->

            if ( "${branch}" == "$Branch" ) {
            
                //this.name = data.name
                
              
            }
        }
       



       
    }
}
