<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%@page import="fr.paris.lutece.plugins.announce.web.AnnounceJspBean"%>

${ announceJspBean.init( pageContext.request, AnnounceJspBean.RIGHT_MANAGE_ANNOUNCE ) }
${ announceJspBean.getManageAnnounces( pageContext.request ) }

<%@ include file="../../AdminFooter.jsp" %>
