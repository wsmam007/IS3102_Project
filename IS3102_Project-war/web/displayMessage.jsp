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