<g:if test="${personInstanceList}">
  <fieldset class="searchResults">
	<legend>Search Results</legend>
  <g:each status="i" var="p" in="${personInstanceList}">
	<div class="searchResult"><g:link controller="person" action="show" id="${p.id}">${p.fullName}</g:link></div>
	<g:if test="${i > 20}"><div class="searchResult">...</div></g:if>
  </g:each>
  </fieldset>
</g:if>
