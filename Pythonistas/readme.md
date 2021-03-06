# Software Construction 

```
Sjoerd van den Heijden
Dylan Bartels - 10607072
```

# Introduction

need to do

# Requirements

- Python 3.6.4

# Setup MacOs

Requirements:
- pyenv (brew install pyenv)
- antlr4

pyenv:
navigate to folder

```
pyenv install 3.6.4
pyenv virtualenv 3.6.4 pythonistas3
pyenv local pythonistas3
pip install -r requirements.txt
python run.py
```

antlr:
```
cd /usr/local/lib
curl -O http://www.antlr.org/download/antlr-4.7.1-complete.jar
export CLASSPATH=".:/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH"
alias antlr4='java -Xmx500M -cp "/usr/local/lib/antlr-4.7.1-complete.jar:$CLASSPATH" org.antlr.v4.Tool'
alias grun='java org.antlr.v4.gui.TestRig'
```

# Running

```
usage: run_app.py [-h] [-l {debug,info,warn,error,critical}] [-v] [file_name]

Python Questionnaire Language

positional arguments:
  file_name             Python input file

optional arguments:
  -h, --help            show this help message and exit
  -l {debug,info,warn,error,critical}, --log-level {debug,info,warn,error,critical}
                        Log level. Only log messages with a level higher or
                        equal than this will be printed. Default: 'warn'
  -v, --version         Prints the program version.
  -t, --test            Runs the testsuite.
```

example: python run_app.py forms/simple.ql

# Testing

```
pytest
```