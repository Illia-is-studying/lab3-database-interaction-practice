<%@ page import="java.util.List" %><%
    String headersAttr = request.getParameter("headersAttr");
    String rowsAttr = request.getParameter("rowsAttr");

    List<String> headers = (List<String>) request.getAttribute(headersAttr);
    List<List<String>> rows = (List<List<String>>) request.getAttribute(rowsAttr);
%>
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