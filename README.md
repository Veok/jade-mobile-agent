## Jade agent program done for university classes

## Setup

1. Download Jade framework
1. Run command that sets CLASSPATH to the Jade libs

eg.
`export CLASSPATH=/Users/lelele/Projects/pz-lab1/jade/lib/jade.jar:/Users/lelele/Projects/pz-lab1/jade/lib/commons-codec/commons-codec-1.3.jar:/Users/lelele/Projects/pz-lab1/mobile-agent/mobile-agent/src:.:$CLASSPATH`

2. Compile java code in the src directory

`javac *.java`

3. Run jade

`java jade.Boot -gui`
`java jade.Boot -container`