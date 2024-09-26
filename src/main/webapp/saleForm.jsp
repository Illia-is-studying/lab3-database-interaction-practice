<%@ page import="com.example.lab3databaseinteractionpractice.Models.Sale" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<Integer, String> sellersMap = (Map<Integer, String>) request.getAttribute("sellersMap");
    Map<Integer, String> buyersMap = (Map<Integer, String>) request.getAttribute("buyersMap");
%>
<html>
<head>
    <title>${formName} Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">

<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-8 mx-auto">
            <h1>${formName} Form</h1>
            <form method="post" action="sales-servlet">
                <input type="hidden" value="${sale.saleID}" name="id">
                <input type="hidden" value="${method}" name="method">

                <div class="mb-3">
                    <label class="form-label" for="sellerID">Seller Name</label>
                    <select class="form-select" id="sellerID" name="sellerID">
                        <%for (Map.Entry<Integer, String> entry : sellersMap.entrySet()) {%>
                        <option value="<%=entry.getKey()%>">
                            <%=entry.getValue()%>
                        </option>
                        <%}%>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label" for="buyerID">Buyer Name</label>
                    <select class="form-select" id="buyerID" name="buyerID">
                        <%for (Map.Entry<Integer, String> entry : buyersMap.entrySet()) {%>
                        <option value="<%=entry.getKey()%>">
                            <%=entry.getValue()%>
                        </option>
                        <%}%>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-label" for="productName">Product Name</label>
                    <input type="text" class="form-control" id="productName"
                           value="${sale.productName}" name="productName">
                </div>

                <div class="mb-3">
                    <label class="form-label" for="sellingPrice">Selling Price</label>
                    <input type="number" class="form-control" id="sellingPrice"
                           value="${sale.sellingPrice}" name="sellingPrice">
                </div>

                <div class="mb-3">
                    <label class="form-label" for="saleDate">Sale Date</label>
                    <input type="date" class="form-control" id="saleDate"
                           value="${sale.saleDate}" name="saleDate">
                </div>

                <button type="submit" class="btn btn-outline-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
