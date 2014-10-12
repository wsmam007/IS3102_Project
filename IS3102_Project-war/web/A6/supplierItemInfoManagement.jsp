<%@page import="EntityManager.SupplierEntity"%>
<%@page import="EntityManager.CountryEntity"%>
<%@page import="EntityManager.Supplier_ItemEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<html lang="en">

    <jsp:include page="../header2.html" />

    <body>
        <script>
            function checkAll(source) {
                checkboxes = document.getElementsByName('delete');
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }
            function add() {
                document.supplierItemInfoManagement_add.action = "../SupplierItemInfoManagement_AddSupplierItemInfoServlet";
                document.supplierItemInfoManagement_add.submit();
            }
            function update(id) {
                supplierItemInfoManagement.id.value = id;
                document.supplierItemInfoManagement.action = "supplierItemInfoManagement_update.jsp";
                document.supplierItemInfoManagement.submit();
            }
            function removeRecord() {
                checkboxes = document.getElementsByName('delete');
                var numOfTicks = 0;
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    if (checkboxes[i].checked) {
                        numOfTicks++;
                    }
                }
                if (checkboxes.length == 0 || numOfTicks == 0) {
                    alert("No record(s) selected.");
                    window.event.returnValue = false;
                } else {

                    var yes = confirm("Are you sure?!");
                    if (yes === true) {
                        window.event.returnValue = true;
                        document.supplierItemInfoManagement.action = "../SupplierItemInfoManagement_RemoveSupplierItemInfoServlet";
                        document.supplierItemInfoManagement.submit();
                    } else {
                        window.event.returnValue = false;
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
                            <h1 class="page-header">Supplier Item Info Management</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-user"></i>  <a href="itemManagement.jsp">Item Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-truck"></i>&nbsp;Supplier Item Info Management
                                </li>
                            </ol>
                        </div>
                        <!-- /.col-lg-12 -->
                    </div>
                    <!-- /.row -->

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading"> <%
                                    String errMsg = request.getParameter("errMsg");
                                    if (errMsg == null || errMsg.equals("")) {
                                        errMsg = "Welcome to Supplier Item Info Management";
                                    }
                                    out.println(errMsg);
                                    %>                                  
                                </div>
                                <!-- /.panel-heading -->
                                <form name="supplierItemInfoManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary btnAdd" name="btnAdd" type="button" value="Add Supplier Item Info" />
                                                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Remove Record(s)" onclick="removeRecord()"  />
                                                </div>
                                            </div>
                                            <br/>
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th style="width:5%"><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th style="width:15%">Supplier ID</th>
                                                            <th style="width:15%">SKU</th>
                                                            <th style="width:15%">Cost Price</th>
                                                            <th style="width:10%">Lot Size</th>
                                                            <th style="width:15%">Lead Time (weeks)</th>
                                                            <th style="width:8%">Update</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<Supplier_ItemEntity> listOfSupplierItemInfo = (List<Supplier_ItemEntity>) (session.getAttribute("listOfSupplierItemInfo"));
                                                            try {
                                                                if (listOfSupplierItemInfo != null) {
                                                                    for (int i = 0; i < listOfSupplierItemInfo.size(); i++) {

                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=listOfSupplierItemInfo.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=listOfSupplierItemInfo.get(i).getSupplier().getId()%>
                                                            </td>
                                                            <td>
                                                                <%=listOfSupplierItemInfo.get(i).getItem().getSKU()%>
                                                            </td>
                                                            <td>
                                                                <%=listOfSupplierItemInfo.get(i).getCostPrice()%>
                                                            </td>
                                                            <td>
                                                                <%=listOfSupplierItemInfo.get(i).getLotSize()%>
                                                            </td>
                                                            <td>
                                                                <%=listOfSupplierItemInfo.get(i).getLeadTime()%>
                                                            </td>
                                                            <td><input class="btn btn-primary" id="btnUpdate" onclick="update(<%=listOfSupplierItemInfo.get(i).getId()%>)" type="button" value="Update"/></td>
                                                        </tr>
                                                        <%
                                                                    }
                                                                }
                                                            } catch (Exception ex) {
                                                                System.out.println(ex);
                                                            }
                                                        %>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <!-- /.table-responsive -->
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <input class="btn btn-primary btnAdd" name="btnAdd" type="button" value="Add Supplier Item Info"/>
                                                    <input class="btn btn-primary" name="btnRemove" type="button" value="Remove Record(s)" onclick="removeRecord()"  />
                                                </div>
                                            </div>
                                            <input type="hidden" name="id" value="">  
                                            <input type="hidden" name="setPrice" value="">
                                        </div>
                                    </div>
                                    <!-- /.panel-body -->
                                </form>
                                <form name="supplierItemInfoManagement_add" onsubmit="add()">
                                    <div id="addItemPricingForm" hidden>
                                        <div class="row">
                                            <div class="form-group">
                                                <div class="col-md-3" style="padding-left: 30px"><br>
                                                    <table>
                                                        <tr>
                                                            <td>
                                                                Supplier ID:&nbsp;
                                                            </td>
                                                            <td>
                                                                <select class="form-control" name="supplierId"> 
                                                                    <%
                                                                        List<SupplierEntity> listOfSuppliers = (List<SupplierEntity>) session.getAttribute("listOfSuppliers");
                                                                        for(SupplierEntity s : listOfSuppliers){
                                                                            out.print("<option value=\"" + s.getId() + "\">ID " + s.getId() + ": " + s.getSupplierName() + "</option>");
                                                                        }
                                                                    %>
                                                                </select>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                SKU:&nbsp;
                                                            </td>
                                                            <td>
                                                                <select class="form-control" name="sku"> 
                                                                    <%                                                                        List<String> listOfSKUs = (List<String>) session.getAttribute("listOfSKUs");
                                                                        for (String s : listOfSKUs) {
                                                                            out.print("<option value=\"" + s + "\">" + s + "</option>");
                                                                        }
                                                                    %>
                                                                </select>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                Cost Price:&nbsp;
                                                            </td>
                                                            <td>
                                                                <input type="number" step="any" class="form-control" name="costPrice" required/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                Lot Size:&nbsp;
                                                            </td>
                                                            <td>
                                                                <input type="number" class="form-control" name="lotSize" required/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td>
                                                                Lead Time:&nbsp;
                                                            </td>
                                                            <td>
                                                                <input type="number" class="form-control" name="leadTime" required/>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                    <input class="btn btn-primary" name="btnAdd" type="submit" value="Add" />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
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

            $(".btnAdd").click(function () {
                $("html, body").animate({scrollTop: $(document).height()}, "slow");
                $("#addItemPricingForm").show("slow", function () {
                });
            });

        </script>

    </body>

</html>