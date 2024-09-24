<%@ page import="com.example.lab3databaseinteractionpractice.Models.Trader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <form method="post" action="sellers-servlet">
                <input type="hidden" value="${trader.id}" name="id">
                <input type="hidden" value="${method}" name="method">

                <div class="mb-3">
                    <label class="form-label" for="traderName">Name</label>
                    <input type="text" class="form-control" id="traderName"
                           value="${trader.name}" name="traderName" required>
                </div>

                <div class="mb-3">
                    <label class="form-label" for="contactPhone">Contact Phone</label>
                    <input type="text" class="form-control" id="contactPhone"
                           value="${trader.contactPhone}" name="contactPhone">
                </div>

                <div class="mb-3">
                    <label class="form-label" for="contactEmail">Contact Email</label>
                    <input type="text" class="form-control" id="contactEmail"
                           value="${trader.contactEmail}" name="contactEmail">
                </div>

                <button type="submit" class="btn btn-outline-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
