<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQQU3eFhvwCSQe3iQzQAGkUKy1QZTb8dr7U8F9mNyxPcElElxVKVbm6DB" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background: white;
            border-radius: 10px;
            padding: 40px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        button {
            font-size: 15px;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h2 class="text-center mb-4">Login</h2>
    <form method="post" action="loginOK">
        <div class="mb-3">
            <label for="userid" class="form-label" style="margin-right: 12px;">User ID</label>
            <input type="text" class="form-control" id="userid" name="userid" placeholder="Enter your User ID">
        </div>

        <div class="mb-3">
            <label for="password" class="form-label" style="margin-right: 0px;">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Enter your Password">
        </div>
        <button type="submit" class="btn btn-primary w-100">Login</button>
    </form>
</div>
<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qvJW8SSZz3eI0U9mKm5MxLMmMHNn0H6htl4gHMIhvnLOuPFTkkZ5Gcz6UN60U7A+" crossorigin="anonymous"></script>
</body>
</html>
