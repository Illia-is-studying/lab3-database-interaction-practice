<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Map<Integer, String> sellersMap = (Map<Integer, String>) request.getAttribute("sellersMap");
    Map<Integer, String> buyersMap = (Map<Integer, String>) request.getAttribute("buyersMap");
%>
<html>
<head>
    <title>Sales</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-2">
            <div class="mb-3">
                <a href="index.jsp" class="btn btn-secondary">Go To Initial</a>
            </div>
            <form method="get" action="${servletName}">
                <div class="mb-3">
                    <label class="form-label" for="saleDate">Sale Date</label>
                    <input type="date" class="form-control" id="saleDate" name="saleDate">
                </div>

                <div class="mb-3 p-2 border border-2 border-info-subtle">
                    <div class="mb-3">
                        <label class="form-label" for="saleLowerRangeDate">Lower Range Date</label>
                        <input type="date" class="form-control" id="saleLowerRangeDate" name="saleLowerRangeDate">
                    </div>
                    <label class="form-label" for="saleUpperRangeDate">Upper Range Date</label>
                    <input type="date" class="form-control" id="saleUpperRangeDate" name="saleUpperRangeDate">
                </div>

                <div class="mb-3">
                    <label class="form-label" for="sellerID">Seller Name</label>
                    <select class="form-select" id="sellerID" name="sellerID">
                        <option value="all">All</option>
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
                        <option value="all">All</option>
                        <%for (Map.Entry<Integer, String> entry : buyersMap.entrySet()) {%>
                        <option value="<%=entry.getKey()%>">
                            <%=entry.getValue()%>
                        </option>
                        <%}%>
                    </select>
                </div>

                <button type="submit" class="btn btn-outline-success">Filter</button>
            </form>
        </div>
        <div class="col-9 mx-auto">
            <h1>${tableName}</h1>
            <div class="mb-3">
                <a href="${servletName}?method=post" class="btn btn-primary">Create New</a>
            </div>
            <jsp:include page="table-with-buttons.jsp">
                <jsp:param name="headersAttr" value="headersAttr"/>
                <jsp:param name="rowsAttr" value="rowsAttr"/>
                <jsp:param name="servletName" value="servletName"/>
            </jsp:include>
        </div>
    </div>
</div>
</body>
</html>