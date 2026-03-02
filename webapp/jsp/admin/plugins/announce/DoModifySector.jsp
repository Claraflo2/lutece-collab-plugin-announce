<%@ page errorPage="../../ErrorPage.jsp" %>

<%@page import="fr.paris.lutece.plugins.announce.web.SectorJspBean"%>

${ sectorJspBean.init( pageContext.request, SectorJspBean.RIGHT_MANAGE_ANNOUNCE ) }
${ pageContext.response.sendRedirect( sectorJspBean.doModifySector( pageContext.request ) ) }
