<%@page import="EntityManager.WarehouseEntity"%>
<%@page import="EntityManager.StorageBinEntity"%>
<%@page import="java.util.List"%>
<% WarehouseEntity warehouseEntity = (WarehouseEntity) (session.getAttribute("warehouseEntity"));
    if (warehouseEntity == null) {
        response.sendRedirect("../ManufacturingWarehouseManagement_Servlet");
    } else {
%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function updateStorageBin(id) {
                storagebinManagement.id.value = id;
                document.storagebinManagement.action = "storageBinManagement_Update.jsp";
                document.storagebinManagement.submit();
            }
            function removeStorageBin() {
                checkboxes = document.getElementsByName('delete');
                var numOfTicks = 0;
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    if (checkboxes[i].checked) {
                        numOfTicks++;
                    }
                }
                if (checkboxes.length == 0 || numOfTicks == 0) {
                    window.event.returnValue = true;
                    document.storagebinManagement.action = "../StorageBinManagement_Servlet";
                    document.storagebinManagement.submit();
                } else {
                    window.event.returnValue = true;
                    document.storagebinManagement.action = "../StorageBinManagement_RemoveServlet";
                    document.storagebinManagement.submit();
                }
            }
            function addStorageBin() {
                window.event.returnValue = true;
                document.storagebinManagement.action = "storageBinManagement_Add.jsp";
                document.storagebinManagement.submit();
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
            <%
                try {
            %>
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Storage Bin Management</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-home"></i> <a href="manufacturingWarehouseManagement_view.jsp">Manufacturing Warehouse Management</a>
                                </li>
                                <li>
                                    <i class="icon icon-home"></i> <a href="../ManufacturingWarehouseManagement_Servlet?destination=manufacturingWarehouseManagement.jsp&id=<%=warehouseEntity.getId()%>"><%=warehouseEntity.getWarehouseName()%></a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-archive"></i> Storage Bin Management
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
                                        String goodMsg = request.getParameter("goodMsg");
                                        if (errMsg == null && goodMsg == null) {
                                            out.println("Add and remove storage bins");
                                        } else if ((errMsg != null) && (goodMsg == null)) {
                                            if (!errMsg.equals("")) {
                                                out.println(errMsg);
                                            }
                                        } else if ((errMsg == null && goodMsg != null)) {
                                            if (!goodMsg.equals("")) {
                                                out.println(goodMsg);
                                            }
                                        }
                                    %>
                                </div>
                                <!-- /.panel-heading -->
                                <form name="storagebinManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Storage Bin" onclick="addStorageBin()"  />
                                                    <a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Remove Storage Bin</button></a>
                                                </div>
                                            </div>
                                            <br>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th>ID</th>
                                                            <th>Type</th>
                                                            <th>Length</th>
                                                            <th>Width</th>
                                                            <th>Height</th>
                                                            <th>Total Volume</th>
                                                            <th>Avaliable Volume</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<StorageBinEntity> storageBins = (List<StorageBinEntity>) (session.getAttribute("storageBins"));
                                                            if (storageBins != null) {
                                                                for (int i = 0; i < storageBins.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=storageBins.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=storageBins.get(i).getId()%>
                                                            </td>
                                                            <td>
                                                                <%=storageBins.get(i).getType()%>
                                                            </td>
                                                            <td>
                                                                <%=storageBins.get(i).getLength()%>
                                                            </td>
                                                            <td>
                                                                <%=storageBins.get(i).getWidth()%>
                                                            </td>
                                                            <td>
                                                                <%=storageBins.get(i).getHeight()%>
                                                            </td>
                                                            <td>
                                                                <%=storageBins.get(i).getVolume()%>
                                                            </td>
                                                            <td>
                                                                <%=storageBins.get(i).getFreeVolume()%>
                                                            </td>
                                                            <td>
                                                                <%
                                                                    int volume = storageBins.get(i).getVolume();
                                                                    int freeVolume = storageBins.get(i).getFreeVolume();
                                                                    if (volume == freeVolume) {
                                                                %>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=storageBins.get(i).getId()%>" value="Update" onclick="javascript:updateStorageBin('<%=storageBins.get(i).getId()%>')"/>
                                                                <%} else {%>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block"  value="Update" disabled/>
                                                                <%}%>
                                                            </td>
                                                        </tr>
                                                        <%
                                                                    }
                                                                }
                                                            } catch (Exception ex) {
                                                                response.sendRedirect("manufacturingWarehouseManagement.jsp");
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add Storage Bin" onclick="addStorageBin()"  />
                                                    <a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Remove Storage Bin</button></a>
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
        <div role="dialog" class="modal fade" id="myModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>Alert</h4>
                    </div>
                    <div class="modal-body">
                        <p id="messageBox">Storage bin will be removed. Are you sure?</p>
                    </div>
                    <div class="modal-footer">                        
                        <input class="btn btn-primary" name="btnRemove" type="submit" value="Confirm" onclick="removeStorageBin()"  />
                        <a class="btn btn-default" data-dismiss ="modal">Close</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            });
        </script>

    </body>

</html>
<%}%>