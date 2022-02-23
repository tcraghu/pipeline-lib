package dev.jenkins

import org.apache.commons.lang.SystemUtils

class InitValue {

public static String getStandardCMD(boolean WinSystem) {
        if ( WinSystem ) {
            return standBuild
        }
        else {
            return standBuild
        }
    }
    
public static String 
  
    public static String standBuild = '''
rm -rf ~/.m2/repository/com/ge
chmod +x build.sh
export PATH=/usr/lib/gradle/2.12/bin:${PATH}
./build.sh ${BUILD_NUMBER}
             '''

}
