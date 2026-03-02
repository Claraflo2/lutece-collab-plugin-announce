<%@ page errorPage="../../ErrorPage.jsp" %>
<jsp:include page="../../AdminHeader.jsp" />

<%@page import="fr.paris.lutece.plugins.announce.web.SectorJspBean"%>

${ sectorJspBean.init( pageContext.request, SectorJspBean.RIGHT_MANAGE_ANNOUNCE ) }
${ sectorJspBean.getModifySector( pageContext.request ) }

<%@ include file="../../AdminFooter.jsp" %>
