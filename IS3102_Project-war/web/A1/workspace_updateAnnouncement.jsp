<%@page import="EntityManager.AnnouncementEntity"%>
<%@page import="java.util.List"%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <body>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Announcement Update
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="workspace.jsp">Workspace</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-user"></i><a href="workspace_viewAnnouncement.jsp"> View Announcements</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Announcement Update
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <%

                        try {
                            String id = request.getParameter("id");
                            List<AnnouncementEntity> listOfAnnouncements = (List<AnnouncementEntity>) (session.getAttribute("listOfAnnouncements"));
                            AnnouncementEntity announcement = new AnnouncementEntity();
                            for (int i = 0; i < listOfAnnouncements.size(); i++) {
                                if (listOfAnnouncements.get(i).getId() == Integer.parseInt(id)) {
                                    announcement = listOfAnnouncements.get(i);
                                }
                            }
                    %>

                    <div class="row">
                        <div class="col-lg-6">

                            <form role="form" action="../RoleManagement_UpdateRoleServlet">
                                <div class="form-group">
                                    <label>Sender</label>
                                    <input class="form-control" name="sender" type="text" value="<%=announcement.getSender()%>" disabled/>
                                </div>
                                <div class="form-group">
                                    <label>Title</label>
                                    <input class="form-control" name="title" type="text" value="<%=announcement.getTitle()%>">
                                </div>
                                <div class="form-group">
                                    <label>Message</label>
                                    <input class="form-control" required="true" type="text" name="message" value="<%=announcement.getMessage()%>"/>
                                </div>
                                <div class="form-group">
                                    <label>Expiry Date</label>
                                    <input class="form-control" required="true" type="text" name="expiryDate" value="<%=announcement.getExpiryDate()%>"/>
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Update" class="btn btn-lg btn-primary btn-block">
                                </div>
                                <input type="hidden" value="<%=announcement.getId()%>" name="id">
                            </form>
                        </div>
                        <!-- /.row -->
                    </div>
                    <%
                        } catch (Exception ex) {
                            response.sendRedirect("../WorkspaceAnnouncement_Servlet");
                        }%>

                </div>

            </div>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->


        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
            $(document).ready(function() {
                $('#dataTables-example').dataTable();
            });
        </script>

    </body>

</html>