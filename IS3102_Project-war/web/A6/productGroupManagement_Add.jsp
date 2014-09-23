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
                                Create Product Group
                            </h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-exchange"></i> <a href="productGroupManagement.jsp">Product Group Management</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-edit"></i> Create Product Group
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
                                    <h3 class="panel-title">  Product Group</h3>
                                </div>
                                <div class="panel-body">
                                    <form role="form" action="../ProductGroupManagement_AddServlet">
                                        <div class="form-group">
                                            <label>Product Group Name: </label>
                                            <input class="form-control" name="name" type="text" required="true"/>
                                        </div>
                                        <div class="form-group">
                                            <label>Work Hours: </label>
                                            <input class="form-control" name="workhours" type="number" required="true"/>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" value="Create Product Group" class="btn btn-lg btn-primary btn-block">
                                        </div>  
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->
    </body>
</html>