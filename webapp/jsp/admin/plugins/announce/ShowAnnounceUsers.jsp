<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%@page import="fr.paris.lutece.plugins.announce.web.AnnounceUserJspBean"%>

${ announceUserJspBean.init( pageContext.request, AnnounceUserJspBean.RIGHT_MANAGE_ANNOUNCE ) }
${ announceUserJspBean.getList( pageContext.request ) }

<%@ include file="../../AdminFooter.jsp" %>
