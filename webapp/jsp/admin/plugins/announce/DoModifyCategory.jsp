<%@ page errorPage="../../ErrorPage.jsp" %>

<%@page import="fr.paris.lutece.plugins.announce.web.CategoryJspBean"%>

${ categoryJspBean.init( pageContext.request, CategoryJspBean.RIGHT_MANAGE_ANNOUNCE ) }
${ pageContext.response.sendRedirect( categoryJspBean.doModifyCategory( pageContext.request ) ) }
