#!/bin/sh

# resolve links - $0 may be a softlink
PRG="$0"

while [ -h "$PRG" ]; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '.*/.*' > /dev/null; then
    PRG="$link"
  else
    PRG=`dirname "$PRG"`/"$link"
  fi
done

# Get standard environment variables
PRGDIR=`dirname "$PRG"`

# Only set RPC_TEST_HOME if not already set
[ -z "$RPC_TEST_HOME" ] && RPC_TEST_HOME=`cd "$PRGDIR/.." ; pwd`
RPC_TEST_CLASSPATH="$RPC_TEST_HOME/lib"
for f in $RPC_TEST_HOME/lib/*.jar
do
    RPC_TEST_CLASSPATH=$RPC_TEST_CLASSPATH:$f
done

java -Djava.security.policy=../conf/client.policy -classpath $RPC_TEST_CLASSPATH edu.ucsb.cs.rpc.base.Main -f ../conf/rpc.properties $*