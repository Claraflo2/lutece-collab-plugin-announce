<%@ page errorPage="../../ErrorPage.jsp" %>

<%@page import="fr.paris.lutece.plugins.announce.web.AnnounceJspBean"%>

${ announceJspBean.init( pageContext.request, AnnounceJspBean.RIGHT_MANAGE_ANNOUNCE ) }
${ pageContext.response.sendRedirect( announceJspBean.doSuspendAnnounce( pageContext.request ) ) }
