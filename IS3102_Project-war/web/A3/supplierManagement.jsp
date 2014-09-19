<%@page import="EntityManager.SupplierEntity"%>
<%@page import="java.util.List"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateSupplier(id) {
                supplierManagement.id.value = id;
                document.supplierManagement.action = "supplierManagement_update.jsp";
                document.supplierManagement.submit();
            }
            function removeSupplier() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.supplierManagement.action = "../SupplierManagement_RemoveSupplierServlet";
                    document.supplierManagement.submit();
                } else {
                    window.event.returnValue = false;
                }
            }
            function addSupplier() {
                window.event.returnValue = true;
                document.supplierManagement.action = "supplierManagement_add.jsp";
                document.supplierManagement.submit();
            }
            function checkAll() {
                alert("Check all the checkboxes...");
                var allRows = document.supplierManagement.getElementsByTagName("delete");
                for (var i = 0; i < allRows.length; i++) {
                    if (allRows[i].type == 'checkbox') {
                        allRows[i].checked = true;
                    }
                }
            }
        </script>
        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Supplier Management</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="supplierManagement.jsp">Supplier Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Supplier Management
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
                                <form name="supplierManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll()" /></th>
                                                            <th>Name</th>
                                                            <th>Phone</th>
                                                            <th>Email</th>
                                                            <th>Country</th>
                                                            <th>Address</th>
                                                            <th>Update</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<SupplierEntity> suppliers = (List<SupplierEntity>) (session.getAttribute("suppliers"));
                                                            if (suppliers != null) {
                                                                for (int i = 0; i < suppliers.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=suppliers.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=suppliers.get(i).getSupplierName()%>
                                                            </td>
                                                            <td>
                                                                <%=suppliers.get(i).getContactNo()%>
                                                            </td>
                                                            <td>
                                                                <%=suppliers.get(i).getEmail()%>
                                                            </td>
                                                            <td>
                                                                <%=suppliers.get(i).getCountry().getName()%>
                                                            </td>
                                                            <td>
                                                                <%=suppliers.get(i).getAddress()%>
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=suppliers.get(i).getId()%>" value="update" onclick="javascript:updateSupplier('<%=suppliers.get(i).getId()%>')"/>
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
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Supplier" onclick="addSupplier()"  />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Supplier" onclick="removeSupplier()"  />
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
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
        </script>

    </body>

</html>
