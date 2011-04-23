<%@ page contentType="text/plain" %>
#!/bin/bash

xmlfile="$1"
working=bob_is_working

if [ -e "$xmlfile" ]; then

        if [ ! -d $working ]; then
                mkdir $working/
        fi

        cp "$xmlfile" $working/backupxml.xml
        cd $working/
        xattr -d com.apple.quarantine backupxml.xml
        shasum backupxml.xml > backupxml.sum
        tar -cvzf ../backupxml.bob backupxml.???
        cd ..
        rm -rf $working/
else
	echo "usage:"
	echo "	$0 <backupxml.xml>"
fi