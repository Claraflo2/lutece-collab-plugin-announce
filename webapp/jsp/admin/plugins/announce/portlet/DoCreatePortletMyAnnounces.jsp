<%@ page errorPage="../../../ErrorPage.jsp" %>

<%@page import="fr.paris.lutece.plugins.announce.web.portlet.MyAnnouncesPortletJspBean"%>

${ myAnnouncesPortletJspBean.init( pageContext.request, MyAnnouncesPortletJspBean.RIGHT_MANAGE_ADMIN_SITE ) }
${ pageContext.response.sendRedirect( myAnnouncesPortletJspBean.doCreate( pageContext.request ) ) }
