<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%
    List<String> headers = (List<String>) request.getAttribute("headersAttr");
    List<List<String>> rows = (List<List<String>>) request.getAttribute("rowsAttr");
%>
<html>
<head>
    <title>Cars</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-9 mx-auto">
            <h1>${tableName}</h1>
            <div class="mb-3">
                <a href="${servletName}?method=post" class="btn btn-primary">Create New</a>
            </div>
            <table class="table table-dark table-striped">
                <thead>
                <tr>
                    <th>Actions</th>
                    <%
                        for (String header : headers) {
                    %>
                    <th scope="col"><%= header %>
                    </th>
                    <%
                        }
                    %>
                </tr>
                </thead>
                <tbody>
                <%
                    for (List<String> row : rows) {
                        String id = row.get(0);
                %>
                <tr>
                    <td>
                        <a href="${servletName}?id=<%=id%>&method=put" class="btn btn-outline-warning">Edit</a>
                        <span> | </span>
                        <a href="${servletName}?id=<%=id%>&method=delete" class="btn btn-outline-danger">Delete</a>
                    </td>
                    <%
                        for (String cell : row) {
                    %>
                    <td>
                        <%= cell %>
                    </td>
                    <%
                        }
                    %>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>