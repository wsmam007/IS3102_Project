<%@page import="EntityManager.MemberEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.StaffEntity"%>
<%@page import="java.text.DecimalFormat"%>
<html lang="en">
    <jsp:include page="../header2.html" />

    <body>

        <script>
            function updateStaff(id) {
                staffManagement.id.value = id;
                document.staffManagement.action = "../StaffManagement_UpdateStaffServlet";
                document.staffManagement.submit();
            }
            function removeStaff() {
                checkboxes = document.getElementsByName('delete');
                var numOfTicks = 0;
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    if (checkboxes[i].checked) {
                        numOfTicks++;
                    }
                }
                if (checkboxes.length == 0 || numOfTicks == 0) {
                    window.event.returnValue = true;
                    document.staffManagement.action = "../StaffManagement_StaffServlet";
                    document.staffManagement.submit();
                } else {
                    window.event.returnValue = true;
                    document.staffManagement.action = "../StaffManagement_RemoveStaffServlet";
                    document.staffManagement.submit();
                }
            }
            function addStaff() {
                window.event.returnValue = true;
                document.staffManagement.action = "staffManagement_add.jsp";
                document.staffManagement.submit();
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
                            <h1 class="page-header">Value Analysis</h1>
                            <ol class="breadcrumb">
                                <li>
                                    <i class="icon icon-users"></i> <a href="analytical.jsp">Analytical CRM</a>
                                </li>
                                <li class="active">
                                    <i class="icon icon-users"></i> Value Analysis
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
                                            out.println("List of Analytics");
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
                                <form name="staffManagement">
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <br>
                                            <%
                                                Double totalCustomerRevenue = (Double) session.getAttribute("totalCustomerRevenue");
                                                Double totalNonCustomerRevenue = (Double) session.getAttribute("totalNonCustomerRevenue");
                                                Integer cummulativeSpendingAgeGrp1 = (Integer) session.getAttribute("cummulativeSpendingAgeGrp1");
                                                Integer cummulativeSpendingAgeGrp2 = (Integer) session.getAttribute("cummulativeSpendingAgeGrp2");
                                                Integer cummulativeSpendingAgeGrp3 = (Integer) session.getAttribute("cummulativeSpendingAgeGrp3");
                                                Integer cummulativeSpendingAgeGrp4 = (Integer) session.getAttribute("cummulativeSpendingAgeGrp4");
                                                Integer averageCummulativeSpending = (Integer) session.getAttribute("averageCummulativeSpending");
                                                List<MemberEntity> members = (List<MemberEntity>) (session.getAttribute("members"));

                                                Integer numOfMembersInAgeGroup1 = (Integer) session.getAttribute("numOfMembersInAgeGroup1");
                                                Integer numOfMembersInAgeGroup2 = (Integer) session.getAttribute("numOfMembersInAgeGroup2");
                                                Integer numOfMembersInAgeGroup3 = (Integer) session.getAttribute("numOfMembersInAgeGroup3");
                                                Integer numOfMembersInAgeGroup4 = (Integer) session.getAttribute("numOfMembersInAgeGroup4");
                                            %>
                                            <!-- /.table-responsive -->
                                            <div class="row">
                                                <div class="col-md-6">

                                                    Total Number of Customers : <%=members.size()%> <br/>

                                                    <div class="panel panel-default">
                                                        <div class="panel-heading">
                                                            <h3 class="panel-title"><i class="fa fa-long-arrow-right fa-fw"></i> Total Revenue : <%=totalCustomerRevenue + totalNonCustomerRevenue%></h3>
                                                        </div>
                                                        <div class="panel-body">
                                                            <div id="morris-donut-chart"></div>
                                                            <div class="text-right">
                                                                <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                                                            </div>
                                                        </div>
                                                    </div>

                                                </div>
                                                <div class="col-md-6">
                                                    <div id="myfirstchart"></div>
                                                </div>


                                                <% Integer averageMemberRecency = (Integer) session.getAttribute("averageMemberRecency");
                                                    Integer averageMemberFrequency = (Integer) session.getAttribute("averageMemberFrequency");
                                                    Integer averageMemberMonetaryValue = (Integer) session.getAttribute("averageMemberMonetaryValue");
                                                %>



                                            </div>

                                        </div>



                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="tabs">
                                                    <ul class="nav nav-tabs">
                                                        <li class="active">
                                                            <a href="#rfm" data-toggle="tab"><i class="icon icon-user"></i> RFM</a>
                                                        </li>
                                                        <li>
                                                            <a href="#clv" data-toggle="tab">CLV</a>
                                                        </li>
                                                    </ul>
                                                    <div class="tab-content">
                                                        <div id="rfm" class="tab-pane active">

                                                            <h4>Recency, Frequency & Monetary Value</h4>
                                                            <div class="panel-body">
                                                                <div class="table-responsive">

                                                                    <div class="col-lg-12">
                                                                        <div class="row">

                                                                            <div class="pricing-table">
                                                                                <div class="col-md-4">
                                                                                    <div class="plan">
                                                                                        <h3>Recency<span><%=averageMemberRecency%></span></h3>
                                                                                        <ul>
                                                                                            <li><b>Average Recency</b> <%=averageMemberRecency%> days</li>
                                                                                            <li><b>25th Percentile</b> <%=averageMemberRecency / 2%></li>
                                                                                            <li><b>75th Percentile</b> <%=(averageMemberRecency / 2) + averageMemberRecency%></li>
                                                                                            <li><b>Recency</b> is the average last purchase per member away from today</li>
                                                                                        </ul>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-md-4">
                                                                                    <div class="plan">
                                                                                        <h3>Frequency<span><%=averageMemberFrequency%></span></h3>
                                                                                        <ul>
                                                                                            <li><b>Average Frequency</b> <%=averageMemberFrequency%> purchases</li>
                                                                                            <li><b>25th Percentile</b> <%=averageMemberFrequency / 2%></li>
                                                                                            <li><b>75th Percentile</b> <%=(averageMemberFrequency / 2) + averageMemberFrequency%></li>
                                                                                            <li><b>Frequency</b> is the number of purchases per member through their membership</li>
                                                                                        </ul>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-md-4">
                                                                                    <div class="plan">
                                                                                        <h3>Monetary<span><%=averageMemberMonetaryValue%></span></h3>
                                                                                        <ul>
                                                                                            <li><b>Average Monetary</b> <%=averageMemberMonetaryValue%></li>
                                                                                            <li><b>25th Percentile</b> <%=averageMemberMonetaryValue / 2%></li>
                                                                                            <li><b>75th Percentile</b> <%=(averageMemberMonetaryValue / 2) + averageMemberMonetaryValue%></li>
                                                                                            <li><b>Monetary</b> is the average total spending per member</li>
                                                                                        </ul>
                                                                                    </div>
                                                                                </div>
                                                                            </div>

                                                                        </div>

                                                                    </div>

                                                                    <br>
                                                                    <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" member="grid">
                                                                        <table class="table table-bordered" id="dataTables-example">
                                                                            <thead>
                                                                                <tr>
                                                                                    <th><input type="checkbox"onclick="checkAll(this)" /></th>
                                                                                    <th>Name</th>
                                                                                    <th>Recency</th>
                                                                                    <th>Frequency</th>
                                                                                    <th>Monetary Value</th>
                                                                                    <th>Action</th>
                                                                                </tr>
                                                                            </thead>
                                                                            <tbody>
                                                                                <%
                                                                                    List<Integer> memberRecencyValue = (List<Integer>) session.getAttribute("memberRecencyValue");
                                                                                    List<Integer> memberFrequencyValue = (List<Integer>) session.getAttribute("memberFrequencyValue");
                                                                                    List<Integer> memberMonetaryValue = (List<Integer>) session.getAttribute("memberMonetaryValue");
                                                                                    if (members != null) {
                                                                                        for (int i = 0; i < members.size(); i++) {
                                                                                            MemberEntity member = members.get(i);
                                                                                %>
                                                                                <tr>                   
                                                                                    <td>
                                                                                        <input type="checkbox" name="delete" value="<%=member.getId()%>" />
                                                                                    </td>
                                                                                    <td>
                                                                                        <%=member.getName()%>
                                                                                    </td>
                                                                                    <td <% if (memberRecencyValue.get(i) < (averageMemberRecency / 2)) {
                                                                                        %>bgcolor="#0066FF"<%
                                                                                        } else if (memberRecencyValue.get(i) > ((averageMemberRecency / 2) + averageMemberRecency)) {
                                                                                        %>
                                                                                        bgcolor="#FF0000"
                                                                                        <% } else { %>
                                                                                        bgcolor="#00FF00"
                                                                                        <%
                                                                                            }
                                                                                        %>
                                                                                        >
                                                                                        <%=memberRecencyValue.get(i)%>
                                                                                    </td>
                                                                                    <td
                                                                                        <% if (memberFrequencyValue.get(i) < (averageMemberFrequency / 2)) {
                                                                                        %>bgcolor="#FF0000"<%
                                                                                        } else if (memberFrequencyValue.get(i) > ((averageMemberFrequency / 2) + averageMemberFrequency)) {
                                                                                        %>
                                                                                        bgcolor="#0066FF"
                                                                                        <% } else { %>
                                                                                        bgcolor="#00FF00"
                                                                                        <%
                                                                                            }
                                                                                        %>
                                                                                        >
                                                                                        <%=memberFrequencyValue.get(i)%>
                                                                                    </td>
                                                                                    <td
                                                                                        <% if (memberMonetaryValue.get(i) < (averageMemberMonetaryValue / 2)) {
                                                                                        %>bgcolor="#FF0000"<%
                                                                                        } else if (memberMonetaryValue.get(i) > ((averageMemberMonetaryValue / 2) + averageMemberMonetaryValue)) {
                                                                                        %>
                                                                                        bgcolor="#0066FF"
                                                                                        <% } else { %>
                                                                                        bgcolor="#00FF00"
                                                                                        <%
                                                                                            }
                                                                                        %>
                                                                                        >
                                                                                        <%=memberMonetaryValue.get(i)%>
                                                                                    </td>

                                                                                    <td>
                                                                                        <input type="button" name="btnEdit" class="btn btn-primary btn-block" id="<%=member.getId()%>" value="View Sales Record" onclick="javascript:viewSalesRecord('<%=member.getId()%>')"/>
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
                                                                            <a href="#myModal" data-toggle="modal"><button class="btn btn-primary">Send Loyalty Points</button></a>
                                                                        </div>
                                                                    </div>
                                                                    <input type="hidden" name="id" value="">    
                                                                </div>

                                                            </div>
                                                        </div>
                                                        <div id="clv" class="tab-pane">
                                                            <h4>Customer Lifetime Value</h4>
                                                            <table class="table">
                                                                <tr>
                                                                    <td>

                                                                    </td>
                                                                    <td>
                                                                        Acquisition Year
                                                                    </td>
                                                                    <td>
                                                                        Second Year
                                                                    </td>
                                                                    <td>
                                                                        Third Year
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Customers
                                                                    </td>
                                                                    <td>
                                                                        <%=members.size()%>
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Retention Rate
                                                                    </td>
                                                                    <td>
                                                                        <%
                                                                            Double customerRetentionRate = (Double) session.getAttribute("customerRetentionRate");
                                                                            DecimalFormat df = new DecimalFormat("#.00"); 
                                                                        %>
                                                                        <% out.print(df.format(customerRetentionRate*100)); %>%
                                                                    </td>
                                                                    <td>
                                                                        
                                                                    </td>
                                                                    <td>
                                                                        Total Revenue
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Orders per Year
                                                                    </td>
                                                                    <td>
                                                                        Cost of Sales
                                                                    </td>
                                                                    <td>
                                                                        Acquisition/Mkt. Cost
                                                                    </td>
                                                                    <td>
                                                                        Marketing Costs
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Avg Order Size
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Total Revenue
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Costs
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Cost of Sales
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Acquisition/Mkt. Cost
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Marketing Costs
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Total Costs
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Gross Profit
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Discount Rate
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Net Present Value
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Cumulative NPV Profit
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td>
                                                                        Customer LTV
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                    <td>
                                                                        testing 321
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </div>
                                                </div>
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
                    <p id="messageBox">Staff will be removed. Are you sure?</p>
                </div>
                <div class="modal-footer">                        
                    <input class="btn btn-primary" name="btnRemove" type="submit" value="Confirm" onclick="removeStaff()"  />
                    <a class="btn btn-default" data-dismiss ="modal">Close</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Page-Level Demo Scripts - Tables - Use for reference -->
    <script>
        $(document).ready(function() {
            $('#dataTables-example').dataTable();
        });
        new Morris.Bar({
            // ID of the element in which to draw the chart.
            element: 'myfirstchart',
            // Chart data records -- each entry in this array corresponds to a point on
            // the chart.
            data: [
                {y: '18-25', a: <%=cummulativeSpendingAgeGrp1%>, b: <%=numOfMembersInAgeGroup1%>, c: 1},
                {y: '26-40', a: <%=cummulativeSpendingAgeGrp2%>, b: <%=numOfMembersInAgeGroup2%>, c: 2},
                {y: '41-55', a: <%=cummulativeSpendingAgeGrp3%>, b: <%=numOfMembersInAgeGroup2%>, c: 3},
                {y: '56-75', a: <%=cummulativeSpendingAgeGrp4%>, b: <%=numOfMembersInAgeGroup1%>, c: 4}
            ],
            xkey: 'y',
            ykeys: ['a', 'b', 'c'],
            labels: ['Total Cummulative Spending', 'Total Number Of Members', 'Age Group']
        });

        new Morris.Donut({
            element: 'morris-donut-chart',
            data: [{
                    label: "Member Sales",
                    value: <%=totalCustomerRevenue%>
                }, {
                    label: "Non-Member Sales",
                    value: <%=totalNonCustomerRevenue%>
                }],
            resize: true
        });

    </script>
</body>
</html>
