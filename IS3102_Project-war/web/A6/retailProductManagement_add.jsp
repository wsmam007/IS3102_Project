<%@page import="java.util.List"%>
<%@page import="EntityManager.RetailProductEntity"%>
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
                                Add Retail Product
                            </h1>
                            <ol class="breadcrumb">
                                <li class="active">
                                    <i class="icon icon-user"></i><a href="retailProductManagement.jsp"> Retail Product Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Add Retail Product
                                </li>
                            </ol>
                        </div>
                    </div>
                    <!-- /.row -->
                    <%
                        String errMsg = request.getParameter("errMsg");
                        if (errMsg == null || errMsg.equals("")) {
                            errMsg = "";
                        } else {
                    %>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="alert alert-danger">
                                <%=errMsg%>
                            </div>
                        </div>
                    </div>
                    <%}%>
                    <!-- /.warning -->
                    <div class="row">
                        <div class="col-lg-6">
                            <form role="form" action="../RetailProductManagement_AddRetailProductServlet">
                                <div class="form-group">
                                    <label>Name</label>
                                    <input class="form-control" name="name"  type="text" required="true">
                                </div>
                                <div class="form-group">
                                    <label>Category</label>
                                    <input class="form-control" required="true" type="text" name="category" >
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <input class="form-control" required="true" type="text" name="description">
                                </div>
                                <div class="form-group">
                                    <label>Image URL</label>
                                    <input class="form-control" type="text" required="true" name="imageURL" >
                                </div>
                                <div class="form-group">
                                    <label>SKU</label>
                                    <input class="form-control" type="text" required="true" name="SKU" >
                                </div>
                                <div class="form-group">
                                    <label>Length</label>
                                    <input class="form-control" type="text" required="true" name="length" >
                                </div>
                                <div class="form-group">
                                    <label>Width</label>
                                    <input class="form-control" type="text" required="true" name="width" >
                                </div>
                                <div class="form-group">
                                    <label>Height</label>
                                    <input class="form-control" type="text" required="true" name="height" >
                                </div>
                                <div class="form-group">
                                    <input type="submit" value="Add" class="btn btn-lg btn-primary btn-block">
                                </div>
                                <input type="hidden" value="A6/retailProductManagement_add.jsp" name="source">
                            </form>
                        </div>
                        <!-- /.row -->

                    </div>
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