<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String errorMessage = (String) request.getSession().getAttribute("errorMessage");
    request.getSession().setAttribute("errorMessage", null);
%>
<html>
<head>
    <title>Table</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-9 mx-auto">
            <%if (errorMessage != null && !errorMessage.isEmpty()) {%>
            <div class="mb-3">
                <div class="alert alert-primary d-flex align-items-center" role="alert">
                    <div>
                        <%=errorMessage%>
                    </div>
                </div>
            </div>
            <%}%>
            <h1>${tableName}</h1>
            <div class="mb-3">
                <a href="${servletName}?method=post" class="btn btn-primary">Create New</a>
                <a href="index.jsp" class="btn btn-secondary">Go To Initial</a>
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