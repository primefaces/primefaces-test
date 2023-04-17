# Quickstart guide for running in Visual Studio Code

## Prerequisites

1. JDK installed and `JAVA_HOME` env variable set, see [here](https://github.com/redhat-developer/vscode-java/wiki/JDK-Requirements)
2. Maven installed, see [here](https://maven.apache.org/install.html)
3. Visual Studio Code installed, either via installer or portable, see [here](https://code.visualstudio.com/docs/setup/setup-overview)

## Setup

1. Clone or download [primefaces-test](https://github.com/primefaces/primefaces-test)
2. Start Visual Studio Code and File > Open Folder... > `primefaces-test`
3. Click Extensions (`Ctrl+Shift+X`) and search "@recommended"
4. Click Install Workspace Recommended Extensions
5. Reload Visual Studio Code as needed

## Running `primefaces-test` project

### Method #1
1. Click Explorer (`Ctrl+Shift+E`)
2. Expand Maven > primefaces-test > Plugins > jetty
3. Click Run on the "run" goal

### Method #2
1. Open Command Pallette (`Ctrl+Shift+P`)
2. Run "Tasks: Run Build Task" (`Ctrl+Shift+B` is a shortcut for this)

## Debugging

1. Set `MAVEN_OPTS` env variable to "-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000 -Xnoagent -Djava.compiler=NONE" (this allows the debugger to attach to running server)
2. Run project (see above)
3. Click Run and Debug (`Ctrl+Shift+D`)
4. Click Start Debugging (`F5`)
