package dev.jenkins
import org.apache.commons.lang.SystemUtils
//import org.junit.runners.model.InitializationError
import groovy.transform.Field

class BuildUtility implements Serializable  {
    def steps

    BuildUtility(steps) {
        this.steps = steps
    }
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


    public static buildCode(script,args,pipelineparams,BuildInfo,boolean WinSystem,String buildType) {

        String repo = pipelineparams.scmUrl
        def gitRepoName = repo.substring(repo.lastIndexOf('/')+1,repo.lastIndexOf('.'))
        def gitArray = repo.split('/')
        def gitORG = gitArray[-2]
   
        if ( buildType == 'CODE'  ) {


            printSHELL(script,"Running CODE",WinSystem)
            String StdCmd = InitValue.getStandardCMD(script,WinSystem)

            execSHELL(script,StdCmd,WinSystem)
        }


    }

    

    
   


}
