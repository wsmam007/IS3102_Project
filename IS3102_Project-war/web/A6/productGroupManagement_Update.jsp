<%@page import="EntityManager.ProductGroupLineItemEntity"%>
<%@page import="EntityManager.ProductGroupEntity"%>
<%@page import="java.util.List"%>
<%
    List<ProductGroupEntity> productGroups = (List<ProductGroupEntity>) (session.getAttribute("productGroups"));
    String id = request.getParameter("id");
    if (productGroups == null || id == null) {
        response.sendRedirect("../ProductGroupManagement_Servlet");
    } else {
        ProductGroupEntity productGroup = new ProductGroupEntity();
        for (int i = 0; i < productGroups.size(); i++) {
            if (productGroups.get(i).getId() == Integer.parseInt(id)) {
                productGroup = productGroups.get(i);
            }
        }

%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <body>
        <script>
            function addPGLineItem(id) {
                productGroupManagement.id.value = id;
                document.productGroupManagement.action = "productGroupManagement_AddLineItem.jsp";
                document.productGroupManagement.submit();
            }
            function updatePGLineItem(lineItemId) {
                productGroupManagement.lineItemId.value = lineItemId;
                document.productGroupManagement.action = "productGroupManagement_UpdateLineItem.jsp";
                document.productGroupManagement.submit();
            }
            function removePGLineItem() {
                var yes = confirm("Are you sure?!");
                if (yes == true) {
                    window.event.returnValue = true;
                    document.productGroupManagement.action = "../ProductGroupLineItemManagement_RemoveServlet";
                    document.productGroupManagement.submit();
                } else {
                    window.event.returnValue = false;
                }
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

                    <!-- Page Heading -->
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                Product Group
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-exchange"></i> <a href="productGroupManagement.jsp">Product Group Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Product Group - Update
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->

                    <jsp:include page="../displayMessage.jsp" />


                    <div class="row">
                        <div class="col-lg-6">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">  Product Group ID: <%=productGroup.getId()%></h3> 
                                </div>
                                <div class="panel-body">
                                    <form role="form" action="../ProductGroupManagement_UpdateServlet">
                                        <div class="form-group">
                                            <label>Product Group Name: </label>
                                            <input class="form-control" name="name" type="text" required="true" value="<%=productGroup.getProductGroupName()%>"/>
                                        </div>
                                        <div class="form-group">
                                            <label>Work Hours: </label>
                                            <input class="form-control" name="workhours" type="number" required="true" value="<%=productGroup.getWorkHours()%>"/>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" value="Update Product Group" class="btn btn-lg btn-primary btn-block">
                                        </div>  
                                        <input type="hidden" value="<%=productGroup.getId()%>" name="id">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h3 class="panel-title">Line item Management</h3>
                                </div>
                                <!-- /.panel-heading -->
                                <form name="productGroupManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <input  type="button" name="btnAddLineItem" class="btn btn-primary" value="Add Line Item" onclick="javascript:addPGLineItem('<%=productGroup.getId()%>')"/>
                                                        <input  class="btn btn-primary" name="btnRemove" type="submit" value="Remove Line Item" onclick="javascript:removePGLineItem()"  />
                                                    </div>
                                                </div>
                                                <br>
                                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                                    <thead>
                                                        <tr>
                                                            <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                            <th>SKU</th>
                                                            <th>Furniture Name</th>
                                                            <th>Percentage</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <%
                                                            List<ProductGroupLineItemEntity> lineItems = productGroup.getLineItemList();
                                                            if (lineItems != null) {
                                                                for (int i = 0; i < lineItems.size(); i++) {
                                                        %>
                                                        <tr>
                                                            <td>
                                                                <input type="checkbox" name="delete" value="<%=lineItems.get(i).getId()%>" />
                                                            </td>
                                                            <td>
                                                                <%=lineItems.get(i).getFurniture().getSKU()%>
                                                            </td>
                                                            <td>
                                                                <%=lineItems.get(i).getFurniture().getName()%>
                                                            </td>
                                                            <td>
                                                                <%=lineItems.get(i).getPercent()%>
                                                            </td>
                                                            <td>
                                                                <input type="button" name="btnEdit" class="btn btn-primary btn-block" value="Update" onclick="javascript:updatePGLineItem('<%=lineItems.get(i).getId()%>')"/>
                                                            </td>
                                                        </tr>
                                                        <%}
                                                            }%>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-12">
                                                <input  type="button" name="btnAddLineItem" class="btn btn-primary" value="Add Line Item" onclick="javascript:addPGLineItem('<%=productGroup.getId()%>')"/>
                                                <input type="submit" name="btnRemove" class="btn btn-primary"  value="Remove Line Item" onclick="javascript:removePGLineItem()"  />
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" value="<%=productGroup.getId()%>" name="id">
                                    <input type="hidden" name="lineItemId">
                                </form>
                            </div>
                        </div>
                    </div> 
                    <!-- /.row for line item management -->



                </div>
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
<%}%>