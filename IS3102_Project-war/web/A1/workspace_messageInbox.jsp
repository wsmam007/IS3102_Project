<%@page import="HelperClasses.MessageHelper"%>
<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="EntityManager.StaffEntity"%>
<%@page import="EntityManager.RoleEntity"%>
<%@page import="java.util.List"%>
<html lang="en">
    <jsp:include page="../header2.html" />

    <body>
        <script>

            var checkFlag = 'false';
            function readMessage(id) {
                rolesManagement.id.value = id;
                document.rolesManagement.action = "workspace_messageRead.jsp";
                document.rolesManagement.submit();
            }
            function deleteMessage() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.rolesManagement.action = "../WorkspaceMessage_RemoveMessageServlet";
                    document.rolesManagement.submit();
                } else {
                    window.event.returnValue = false;
                }
            }
            function sendMessage() {
                window.event.returnValue = true;
                document.rolesManagement.action = "workspace_messageAdd.jsp";
                document.rolesManagement.submit();
            }
            function checkAll(source) {
                checkboxes = document.getElementsByName('delete');
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }
        </script>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Inbox</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="workspace.jsp">Workspace</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-user"></i> <a href="workspace_messageInbox.jsp">Messages</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Inbox
                                </li>
                            </ol>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <%
                                        String errMsg = request.getParameter("errMsg");
                                        if (errMsg == null || errMsg.equals("")) {
                                            errMsg = "Insert some text";
                                        }
                                        out.println(errMsg);
                                    %>
                                </div>
                                <!-- /.panel-heading -->
                                <form name="rolesManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox" onclick="checkAll(this)" /></th>
                                                            <th>From</th>
                                                            <th>Date Received</th>
                                                            <th>To</th>
                                                            <th>Open</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<MessageHelper> inbox = (List<MessageHelper>) (session.getAttribute("inboxMessages"));
                                                            if (inbox != null) {
                                                                for (int i = 0; i < inbox.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=inbox.get(i).getMessageId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=inbox.get(i).getSenderName()%>
                                                            </td>
                                                            <td>
                                                                <%
                                                                    Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                                    String dateString = formatter.format(inbox.get(i).getSentDate());
                                                                    out.println(dateString);
                                                                %>
                                                            </td>
                                                            <td>
                                                                <%
                                                                    List<String> receviers = (List<String>) (inbox.get(i).getReceiversName());
                                                                    if (receviers.isEmpty()) {
                                                                        out.println("-");
                                                                    } else {
                                                                        for (int k = 0; k < receviers.size(); k++) {
                                                                            out.println(receviers.get(i));
                                                                        }
                                                                    }
                                                                %>
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=inbox.get(i).getMessageId()%>" value="read" onclick="readMessage()" />
                                                            </td>
                                                        </tr>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Create Message" onclick="sendMessage()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Delete Message" onclick="deleteMessage()"  />
                                                </div>
                                            </div>
                                            <input type="hidden" name="id" value="">    
                                        </div>

                                    </div>
                                    <!-- /.panel-body -->
                                </form>

                            </div>
                            <!-- /.panel -->
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->


                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->


        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
            $(document).ready(function() {
                $('#dataTables-example').dataTable();
            }
            );
        </script>
    </body>
</html>