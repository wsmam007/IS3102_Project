<%@page import="EntityManager.StaffEntity"%>
<%@page import="EntityManager.PickRequestEntity"%>
<%@ page import="java.io.*,java.util.*" %>
<%
    StaffEntity staff = (StaffEntity) (session.getAttribute("staffEntity"));
    if (staff == null) {
%>
<jsp:forward page="../A1/staffLogin.jsp?errMsg=Session Expired." />
<% }%>
<html>
    <head>
        <jsp:include page="../header1.html" />
        <meta http-equiv="refresh" content="3; url=../PickerRefreshJob_Servlet">
    </head>
    <body class="dark">


        <div role="main" class="main">

            <div class="header-container">
                <div class="row" style="background-color : rgb(153, 0, 0); margin-bottom: 50px" >
                    <div class="col-md-4 col-md-offset-4">  
                        <img class="center-block img-responsive"  src="../img/logo-label.png" style="margin-top: 20px; margin-bottom: 20px;">
                    </div>
                </div>
            </div>


            <div class="container">
                <div class="row">

                    <div id="dataTables-example_wrapper" class="dataTables_wrapper form-inline" role="grid">
                        <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                            <thead>
                                <tr>
                                    <th>Queue No.</th>
                                    <th>Pick Status</th>
                                    <th>Picker</th>
                                    <th>Date/Time</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<PickRequestEntity> pickRequests = (List<PickRequestEntity>) (session.getAttribute("pickRequests"));
                                    if (pickRequests != null) {
                                        for (int i = 0; i < pickRequests.size(); i++) {
                                %>
                                <tr>
                                    <td>
                                        <%= pickRequests.get(i).getQueueNo()%>
                                    </td>
                                    <td>
                                        <%=pickRequests.get(i).getPickStatus()%>
                                    </td>
                                    <td>
                                        <%=pickRequests.get(i).getPicker().getPicker().getName()%>
                                    </td>
                                    <td>
                                        <%=pickRequests.get(i).getDateSubmitted()%>
                                    </td>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>
                    <hr class="tall">
                </div>
            </div>
        </div>
    </body>
</html>
