<%@page import="EntityManager.SaleForecastEntity"%>
<%@page import="EntityManager.ProductGroupEntity"%>
<%@page import="HelperClasses.StoreHelper"%>
<%@page import="EntityManager.MonthScheduleEntity"%>
<%@page import="EntityManager.StoreEntity"%>
<%@page import="EntityManager.RegionalOfficeEntity"%>
<%@page import="HelperClasses.MessageHelper"%>
<%@page import="java.text.Format"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="EntityManager.StaffEntity"%>
<%@page import="EntityManager.RoleEntity"%>
<%@page import="java.util.List"%>
<html lang="en">
    <jsp:include page="../header2.html" />
    <body>        

        <style>
            select {
                max-width: 300px;
            }
        </style>

        <div id="wrapper">
            <jsp:include page="../menu1.jsp" />
            <div id="page-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">Sales And Operations Planning</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-pencil"></i>  <a href="../SaleAndOperationPlanning_Servlet/sop_index_GET">Sales And Operations Planning</a>
                                </li>   
                                <li>
                                    <i class="icon icon-calendar"></i>  <a href="../SaleAndOperationPlanning_Servlet/sop_schedule_GET">Schedule</a>
                                </li>
                                <li>
                                    <i class="icon icon-list"></i>  <a href="../SaleAndOperationPlanning_Servlet/sop_main_GET">Dashboard</a>
                                </li>
                                <li>
                                    <i class="icon icon-edit"></i>  <a href="#">Create Sales And Operations Plan</a>
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
                                    <div class="row">                             
                                        <div class="col-lg-3">
                                            <%  StoreHelper storeHelper = (StoreHelper) request.getAttribute("storeHelper");%>
                                            <h4><b> Store:  </b><%= storeHelper.store.getName()%></h4>
                                        </div>                                                
                                        <div class="col-lg-3">
                                            <% MonthScheduleEntity schedule = (MonthScheduleEntity) request.getAttribute("schedule");%>
                                            <h4><b> Period: </b><%= schedule.getYear()%> - <%= schedule.getMonth()%> </h4>
                                        </div>                                      
                                        <div class="col-lg-3">
                                            <% ProductGroupEntity productGroup = (ProductGroupEntity) request.getAttribute("productGroup");%>
                                            <h4><b> Product Group:  </b><%= productGroup.getName()%></h4>
                                        </div>
                                        <div class="col-lg-3">                                            
                                            <h4><b> Lot Size:  </b><%= productGroup.getLotSize()%></h4>
                                        </div>
                                    </div>                                        
                                </div>
                                <div class="panel-body">

                                    <form action="../SaleAndOperationPlanning_Servlet/sop_create_POST">
                                        <% SaleForecastEntity saleForecast = (SaleForecastEntity) request.getAttribute("saleForecast");%>
                                        <div class="form-group">
                                            <label>Sales Forecast</label>
                                            <input id="input_saleForecast" type="number" class="form-control" name="saleForecast" value="<%=saleForecast.getQuantity()%>" readonly>
                                        </div>

                                        <div class="form-group">
                                            <label>Production Plan</label>
                                            <input id="input_productionPlan" type="number" class="form-control" name="productionPlan" readonly>
                                        </div>

                                        <div class="form-group">
                                            <label>Current Inventory Level</label>
                                            <% Integer currentInventory = (Integer) request.getAttribute("currentInventoryLevel");%>
                                            <input id="input_currentInventory" type="number" class="form-control" name="currentInventory" value="<%= currentInventory%>" readonly>
                                        </div>

                                        <div class="form-group">
                                            <label>Target Inventory Level</label>
                                            <input id="input_targetInventoty" type="number" class="form-control" name="targetInventory" required>
                                        </div>

                                        <input type="submit" class="btn btn-primary" value="Submit">

                                    </form>

                                </div>                               

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

        <script>
            $('#input_targetInventoty').change(function () {
                var lotSize = <%= productGroup.getLotSize()%>;
                var targetInventoty = parseInt($('#input_targetInventoty').val());
                var saleForecast = parseInt($('#input_saleForecast').val());
                var currentInventory = parseInt($('#input_currentInventory').val());

                if (targetInventoty < currentInventory - saleForecast) {
                    targetInventoty = currentInventory - saleForecast;
                    $('#input_targetInventoty').val(targetInventoty);
                    alert("Target Inventory Level cannot be less than ( current inventory level - sales forecast ).");
                }
                if (targetInventoty < 0) {
                    targetInventoty = 0;
                    $('#input_targetInventoty').val(0);
                    alert("Target Inventory Level cannot be less than zero.");
                }

                var productionPlan = saleForecast - currentInventory + targetInventoty;
                if (productionPlan % lotSize !== 0) {
                    productionPlan = ( Math.floor(productionPlan / lotSize) + 1) * lotSize;
                } 
                $('#input_productionPlan').val(productionPlan);
                $('#input_targetInventoty').val(productionPlan + currentInventory - saleForecast);
            });
        </script>

        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>
            $(document).ready(function () {
                $('#dataTables-example').dataTable();
            }
            );
        </script>
    </body>
</html>
