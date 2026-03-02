<%@ page errorPage="../../../ErrorPage.jsp" %>
<jsp:include page="../../../PortletAdminHeader.jsp" />

<%@page import="fr.paris.lutece.plugins.announce.web.portlet.LastAnnouncesPortletJspBean"%>

${ lastAnnouncesPortletJspBean.init( pageContext.request, LastAnnouncesPortletJspBean.RIGHT_MANAGE_ADMIN_SITE ) }
${ lastAnnouncesPortletJspBean.getCreate( pageContext.request ) }
