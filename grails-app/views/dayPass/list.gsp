<%@ page import="coop.mnclimbing.DayPass" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dayPass.label', default: 'DayPass')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
        <style type="text/css">
        #passHistogram { 
            background-color: #FDD;
            } 
        .startDate {
        	display:inline;
        	max-width: 20px;
        }
        .endDate {
        	display:inline;
        	max-width: 20px;
        }
        #hoverDate {
        	width: 12em;
        	float: left;
        	clear: both;
        }
        </style>
        <script>
		$(function() {
			$("#passHistogram").hover(function(){
				$("#hoverDate").text('');
			});		
			
			$("img.dateBar").hover(function(){
				var theDate = $(this).attr('alt');
				$("#hoverDate").text(theDate);
			});		
		});
		</script>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1>Day Passes</h1>
            <g:if test="${flash.message}">
           	<div class="message">${flash.message}</div>
            </g:if>
            Histogram from <g:formatDate date="${dayPassStartDate}" format="MM/dd/yyyy" />
            to <g:formatDate date="${dayPassEndDate}" format="MM/dd/yyyy" />
            
            <div id="passHistogram">
            	<div id="hoverDate"></div><br/>
            	<g:each var="d" in="${dayPassHistogram}">
            	<g:link action="list" params="${[dayPassRefDate:formatDate(date:d.date, format:'yyyy-MM-dd')]}" title="${formatDate(date:d.date, format:'yyyy-MM-dd')}">
            	<img class="dateBar" src="${resource(dir:'images',file:'orange1px.gif')}" width="5px" height="${d.count * 2	}" alt="${formatDate(date:d.date, format:'yyyy-MM-dd')}" style="margin-right:1px;" />
            	</g:link>
            	</g:each>
            </div>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="name" title="${message(code: 'dayPass.amount.label', default: 'Name')}" />
                        
                            <th>Sponsor</th>
                        
                            <g:sortableColumn property="paymentDate" title="${message(code: 'dayPass.paymentDate.label', default: 'Payment Date')}" />
                                                
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${dayPassInstanceList}" status="i" var="dayPassInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td>${fieldValue(bean: dayPassInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: dayPassInstance, field: "sponsor")}</td>
                        
                            <td><g:link action="edit" id="${dayPassInstance.id}">
                            	<g:formatDate date="${dayPassInstance.paymentDate}" format="MM/dd/yyyy" />
                            	</g:link>
                            </td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
