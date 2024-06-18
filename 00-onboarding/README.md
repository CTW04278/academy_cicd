# Tooling
## Warning  
Probably you already have your favorite IDE, terminal, and other tools but to ensure that everyone speaks the same language we strongly recommend that you follow this manual. If you decide to follow another path be aware that you should be able to follow the rest of the course at the same pace as your colleagues.  

### BMW Access  
First thing you need to validate that your credentials are working so please follow this **[Access Procedures](https://criticaltechworks.sharepoint.com/sites/ITDESKTrainingManuals/Shared%20Documents/Forms/AllItems.aspx?id=%2Fsites%2FITDESKTrainingManuals%2FShared%20Documents%2FManuals%2FBMW%2FCTW%2D2020%2DBMW%2DAccess%2Dprocedures%2Epdf&parent=%2Fsites%2FITDESKTrainingManuals%2FShared%20Documents%2FManuals%2FBMW&p=true&ga=1)** and you need access to our code repository so try to login here [Bitbucket](https://atc.bmwgroup.net/bitbucket/).

### Windows Update

Please ensure that your operating system is up to date.

### Terminal  

Every DevOps engineer needs to use a terminal not to catch a bus but to run command line tools. Windows has a useful application called "Terminal" 🙄 according to wikipedia "multi-tabbed terminal emulator".

It should be installed by default on your OS (if you have Windows 11), just search for the terminal, if you can't find it use the windows store to install it, just **[follow me](https://apps.microsoft.com/store/detail/windows-terminal/9N0DX20HK701?hl=pt-pt&gl=pt)**.
  
### WSL (Windows sub-system for Linux)  
There is a high probability that you'll need to use unix-based tools since we are in a world dominated by Linux containers. So we want to give you that experience from day 0. You are lucky to start working in 2023 because it's simpler than ever to use Linux inside Windows:

1. open the **terminal** with a **PowerShell** tab (run as admin)
2. run the following command: `wsl --install`
3. restart your PC
4. reopen **terminal** with a **PowerShell** tab (run as admin)
5. run the following command: `wsl --install -d Ubuntu` and follow the instructions
6. When asked, setup your Ubuntu username and password (remember them, you'll need them later)
7. open a new tab, if everything went well you now have an "Ubuntu" option
  
Yes that's it, you know have access to a Linux system inside your Windows system 🤯.

Reference:

1. [Windows documentation](https://learn.microsoft.com/en-gb/windows/wsl/install).
3. If you are not able to install Ubuntu directly, install it from Microsoft Store.
2. If you face some issues:
    a. Error "0x8007019e": [troubleshooting guide](https://thegeekpage.com/wslregisterdistribution-failed-with-error-0x8007019e/).


## Docker

### **Install Docker**
If you already know what Docker, great if not, don't worry. For now we just need to install it, you'll learn more about this in the following weeks. Just go **[here](https://www.docker.com/products/docker-desktop/)** download the installer and follow the instructions. In the end open the "Docker Desktop" application, go to the terminal application open a Power Shell tab and run the following command to validate that everything is working `docker --version`.

### **Additional steps for domain-joined Windows PC**

If you get an error `Docker Desktop - Access denied. You are not allowed to use Docker. You must be in the "docker-users" group`, you need to follow the following steps:
1. Open the Terminal App on your PC (Run as Administrator)
1. On a Powershell console, run (Replace XXXXX with your CTW number): `net localgroup docker-users CRITICALTW\ctwXXXXX /ADD`
1. Restart your PC
1. Launch the Docker Desktop application
1. On the bottom left of the opened window, after some seconds, it should indicate that the engine is running

### **Login with your Docker account**
1. Open the Docker Desktop application on your PC
1. Click on “Sign in” on the top right
1. Login on docker hub, on the browser using your ctwxxxxx@criticaltechworks.com e-mail
1. After prompted from the browser, click to Open Docker Desktop again
1. Now your username shall appear as logged in on the Docker Desktop application (top right)
 
### **Link Docker Desktop (Windows) with WSL**
Now you need to enable docker to work with your WSL. 

1. Open Docker Desktop
1. Click on Settings
1. Choose Resources -> WSL Integration
1. Choose "Enable Integration with my default WSL distro"
1. If necessary choose "Ubuntu" from the options below

If you follow the previous step order you are done! Open an "Ubuntu" tab on the terminal app and run `docker ps` to check if it's working, if not please follow this **[page](https://docs.docker.com/desktop/windows/wsl/)**.

### **Enable Kubernetes inside Docker Desktop**
Now lets enable Kubernetes support in Docker Desktop.

1. From the Docker Dashboard, select the Settings. 
2. Select Kubernetes from the left sidebar. 
3. Next to Enable Kubernetes, select the checkbox. 
4. Select Apply & Restart to save the settings and then click Install to confirm. This instantiates images required to run the Kubernetes server as containers, and installs the **kubectl** command on your machine.
5. Run in **PowerShell** `kubectl get nodes` in order to see the created node.
You should see something similar to this:
```
   $> kubectl get nodes
   NAME             STATUS   ROLES           AGE   VERSION
   docker-desktop   Ready    control-plane   57m   v1.25.9
```

Reference:

1. If Docker fails to start Kubernetes, you will need to reset the entire docker space (you have that option on the debug window).
2. Unexpected WSL Error on Docker Desktop startup: https://github.com/microsoft/WSL/releases/download/2.1.5/wsl.2.1.5.0.x64.msi (https://github.com/docker/for-win/issues/13845)
3. Ubuntu fix Patch: https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi

### Git and Bitbucket access configuration

"Git is a free and open source distributed version control system designed to handle everything from small to very large projects with speed and efficiency. "
**[GIT](https://git-scm.com/)**  

#### Install GIT on Ubuntu  
1. Open a terminal with an Ubuntu tab
2. Run `sudo apt-get update`
3. Run `sudo apt-get install git-all`
4. Run `git version` to validate the installation

#### Configure GIT
1. Run `git config --global user.email "youremail@ctw.bmwgroup.com"`
2. Run `git config --global user.name "your name"`
3. Run `git config --global core.autocrlf false`
4. Run `git config --global core.eol lf`

### **Configure SSH keys for Bitbucket access**

Bitbucket is a git repository that we'll use for this academy.
In order to access Bitbucket you need SSH key pair and configure your profile.
1. Open a terminal with an Ubuntu tab
2. Run `ssh-keygen -t rsa -C "your_bmw_email@ctw.bmwgroup.com"` and follow the instructions
3. to check if everything went well run `ls ~/.ssh` you should see at least 2 files `id_rsa` and `id_rsa.pub`
4. Go to **[Azure Git account management](https://dev.azure.com/CriticalTechworks/_usersSettings/keys)**
5. On the left menu choose "SSH Public Keys" and press "New key"
6. Copy the content from `~/.ssh/id_rsa.pub` and paste it in the text area of Public Key Data, now press "Add" (Hint: you can do `cat ~/.ssh/id_rsa.pub | clip.exe` in the Terminal to copy the content of the file directly to your clipboard. Then you just need to paste in the Bitbucket website)
7. Let's test, choose a folder to "clone" our academy content to your local PC and run `git clone git@ssh.dev.azure.com:v3/CriticalTechworks/Academy-Java-Fullstack/support-material`

We are getting closer 🙇

### **Copy SSH keys to Windows**
In some instances you may want to perform GIT operations from Windows instead of WSL. Since we already created some SSH Keys in Ubuntu, we can sync them to Windows so that we also have authorization to perform GIT operations from it.
1. In a terminal window with Ubuntu run `cp -r ~/.ssh /mnt/c/Users/ctwXXXXX/` (replace with your CTW user). This will copy the .ssh directory to windows (you can check by navigating to: `C:\Users\ctwXXXXX\.ssh`)

### Java
To implement our backends the most commonly used language across CTW is Java ("is a programming language and computing platform first released by Sun Microsystems in 1995"). In our scenario we need to install this twice, one because of the IDE that will run on our Windows SO other in our Ubuntu SO.

There is a high number of different implementations of the current java specification, in our case don't really matter which one we'll use. We'll use the latest LTS (long-term support) version 17 and an **[Azul](https://www.azul.com/)** implementation.     

**Install Java on Windows**

1. Open a terminal with a PowerShell or cmd tab
2. Run `winget install -e --id Azul.Zulu.17.JDK`
    3. If winget fails, download and install JDK directly from Azul webpage.
3. Run `java --version` to check if everything is installed

**Install Java on Ubuntu (WSL)**

1. Open a terminal with a Ubuntu tab
2. Run the following commands:
    1. `sudo apt install gnupg ca-certificates curl`
    2. `curl -s https://repos.azul.com/azul-repo.key | sudo gpg --dearmor -o /usr/share/keyrings/azul.gpg`
    3. `echo "deb [signed-by=/usr/share/keyrings/azul.gpg] https://repos.azul.com/zulu/deb stable main" | sudo tee /etc/apt/sources.list.d/zulu.list`
    4. `sudo apt update`
    5. `sudo apt install zulu17-jdk`
3. Run `java --version` to check if everything is installed

### Maven

"Apache Maven is a software project management and comprehension tool". We'll use Maven to manage our Java projects dependencies and plugins.
1. Open a terminal with an Ubuntu tab
2. Run `sudo apt-get update`
3. Run `sudo apt-get install maven`
4. Run `mvn --version` to test your installation

### Node.JS
Most of our frontend aplications are written in JavaScript/TypeScript, so we need a way to run our applications "Node.JS is an open-source, cross-platform JavaScript runtime environment" 

1. Open a terminal with an Ubuntu tab
2. Run the following commands:
    1. Install curl: `sudo apt-get install curl`
    2. Install Node Version Manager: `curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/master/install.sh | bash`
    3. run `source ~/.bashrc`
    4. Install node LTS version: `nvm install --lts`
3. Run `node -v` to check your instalation

### NPM
Like in our java projects in our frontend application we don't do everything from scratch. NPM is a registry and package dependency manager for Node.JS applications.

With the previous `nvm install --lts` command, `npm` was also installed. Check it by running:
1. `npm -v`

### Angular


**[Angular](https://angular.io/)** "The modern web developer's platform", like React, Vue etc. Angular is a framework that accelerates our development, as we speak is the most used framework used by CTW and BMW.
1. Open a terminal with an Ubuntu tab
2. Run `npm install -g @angular/cli`
3. Create your first (or not) Angular application by running `ng new my-first-app`
4. Run `cd my-first-app`
5. Run `ng serve --open`
Congratulations you just created your first Angular application.

### Ruby

For the GIT module we'll play the GIT Hug game, for that we need to install Ruby:

1. Open a terminal with an Ubuntu tab
2. RUn `sudo apt install ruby-full`
3. Check the installation `ruby --version``


### VS Code

You have a ton of IDE choices like, IntelliJ (Jetbrains), Eclipse etc. We advise you to use Visual Studio Code during our academy because is a great tool and it's free.

1. Open the following **[installer](https://aka.ms/vscode-java-installer-win)** after the download just run it and follow the instructions, after the installation open the "extensions" menu to add extra plugins:
2. install "WSL" plugin
3. install "Remote - SSH" plugin (install all provided by Microsoft)
4. Open a folder inside WSL and install the following: 
5. install "Sonar Lint" plugin
6. install "Quarkus" plugin
7. install "Docker" plugin
8. install "Angular Language Service" plugin
9. install "Coverage Gutters" plugin
