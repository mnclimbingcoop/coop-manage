<g:if test="${accessCardInstanceList}">
  <fieldset class="searchResults">
	<legend>Search Results</legend>
  <g:each var="c" in="${accessCardInstanceList}">
	<p><g:link controller="accessCard" action="edit" id="${c.id}">${c.cardIdentifier} (${c.label})</g:link></p>
  </g:each>
  </fieldset>
</g:if>