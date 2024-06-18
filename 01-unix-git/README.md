# Unix basics

## [Z Shell](https://zsh.sourceforge.io/)

"Zsh is a shell designed for interactive use, although it is also a powerful scripting language. Many of the useful features of bash, ksh, and tcsh were incorporated into zsh; many original features were added"

### Follow this steps to install and configure zsh

1. Run `sudo apt update`
2. Run `sudo apt upgrade`
3. Run `sudo apt install wget ca-certificates`
4. Run `sudo apt install zsh`

Lets bring this to a new level with [oh-my-zshell](https://ohmyz.sh/).

"Oh My Zsh is a delightful, open source, community-driven framework for managing your Zsh configuration."

5. Run `sh -c "$(wget https://raw.github.com/robbyrussell/oh-my-zsh/master/tools/install.sh -O -)"`

Now lets install some usefull plugins like:

* [zsh-completions](https://github.com/zsh-users/zsh-completions)
* [zsh-highlighting](https://github.com/zsh-users/zsh-syntax-highlighting)
* [zsh-autosuggestions](https://github.com/zsh-users/zsh-autosuggestions)

1. Run `git clone https://github.com/zsh-users/zsh-completions ${ZSH_CUSTOM:-${ZSH:-~/.oh-my-zsh}/custom}/plugins/zsh-completions`
2. Run `git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting`
3. Run `git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-autosuggestions`
4. Open ".zshrc", file is located in your home directory you can use vi, `vi ~/.zshrc"
5. Add it to FPATH in your .zshrc by adding the following line before source "$ZSH/oh-my-zsh.sh": `fpath+=${ZSH_CUSTOM:-${ZSH:-~/.oh-my-zsh}/custom}/plugins/zsh-completions/src`
6. Activate the plugin by adding the following `plugins=( [plugins...] git zsh-syntax-highlighting zsh-autosuggestions)` to the extant variable

You `~/.zshrc` should look like:

```
...
plugins=(
        git
        zsh-autosuggestions
        zsh-syntax-highlighting
)

fpath+=${ZSH_CUSTOM:-${ZSH:-~/.oh-my-zsh}/custom}/plugins/zsh-completions/src

...
````


Open a new terminal window or run `source ~/.oh-my-zsh/oh-my-zsh.sh` (or `source $ZSH/oh-my-zsh.sh`) to activate the new configurations

Reopen Ubuntu or run `source ~/.zshrc`to activate all plugins.