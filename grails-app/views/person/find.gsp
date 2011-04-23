<g:if test="${personInstanceList}">
  <fieldset class="searchResults">
	<legend>Search Results</legend>
  <g:each var="p" in="${personInstanceList}">
	<p><g:link controller="person" action="edit" id="${p.id}">${p.fullName}</g:link></p>
  </g:each>
  </fieldset>
</g:if>