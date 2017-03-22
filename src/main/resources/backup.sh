#!/bin/sh

date=`date +%Y%m%d%H%M%S`
files=`ls *.jar`
for file in ${files}
do
#	target=${file%.*}
	cp ${file} ${file}_${date}
done

