<%@page import="EntityManager.ProductGroupLineItemEntity"%>
<%@page import="EntityManager.ProductGroupEntity"%>
<%@page import="java.util.List"%>
<%
    List<ProductGroupEntity> productGroups = (List<ProductGroupEntity>) (session.getAttribute("productGroups"));
    String id = request.getParameter("id");
    String lineItemId = request.getParameter("lineItemId");
    if (productGroups == null || id == null || lineItemId == null) {
        response.sendRedirect("../ProductGroupManagement_Servlet");
    } else {
        ProductGroupEntity productGroup = new ProductGroupEntity();
        for (int i = 0; i < productGroups.size(); i++) {
            if (productGroups.get(i).getId() == Integer.parseInt(id)) {
                productGroup = productGroups.get(i);
            }
        }

        List<ProductGroupLineItemEntity> lineItems = productGroup.getLineItemList();
        ProductGroupLineItemEntity lineItem = new ProductGroupLineItemEntity();
        for (int i = 0; i < lineItems.size(); i++) {
            if (lineItems.get(i).getId() == Integer.parseInt(id)) {
                lineItem = lineItems.get(i);
            }
        }

%>
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
                                Update Line Item
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-exchange"></i> <a href="productGroupManagement.jsp">Product Group Management</a>
                                </li>
                                <li>
                                    <i class="icon icon-exchange"></i> <a href="productGroupManagement_Update.jsp?id=<%=productGroup.getId()%>">Purchase Order ID: <%=productGroup.getId()%></a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Update Line Item
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
                                    <h3 class="panel-title"> Product Group ID: <%=productGroup.getId()%> - Add Line Item </h3>
                                </div>
                                <div class="panel-body">
                                    <form role="form" action="../ProductGroupLineItemManagement_AddServlet">
                                        <div class="form-group">
                                            <label>SKU</label>
                                            <input class="form-control" name="sku" type="text" value="<%=lineItem.getFurniture().getSKU()%>" required="true">
                                        </div>
                                        <div class="form-group">
                                            <label>Percent</label>
                                            <input class="form-control" name="percent" type="number" required="true" value="<%=lineItem.getPercent()%>" >
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" value="Add Line Item" class="btn btn-lg btn-primary btn-block">
                                        </div>  
                                        <input type="hidden" value="<%=productGroup.getId()%>" name="id">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /#page-wrapper -->
            </div>
            <!-- /#container fluid -->
        </div>
        <!-- /#wrapper -->
    </body>
</html>
<%}%>