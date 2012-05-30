<g:if test="${accessInstance?.person?.emailAddress}">
Hi there ${accessInstance.person},

We noticed your MNCC 24/7 climbing access pass is expiring on <g:formatDate date="${accessInstance.endDate}" format="MMMM d, yyyy" /> . Come on into the coop during open hours [1] to renew your pass and fill out a new waiver if you haven't in the past year.

If you wish, you can renew your access online at the following URL:
http://www.mnclimbingcoop.com/climbing-pass

Have questions? Drop us an email at info@mnclimbingcoop.com !

Thanks,
The Minnesota Climbing Coop Volunteer Staff

Don't want to be contacted at ${accessInstance?.person?.emailAddress}?  Let us know at info@mnclimbingcoop.com!  Thanks.

[1] Open Hours : http://www.mnclimbingcoop.com/open-hours
</g:if>
<g:else>
Hello MNCC Volunteer!

It appears that ${accessInstance.person}'s access pass is expiring on <g:formatDate date="${accessInstance.endDate}" format="MMMM d, yyyy" />, but we don't have an email address on file for them.
<g:if test="${accessInstance?.person?.phoneNumber}">
Unfortunately, we also don't have a phone number on file for them either!
If you can track them down to let them know that their pass is expiring, or know somone who could, that'd be great.
</g:if><g:else>
Their phone number is "${accessInstance?.person?.phoneNumber}" if you or someone would be willing to give them
a quick call to let them know that their pass is expiring, that'd be great.
</g:else>
Additionally if we could get updated contact information for them, that'd be great!  You can enter their information directly via this link:
<g:link controller="person" action="edit" id="${ccessInstance?.person?.id}" absolute="true" />
</g:else>
