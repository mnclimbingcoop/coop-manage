<%@ page contentType="text/xml" %><hid:Credentials action="AD" recordOffset="0" recordCount="${credentials.size()}" moreRecords="false"><g:each var="c" in="${credentials}"><g:if test="${c.formatID > 0}" ><hid:Credential rawCardNumber="${c.rawCardNumber}" isCard="${c.isCard}" cardNumber="${c.cardNumber}" formatID="${c.formatID}" formatName="${c.formatName}" endTime="${g.formatDate(date:c.endTime, format:"yyyy-MM-dd'T'HH:mm:ss")}" cardholderID="${c.cardholderID}" extendedAccess="${c.extendedAccess}" exemptFromPassback="${c.exemptFromPassback}" pinCommands="${c.pinCommands}" confirmingPinExempt="${c.confirmingPinExempt}"/></g:if><g:else><hid:Credential rawCardNumber="${c.rawCardNumber}" isCard="${c.isCard}" cardNumber="${c.cardNumber}" endTime="${g.formatDate(date:c.endTime, format:'yyyy-MM-dd')}T${g.formatDate(date:c.endTime, format:'HH:mm:ss')}" cardholderID="${c.cardholderID}" extendedAccess="${c.extendedAccess}" exemptFromPassback="${c.exemptFromPassback}" pinCommands="${c.pinCommands}" confirmingPinExempt="${c.confirmingPinExempt}"/></g:else></g:each></hid:Credentials>