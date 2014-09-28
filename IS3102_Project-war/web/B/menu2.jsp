<%@page import="EntityManager.MemberEntity"%>
<%
    MemberEntity member = (MemberEntity) (session.getAttribute("member"));
    if (member == null) {
%>
<jsp:forward page="memberLogin.jsp?errMsg=Session Expired." />
<%
} else {
%>
<header id="header">
    <div class="container">
        <h1 class="logo">
            <a href="index.jsp">
                <img alt="Island Furniture" width="180" height="80" data-sticky-width="82" data-sticky-height="40" src="../img/logo.png">
            </a>
        </h1>
        <nav>
            <ul class="nav nav-pills nav-top">
                <li>
                    <a href="about-us.html"><i class="icon icon-map-marker"></i>Store Location</a>
                </li>
                <li>
                    <a href="contact-us.html"><i class="icon icon-shopping-cart"></i>My Shopping List</a>
                </li>
                <li>
                    <a href="memberProfile.jsp"><i class="icon icon-user"></i>Profile</a>
                </li>
                <li>
                    <a href="../ECommerce_LogoutServlet"><i class="icon icon-unlock-alt"></i>Logout</a>
                </li>
            </ul>
            <button class="btn btn-responsive-nav btn-inverse" data-toggle="collapse" data-target=".nav-main-collapse">
                <i class="icon icon-bars"></i>
            </button>
        </nav>
    </div>
    <div class="navbar-collapse nav-main-collapse collapse">
        <div class="container">
            <nav class="nav-main mega-menu">
                <ul class="nav nav-pills nav-main" id="mainMenu">
                    <li class="dropdown active">
                        <a href="shortcodes.html">All Products</a>
                    </li>
                    <li>
                        <a href="shortcodes.html">Promotion</a>
                    </li>
                    <li>
                        <a href="shortcodes.html">Living Room</a>
                    </li>
                    <li>
                        <a href="shortcodes.html">Kitchen</a>
                    </li>
                    <li>
                        <a href="shortcodes.html">Cooking</a>
                    </li>
                    <li>
                        <a href="shortcodes.html">Eating</a>
                    </li>
                    <li>
                        <a href="shortcodes.html">Bedroom</a>
                    </li>
                    <li>
                        <a href="shortcodes.html">Children</a>
                    </li>

                    <li class="dropdown mega-menu-item mega-menu-fullwidth">
                        <a class="dropdown-toggle" href="#">
                            All Departments
                            <i class="icon icon-angle-down"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <div class="mega-menu-content">
                                    <div class="row">
                                        <div class="col-md-3">
                                            <ul class="sub-menu">
                                                <li>
                                                    <ul class="sub-menu">
                                                        <li><a href="about-us.html"><i class="icon icon-map-marker"></i> Bathroom</a></li>
                                                        <li><a href="about-us.html"><i class="icon icon-linux"></i> Bedroom</a></li>
                                                        <li><a href="about-us.html"><i class="icon icon-male"></i> Children</a></li>
                                                        <li><a href="about-us.html"><i class="icon icon-cutlery"></i> Cooking</a></li>
                                                        <li><a href="about-us.html"><i class="icon icon-map-marker"></i> Decoration</a></li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="col-md-3">
                                            <ul class="sub-menu">
                                                <li>
                                                    <ul class="sub-menu">
                                                        <li><a href="about-us.html"><i class="icon icon-coffee"></i> Dining</a></li>
                                                        <li><a href="about-us.html"><i class="icon icon-cutlery"></i> Eating</a></li>
                                                        <li><a href="about-us.html"><i class="icon icon-angle-double-right"></i> Hallway</a></li>
                                                        <li><a href="about-us.html"><i class="icon icon-cutlery"></i> Kitchen</a></li>
                                                        <li><a href="about-us.html"><i class="icon icon-cutlery"></i> Laundry</a></li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="col-md-3">
                                            <ul class="sub-menu">
                                                <li>
                                                    <ul class="sub-menu">
                                                        <li><a href="feature-grid-system.html">Lighting</a></li>
                                                        <li><a href="feature-grid-system.html">Living room</a></li>
                                                        <li><a href="feature-grid-system.html">Outdoor</a></li>
                                                        <li><a href="feature-grid-system.html">Secondary storage</a></li>
                                                        <li><a href="feature-grid-system.html">Small Storage</a></li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </div>
                                        <div class="col-md-3">
                                            <ul class="sub-menu">
                                                <li>
                                                    <ul class="sub-menu">
                                                        <li><a href="feature-grid-system.html">Textiles</a></li>
                                                        <li><a href="feature-grid-system.html">Workspaces</a></li>
                                                        <li><a href="feature-grid-system.html">For business</a></li>
                                                        <li><a href="feature-grid-system.html">Summer</a></li>
                                                    </ul>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </li>

                </ul>
            </nav>
        </div>
    </div>
</header>
<%}%>