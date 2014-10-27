<%@page import="EntityManager.Item_CountryEntity"%>
<%@page import="EntityManager.MemberEntity"%>
<%@page import="EntityManager.FurnitureEntity"%>
<%@page import="java.util.List"%>
<%@page import="EntityManager.RetailProductEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="checkCountry.jsp" />
<%
    Boolean displayCartOption = false;
    MemberEntity member = (MemberEntity) (session.getAttribute("member"));
    if (member == null) {
        displayCartOption = false;
    } else {
        displayCartOption = true;
    }
%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />
    <body>
        <%
            List<RetailProductEntity> retailProducts = (List<RetailProductEntity>) (session.getAttribute("furnitures"));
            List<Item_CountryEntity> item_countryList = (List<Item_CountryEntity>) (session.getAttribute("item_countryList"));
        %>
        <script>
            function checkAll(source) {
                checkboxes = document.getElementsByName('delete');
                for (var i = 0, n = checkboxes.length; i < n; i++) {
                    checkboxes[i].checked = source.checked;
                }
            }
        </script>

        <div class="body">
            <jsp:include page="menu2.jsp" />
            <div class="body">
                <div role="main" class="main">
                    <section class="page-top">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <h2>Retail Products</h2>
                                </div>
                            </div>
                        </div>
                    </section>
                    <div class="container">

                        <hr class="tall">

                        <div class="row">
                            <div class="col-md-6">
                                <h2 class="shorter"><strong>Shop</strong></h2>
                                <p>Showing 1–12 of 25 results.</p>
                            </div>
                        </div>

                        <div class="row">

                            <ul class="products product-thumb-info-list" data-plugin-masonry>

                                <%

                                    List<RetailProductEntity> retailProducts = (List<RetailProductEntity>) (session.getAttribute("retailProducts"));

                                    try {
                                        if (retailProducts != null) {
                                            for (int i = 0; i < retailProducts.size(); i++) {
                                %>

                                <li class="col-md-3 col-sm-6 col-xs-12 product">
                                    <span class="product-thumb-info">
                                        <a href="shop-cart.html" class="add-to-cart-product">
                                            <span><i class="fa fa-shopping-cart"></i> Add to Cart</span>
                                        </a>
                                        <a href="shop-product-sidebar.html">
                                            <span class="product-thumb-info-image">
                                                <span class="product-thumb-info-act">
                                                    <span class="product-thumb-info-act-left"><em><%=retailProducts.get(i).getDescription()%></em></span>
                                                </span>
                                                <img alt="" class="img-responsive" src="../img/products/<%=i % 5%>.JPG">
                                            </span>
                                        </a>
                                        <span class="product-thumb-info-content">

                                            <h4><%=retailProducts.get(i).getName()%></h4>

                                            <span class="product-thumb-info-act-left"><em>Height: <%=retailProducts.get(i).getHeight()%></em></span><br/>
                                            <span class="product-thumb-info-act-left"><em>Length: <%=retailProducts.get(i).getLength()%></em></span><br/>
                                            <span class="product-thumb-info-act-left"><em>Width: <%=retailProducts.get(i).getWidth()%></em></span><br/>
                                            <%
                                                for (int j = 0; j < item_countryList.size(); j++) {
                                                    if (item_countryList.get(j).getItem().getId().equals(retailProducts.get(i).getId())) {
                                            %>
                                            <span class="product-thumb-info-act-left"><em>Price: $ <%=item_countryList.get(j).getRetailPrice()%></em></span>

                                            <%
                                                        break;
                                                    }
                                                }

                                            %>
                                            <br/>
                                            <a href="productDetails.jsp?sku=<%=furnitures.get(i).getSKU()%>"><span class="product-thumb-info-act-left"><em>More Details</em></span></a>

                                        </span>
                                    </span>
                                </li>
                                <%
                                            }
                                        }
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                    }
                                %>


                            </ul>
                        </div>

                        <div class="row">
                            <div class="col-md-12">
                                <ul class="pagination pull-right">
                                    <li><a href="#"><i class="fa fa-chevron-left"></i></a></li>
                                    <li class="active"><a href="#">1</a></li>
                                    <li><a href="#">2</a></li>
                                    <li><a href="#">3</a></li>
                                    <li><a href="#"><i class="fa fa-chevron-right"></i></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <jsp:include page="footer.html" />
        </div>
        <!-- Theme Initializer -->
        <script src="../js/theme.plugins.js"></script>
        <script src="../js/theme.js"></script>

        <!-- Current Page JS -->
        <script src="../vendor/rs-plugin/js/jquery.themepunch.tools.min.js"></script>
        <script src="../vendor/rs-plugin/js/jquery.themepunch.revolution.js"></script>
        <script src="../vendor/circle-flip-slideshow/js/jquery.flipshow.js"></script>
        <script src="../js/views/view.home.js"></script>   
    </div>
</body>
</html>
