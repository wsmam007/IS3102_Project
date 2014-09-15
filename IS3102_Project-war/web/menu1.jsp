<%@page import="java.util.ArrayList"%>
<%@page import="EntityManager.RoleEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.StaffEntity"%>
<%
    StaffEntity staffEntity = (StaffEntity) (session.getAttribute("staffEntity"));
    if (staffEntity == null) {
%>
<jsp:forward page="Staff/staffLogin.jsp?errMsg=Session Expired." />
<%
} else {
    List<RoleEntity> roles = staffEntity.getRoles();
    Long[] approvedRolesID;
    boolean roleCanView;
%>


<!-- Navigation -->
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="../A1/workspace.jsp">Island Furniture - Staff Portal</a>
    </div>
    <!-- Top Menu Items -->
    <ul class="nav navbar-right top-nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon icon-envelope"></i> <b class="caret"></b></a>
            <ul class="dropdown-menu message-dropdown">
                <li class="message-preview">
                    <a href="#">
                        <div class="media">
                            <div class="media-body">
                                <h5 class="media-heading"><strong><%=staffEntity.getName()%></strong></h5>
                                <p class="small text-muted"><i class="icon icon-clock-o"></i> Yesterday at 4:32 PM</p>
                                <p>Lorem ipsum dolor sit amet, consectetur...</p>
                            </div>
                        </div>
                    </a>
                </li>
                <li class="message-preview">
                    <a href="#">
                        <div class="media">
                            <div class="media-body">
                                <h5 class="media-heading"><strong>John Smith</strong></h5>
                                <p class="small text-muted"><i class="icon icon-clock-o"></i> Yesterday at 4:32 PM</p>
                                <p>Lorem ipsum dolor sit amet, consectetur...</p>
                            </div>
                        </div>
                    </a>
                </li>
                <li class="message-preview">
                    <a href="#">
                        <div class="media">
                            <div class="media-body">
                                <h5 class="media-heading"><strong>John Smith</strong></h5>
                                <p class="small text-muted"><i class="icon icon-clock-o"></i> Yesterday at 4:32 PM</p>
                                <p>Lorem ipsum dolor sit amet, consectetur...</p>
                            </div>
                        </div>
                    </a>
                </li>
                <li class="message-footer">
                    <a href="#">Read All New Messages</a>
                </li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon icon-bell"></i> <b class="caret"></b></a>
            <ul class="dropdown-menu alert-dropdown">
                <li>
                    <a href="#">Alert Name <span class="label label-default">Alert Badge</span></a>
                </li>
                <li>
                    <a href="#">Alert Name <span class="label label-primary">Alert Badge</span></a>
                </li>
                <li>
                    <a href="#">Alert Name <span class="label label-success">Alert Badge</span></a>
                </li>
                <li>
                    <a href="#">Alert Name <span class="label label-info">Alert Badge</span></a>
                </li>
                <li>
                    <a href="#">Alert Name <span class="label label-warning">Alert Badge</span></a>
                </li>
                <li>
                    <a href="#">Alert Name <span class="label label-danger">Alert Badge</span></a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="#">View All</a>
                </li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> <%=staffEntity.getName()%><b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li>
                    <a href="#"><i class="icon icon-user"></i> Profile</a>
                </li>
                <li>
                    <a href="#"><i class="icon icon-envelope"></i> Inbox</a>
                </li>
                <li>
                    <a href="#"><i class="icon icon-gear"></i> Settings</a>
                </li>
                <li class="divider"></li>
                <li>
                    <a href="../AccountManagement_LogoutServlet"><i class="icon icon-power-off"></i> Log Out</a>
                </li>
            </ul>
        </li>
    </ul>
    <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
    <div class="collapse navbar-collapse navbar-ex1-collapse">
        <ul class="nav navbar-nav side-nav">
            <%
                approvedRolesID = new Long[]{1L, 2L};
                roleCanView = false;
                for (RoleEntity roleEntity : roles) {
                    for (Long ID : approvedRolesID) {
                        if (roleEntity.getId().equals(ID)) {
                            roleCanView = true;
                            break;
                        }
                    }
                    if (roleCanView) {
                        break;
                    }
                }
                if (roleCanView) {
            %>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#commonInfrastructure">
                    <i class="icon icon-user"></i> Common Infrastructure <i class="icon icon-caret-down"></i>
                </a>
                <ul id="commonInfrastructure" class="collapse">
                    <li>
                        <a href="../A1/accountManagement.jsp">Account Management</a>
                    </li>
                    <li>
                        <a href="#">System Security</a>
                    </li>
                    <li>
                        <a href="../A1/workspace_BroadcastAnnouncement.jsp">Workspace</a>
                    </li>
                </ul>
            </li>
            <%}
                approvedRolesID = new Long[]{1L, 2L, 3L, 4L, 5L, 7L};
                roleCanView = false;
                for (RoleEntity roleEntity : roles) {
                    for (Long ID : approvedRolesID) {
                        if (roleEntity.getId().equals(ID)) {
                            roleCanView = true;
                            break;
                        }
                    }
                    if (roleCanView) {
                        break;
                    }
                }
                if (roleCanView) {
            %>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#MRP">
                    <i class="icon icon-book"></i> MRP <i class="icon icon-caret-down"></i>
                </a>
                <ul id="MRP" class="collapse">
                    <li>
                        <a href="#">Sales Forecast</a>
                    </li>
                    <li>
                        <a href="#">Sales and Operation Planning</a>
                    </li>
                    <li>
                        <a href="#">Production Plan Distribution</a>
                    </li>
                    <li>
                        <a href="#">Demand Management</a>
                    </li>
                    <li>
                        <a href="#">Manufacturing Requirement Planning</a>
                    </li>
                    <li>
                        <a href="#">Material Requirement Fulfilment</a>
                    </li>
                </ul>
            </li>
            <% }
                approvedRolesID = new Long[]{1L, 2L, 3L, 4L, 7L};
                roleCanView = false;
                for (RoleEntity roleEntity : roles) {
                    for (Long ID : approvedRolesID) {
                        if (roleEntity.getId().equals(ID)) {
                            roleCanView = true;
                            break;
                        }
                    }
                    if (roleCanView) {
                        break;
                    }
                }
                if (roleCanView) {
            %>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#SCM">
                    <i class="icon icon-home"></i> SCM <i class="icon icon-caret-down"></i>
                </a>
                <ul id="SCM" class="collapse">
                    <li>
                        <a href="../ManufacturingInventoryControl_MICServlet">Manufacturing's Inventory Control</a>
                    </li>
                    <li>
                        <a href="#">Retail Products and Raw Materials Purchasing</a>
                    </li>
                    <li>
                        <a href="../SupplierManagement_SupplierServlet">Supplier Management</a>
                    </li>
                    <li>
                        <a href="#">Inbound and Outbound Logistics</a>
                    </li>
                    <li>
                        <a href="../A3/manufacturingWarehouseManagement.jsp">Manufacturing's Warehouse Management</a>
                    </li>
                </ul>
            </li>
            <% }
                approvedRolesID = new Long[]{1L, 6L};
                roleCanView = false;
                for (RoleEntity roleEntity : roles) {
                    for (Long ID : approvedRolesID) {
                        if (roleEntity.getId().equals(ID)) {
                            roleCanView = true;
                            break;
                        }
                    }
                    if (roleCanView) {
                        break;
                    }
                }
                if (roleCanView) {
            %>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#pperationalCRM">
                    <i class="icon icon-cogs"></i> Operational CRM <i class="icon icon-caret-down"></i>
                </a>
                <ul id="pperationalCRM" class="collapse">
                    <li>
                        <a href="#">Loyalty & Rewards</a>
                    </li>
                    <li>
                        <a href="#">Customer Service</a>
                    </li>
                    <li>
                        <a href="#">Promotional Sales</a>
                    </li>
                    <li>
                        <a href="#">Product Retirement Campaign</a>
                    </li>
                    <li>
                        <a href="#"> Customer Information Management</a>
                    </li>
                </ul>
            </li>
            <% }
                approvedRolesID = new Long[]{1L, 2L, 6L};
                roleCanView = false;
                for (RoleEntity roleEntity : roles) {
                    for (Long ID : approvedRolesID) {
                        if (roleEntity.getId().equals(ID)) {
                            roleCanView = true;
                            break;
                        }
                    }
                    if (roleCanView) {
                        break;
                    }
                }
                if (roleCanView) {
            %>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#analyticalCRM">
                    <i class="icon icon-bar-chart-o"></i> Analytical CRM <i class="icon icon-caret-down"></i>
                </a>
                <ul id="analyticalCRM" class="collapse">
                    <li>
                        <a href="#">Customer Value Analysis</a>
                    </li>
                    <li>
                        <a href="#">Segmentation Marketing</a>
                    </li>
                    <li>
                        <a href="#">Product Retirement Campaign</a>
                    </li>
                </ul>
            </li>
            <% }
                approvedRolesID = new Long[]{1L};
                roleCanView = false;
                for (RoleEntity roleEntity : roles) {
                    for (Long ID : approvedRolesID) {
                        if (roleEntity.getId().equals(ID)) {
                            roleCanView = true;
                            break;
                        }
                    }
                    if (roleCanView) {
                        break;
                    }
                }
                if (roleCanView) {
            %>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#corporateCRM">
                    <i class="icon icon-briefcase"></i> Corporate CRM <i class="icon icon-caret-down"></i>
                </a>
                <ul id="corporateCRM" class="collapse">
                    <li>
                        <a href="../A6/facilityManagement.jsp">Facility Management</a>
                    </li>
                    <li>
                        <a href="../A6/itemManagement.jsp">Item Management</a>
                    </li>
                </ul>
            </li>
            <% }
            %>
            <li>
                <a href="javascript:;" data-toggle="collapse" data-target="#demo">
                    <i class="icon icon-arrows-v"></i> Sample <i class="icon icon-caret-down"></i>
                </a>
                <ul id="demo" class="collapse">
                    <li>
                        <a href="../template/sampleManagement.jsp">Sample Management</a>
                    </li>
                    <li>
                        <a href="../template/sampleManagement_update.jsp">Update Sample Management</a>
                    </li>
                </ul>

            </li>
        </ul>
    </div>
    <!-- /.navbar-collapse -->
</nav>

<%}%>