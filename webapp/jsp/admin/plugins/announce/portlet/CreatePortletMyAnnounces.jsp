<%@ page errorPage="../../../ErrorPage.jsp" %>
<jsp:include page="../../../PortletAdminHeader.jsp" />

<%@page import="fr.paris.lutece.plugins.announce.web.portlet.MyAnnouncesPortletJspBean"%>

${ myAnnouncesPortletJspBean.init( pageContext.request, MyAnnouncesPortletJspBean.RIGHT_MANAGE_ADMIN_SITE ) }
${ myAnnouncesPortletJspBean.getCreate( pageContext.request ) }
