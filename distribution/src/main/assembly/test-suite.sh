#!/bin/bash
echo "Using Client: $1"
echo "Iteration Count: $2"
if [ -z "$3" ]; then
    echo "Warning! Warm up iteration count not specified."
    warm_up=""
else
    echo "Warmup Rounds: $3"
    warm_up="-w $3"
fi
echo "Using JAVA_HOME: $JAVA_HOME"
export PATH=$JAVA_HOME/bin:$PATH
java_ver=`java -version`
echo "Using Java Version: ${java_ver}"

for size in 0 100 1024 10240 51200 102400 1048576
do
    if [ ${size} == "0" ]; then
        echo "Invoking doNothing operation"
        operation="doNothing"
        size_opt=""
    else
        echo "Invoking echoString operation with payload size: ${size}"
        operation="echoString"
        size_opt="-s ${size}"
    fi
    bash run.sh -c $1 -n $2 -o ${operation} ${size_opt} ${warm_up}
done

echo "Done."