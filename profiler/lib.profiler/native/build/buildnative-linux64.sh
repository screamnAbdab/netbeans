#!/bin/sh

# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.

BuildForJDK()
{
        JAVA_HOME=$1
        JDK_ID=$2
        echo $JAVA_HOME $JDK_ID
	gcc32 -I$JAVA_HOME/include -I$JAVA_HOME/include/linux -DLINUX -pthread -fPIC -shared -O3 -Wall -m64  \
	-o ../../release/lib/deployed/$JDK_ID/linux-amd64/libprofilerinterface.so \
	../src-jdk15/class_file_cache.c \
	../src-jdk15/attach.c \
	../src-jdk15/Classes.c \
	../src-jdk15/HeapDump.c \
	../src-jdk15/Timers.c \
	../src-jdk15/GC.c \
	../src-jdk15/Threads.c \
	../src-jdk15/Stacks.c \
	../src-jdk15/common_functions.c

        rm -f *.o
}

BuildForJDK "$JAVA_HOME_15" "jdk15"
BuildForJDK "$JAVA_HOME_16" "jdk16"

