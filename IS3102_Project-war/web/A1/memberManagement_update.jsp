<%@page import="EntityManager.RoleEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.MemberEntity"%>
<html lang="en">

    <jsp:include page="../header2.html" />
    <body>
        <style>
            label{
                font-size: 18px;
            }
        </style>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />

            <div id="page-wrapper">
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Member Particulars Update
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-users"></i> <a href="accountManagement.jsp">Account Management</a>
                                </li>
                                <li>
                                    <i class="icon icon-users"></i> <a href="memberManagement.jsp">Member Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i>  Member Particulars Update
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <jsp:include page="../displayMessage.jsp" />


                    <%
                        List<MemberEntity> members = (List<MemberEntity>) session.getAttribute("members");
                        try {
                            String id = (String) session.getAttribute("memberUpdateId");
                            MemberEntity member = new MemberEntity();
                            for (int i = 0; i < members.size(); i++) {
                                if (members.get(i).getId() == Long.parseLong(id)) {
                                    member = members.get(i);
                                }
                            }
                    %>

                    <div class="row">
                        <div class="col-lg-6">

                            <form role="form" action="../MemberManagement_UpdateMemberServlet" onsubmit="return validatePassword()">
                                <div class="form-group">
                                    <label>Identification No</label>
                                    <input class="form-control" required="true" name="identificationNo" type="text" value="<%=member.getIdentificationNo()%>">
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control" required="true" name="name" type="text" value="<%=member.getName()%>">
                                </div>
                                <div class="form-group">
                                    <label>E-mail Address</label>
                                    <input class="form-control" required="true" type="email" name="email" value="<%=member.getEmail()%>" disabled/>
                                </div>
                                <div class="form-group">
                                    <label>Phone</label>
                                    <input class="form-control" required="true" type="text" name="phone" value="<%=member.getPhone()%>">
                                </div>
                                <div class="form-group">
                                    <label>Address</label>
                                    <input class="form-control" type="text" required="true" name="address" value="<%=member.getAddress()%>">
                                </div>
                                <div class="form-group">
                                    <label>New Password (leave blank unless setting a new password).</label>
                                    <input class="form-control" type="password" name="password" id="password">
                                </div>
                                <div class="form-group">
                                    <label>Re-enter New Password</label>
                                    <input class="form-control" type="password" name="repassword" id="repassword">
                                </div>
                                
                                <div class="form-group">
                                    <label>Roles Assignment</label><br/>
                                    <%
                                        List<RoleEntity> roles = (List<RoleEntity>) session.getAttribute("memberUpdateRoles");
                                        boolean role1, role2, role3, role4, role5, role6, role7;
                                        role1 = role2 = role3 = role4 = role5 = role6 = role7 = false;

                                        for (RoleEntity currentRole : roles) {
                                            System.out.println(currentRole.getName());
                                            if (currentRole.getId().toString().equals("1")) {
                                                role1 = true;
                                            } else if (currentRole.getId().toString().equals("2")) {
                                                role2 = true;
                                            } else if (currentRole.getId().toString().equals("3")) {
                                                role3 = true;
                                            } else if (currentRole.getId().toString().equals("4")) {
                                                role4 = true;
                                            } else if (currentRole.getId().toString().equals("5")) {
                                                role5 = true;
                                            } else if (currentRole.getId().toString().equals("6")) {
                                                role6 = true;
                                            } else if (currentRole.getId().toString().equals("7")) {
                                                role7 = true;
                                            }
                                        }
                                    %>
                                    <table class="table table-hover">
                                        <%
                                            List<RoleEntity> roleList = (List<RoleEntity>) session.getAttribute("allRoles");
                                            for (RoleEntity role : roleList) {
                                        %>
                                        <tr>
                                            <td><input type="checkbox" name="roles" value="<%= role.getId() %>" <%if (roles.contains(role)) {%>checked<%}%>/> <%= role.getName() %> </td>
                                            <td><span class="btn btn-default"><a href="../AccessRight_Servlet/AccessRight_GET?memberId=<%= member.getId() %>&roleId=<%= role.getId() %>">Customize Access Right</a></span></td>
                                        </tr>
                                        <%
                                            }
                                        %>                                        
                                    </table>
                                </div>
                                <div class="form-group">
                                    <input type="hidden" name="update" value="yes"/>
                                    <input type="submit" value="Update" class="btn btn-lg btn-primary btn-block"/>
                                </div>
                                <input type="hidden" value="<%=member.getId()%>" name="id">
                            </form>
                        </div>
                        <!-- /.row -->
                    </div>
                    <%
                        } catch (Exception ex) {
                            response.sendRedirect("../MemberManagement_MemberServlet");
                            ex.printStackTrace();
                        }%>

                </div>

            </div>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->
        <script>
             function validatePassword() {
                    var password = document.getElementById("password").value;
                    var repassword = document.getElementById("repassword").value;
                    var ok = true;
                    if ((password != null && repassword != null) || (password != "" && repassword != "")) {
                        if (password != repassword) {
                            //alert("Passwords Do not match");
                            document.getElementById("password").style.borderColor = "#E34234";
                            document.getElementById("repassword").style.borderColor = "#E34234";
                            alert("Passwords do not match. Please key again.");
                            ok = false;
                        } 
                    } else {
                        return ok;
                    }
                    return ok;
                }
        </script>F
    </body>

</html>
