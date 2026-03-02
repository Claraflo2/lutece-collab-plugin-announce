<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%@page import="fr.paris.lutece.plugins.announce.web.CategoryJspBean"%>

${ categoryJspBean.init( pageContext.request, CategoryJspBean.RIGHT_MANAGE_ANNOUNCE ) }
${ categoryJspBean.getDuplicateCategory( pageContext.request ) }

<%@ include file="../../AdminFooter.jsp" %>
