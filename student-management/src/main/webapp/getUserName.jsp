<!DOCTYPE html>
<html>
<head>
  <title>Edit Student - Enter Username</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
  <div class="container mt-5">
    <h3 class="text-center mb-4">Enter Student Username to Edit Details</h3>
    <form action="editStudentForm" method="get" class="w-50 mx-auto">
      <div class="mb-3">
        <label for="username" class="form-label">Student Username</label>
        <input type="text" class="form-control" name="username" id="username" required>
      </div>
      <button type="submit" class="btn btn-primary">Fetch Details</button>
    </form>
  </div>
</body>
</html>

