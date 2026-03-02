<%@ page errorPage="../../../ErrorPage.jsp" %>

<%@page import="fr.paris.lutece.plugins.announce.web.portlet.LastAnnouncesPortletJspBean"%>

${ lastAnnouncesPortletJspBean.init( pageContext.request, LastAnnouncesPortletJspBean.RIGHT_MANAGE_ADMIN_SITE ) }
${ pageContext.response.sendRedirect( lastAnnouncesPortletJspBean.doCreate( pageContext.request ) ) }
