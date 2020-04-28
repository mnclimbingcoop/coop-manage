<g:if test="${accessInstance?.person?.emailAddress}">
Hi ${accessInstance.person},

We noticed that your MNCC 24/7 climbing access pass would normally be expiring on <g:formatDate date="${accessInstance.endDate}" format="MMMM d, yyyy" />.

In light of the COVID-19 pandemic, all Access Passes were frozen March 17 and will be extended for the length of time we are closed. 

We understand that this crisis has financially impacted us all. We are thankful that we’re a volunteer-run organization that doesn’t have to make tough decisions with payroll. However, we still have expenses during this time—mainly rent.

If you're willing and able, we need your continued support while we are closed. Please consider consider supporting our Renew, Waive, Donate campaign at https://www.mnclimbingcoop.com

If you have questions or would like to update your email address, send a note to info@mnclimbingcoop.com

Thank you for your support,
Minnesota Climbing Co-op
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
