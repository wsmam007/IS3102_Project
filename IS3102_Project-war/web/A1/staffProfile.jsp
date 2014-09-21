<%@page import="EntityManager.RoleEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.StaffEntity"%>
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
                                User Profile
                            </h1>
                        </div>
                    </div>
                    <!-- /.row -->

                    <jsp:include page="../displayMessage.jsp" />
                    <%
                        try {
                            StaffEntity staffEntity = (StaffEntity) session.getAttribute("staffEntity");
                    %>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="tabs">
                                <ul class="nav nav-tabs">
                                    <li class="active">
                                        <a href="#overview" data-toggle="tab"><i class="icon icon-user"></i> Overview</a>
                                    </li>
                                    <li>
                                        <a href="#recent" data-toggle="tab">Recent</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div id="overview" class="tab-pane active">
                                        <form role="form" action="../StaffManagement_UpdateStaffServlet">
                                            <h4>Personal Information</h4>
                                            <div class="form-group">
                                                <label>Identification No</label>
                                                <input class="form-control" required="true" name="identificationNo" type="text" value="<%=staffEntity.getIdentificationNo()%>">
                                            </div>
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input class="form-control" required="true" name="name" type="text" value="<%=staffEntity.getName()%>">
                                            </div>
                                            <div class="form-group">
                                                <label>E-mail Address</label>
                                                <input class="form-control" required="true" value="<%=staffEntity.getEmail()%>" disabled/>
                                            </div>
                                            <div class="form-group">
                                                <label>Phone</label>
                                                <input class="form-control" required="true" type="text" name="phone" value="<%=staffEntity.getPhone()%>">
                                            </div>

                                            <hr class="more-spaced "/>
                                            <h4>Change Password</h4>
                                            <div class="form-group">
                                                <label>New Password (leave blank unless setting a new password)</label>
                                                <input class="form-control" type="password" name="password">
                                            </div>
                                            <div class="form-group">
                                                <label>Re-enter New Password</label>
                                                <input class="form-control" type="password"  name="repassword">
                                            </div>
                                            <div class="form-group">
                                                <label>Address</label>
                                                <input class="form-control" type="text" required="true" name="address" value="<%=staffEntity.getAddress()%>">
                                            </div>
                                            <div class="panel-footer" style="padding-bottom: 0px;">
                                                <div class="row">
                                                    <div class="form-group">
                                                        <input type="submit" value="Submit" class="btn btn-primary"/>
                                                        <input type="reset" value="Reset" class="btn btn-primary"/>
                                                    </div>
                                                    <input type="hidden" value="<%=staffEntity.getId()%>" name="id">
                                                    <input type="hidden" value="A1/staffProfile.jsp" name="source"/>
                                                    <input type="hidden" value="<%=staffEntity.getEmail()%>" name="email"/>
                                                </div>
                                            </div>
                                        </form>

                                    </div>
                                    <div id="recent" class="tab-pane">
                                        <p>Recent</p>
                                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitat.</p>
                                    </div>
                                </div>

                            </div>


                        </div>
                    </div>

                </div>
                <!-- /#page-wrapper -->
            </div>
            <!-- /#wrapper -->

            <%
                } catch (Exception ex) {
                    response.sendRedirect("../StaffManagement_StaffServlet");
                    ex.printStackTrace();
                }%>
            <!-- Page-Level Demo Scripts - Tables - Use for reference -->
            <script>
                $(document).ready(function () {
                    $('#dataTables-example').dataTable();
                });
            </script>

    </body>

</html>