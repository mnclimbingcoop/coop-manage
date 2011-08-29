#!/bin/bash

host="localhost:8443/coop-manage"
#host="ajz.healthstudies.umn.edu/mncc"
action="/import/events/"
key="RANDOMLYGENERATEDKEY"

file="${1}"
door="${2}"

url="https://${host}${action}${door}?key=${key}"

if [ -f "${file}" ]; then

	output="${file/\.csv/.xml}"
	results="${file/\.csv/-results.xml}"
	echo '<?xml version="1.0" encoding="UTF-8" ?>' > "${output}"
	echo "<eventlog>" >> "${output}"

	cat "${file}" \
		| tr -d '\15\32' \
		| sed -E 's/^/	<event><code>/' \
		| sed -E 's/,/<\/code><date>/' \
		| sed -E 's/,/<\/date><subject>/' \
		| sed -E 's/$/<\/subject><\/event>/' \
		>> "${output}"

	echo "</eventlog>" >> "${output}"

	content_type="text/xml"

	#less "${output}"

	echo "Sending file: ${file}"
	echo "To: ${url}"

	curl ${url} \
		--insecure \
		--silent \
		--request POST \
		--header "Content-Type:${content_type}" \
		--data @"${output}" \
		--output "${results}.tmp"

	rm "${output}"
	tidy -quiet -xml "${results}.tmp" > "${results}"
	rm "${results}.tmp"

	r_total=`xpath "${results}" '//entry[@key="total"]' 2>1 | grep entry | sed -e 's/<\/.*//' -e 's/.*>//'`
	r_existing=`xpath "${results}" '//entry[@key="existing"]' 2>1 | grep entry | sed -e 's/<\/.*//' -e 's/.*>//'`
	r_created=`xpath "${results}" '//entry[@key="created"]' 2>1 | grep entry | sed -e 's/<\/.*//' -e 's/.*>//'`
	r_errors=`xpath "${results}" '//entry[@key="errors"]' 2>1 | grep entry | sed -e 's/<\/.*//' -e 's/.*>//'`

	echo "Total: $r_total"
	echo "Existing: $r_existing"
	echo "Created: $r_created"
	echo "Errors: $r_errors"

else
	echo "Usage:"
	echo "	${0} <events filename>"
fi

