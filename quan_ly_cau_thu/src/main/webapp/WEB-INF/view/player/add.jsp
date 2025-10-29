<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Thêm mới cầu thủ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            padding: 40px;
        }

        .container {
            max-width: 400px;
            background: #fff;
            padding: 24px 28px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.08);
            margin: 0 auto;
        }

        h2 {
            text-align: center;
            margin-top: 0;
            margin-bottom: 20px;
            color: #333;
        }

        .form-group {
            margin-bottom: 14px;
        }

        label {
            display: block;
            font-size: 14px;
            font-weight: 600;
            color: #444;
            margin-bottom: 4px;
        }

        input, select {
            width: 100%;
            box-sizing: border-box;
            padding: 8px 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
            outline: none;
        }

        input:focus, select:focus {
            border-color: #4CAF50;
        }

        .actions {
            text-align: center;
            margin-top: 20px;
        }

        button, .back-link {
            display: inline-block;
            min-width: 100px;
            padding: 8px 12px;
            font-size: 14px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            text-decoration: none;
            cursor: pointer;
        }

        button:hover,
        .back-link:hover {
            background-color: #45a049;
        }

        .back-link {
            background-color: #777;
            margin-left: 8px;
        }

        .back-link:hover {
            background-color: #666;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Thêm mới cầu thủ</h2>

    <form:form modelAttribute="player" action="/players/add" method="post">

        <div class="form-group">
            <label for="id">ID:</label>
            <form:input path="id" id="id"/>
        </div>

        <div class="form-group">
            <label for="name">Tên cầu thủ:</label>
            <form:input path="name" id="name"/>
        </div>

        <div class="form-group">
            <label for="dob">Ngày sinh:</label>
            <form:input path="dob" id="dob" type="date"/>
        </div>

        <div class="form-group">
            <label for="experience">Kinh nghiệm:</label>
            <form:input path="experience" id="experience"/>
        </div>

        <div class="form-group">
            <label for="position">Vị trí:</label>
            <form:select path="position" id="position">
                <form:option value="" label="-- Chọn vị trí --"/>
                <form:option value="Thủ môn"/>
                <form:option value="Hậu vệ"/>
                <form:option value="Tiền vệ"/>
                <form:option value="Tiền đạo"/>
            </form:select>
        </div>

        <div class="form-group">
            <label for="avatar">Link ảnh đại diện:</label>
            <form:input path="avatar" id="avatar"/>
        </div>

        <div class="actions">
            <form:button>Lưu</form:button>
            <a href="/players" class="back-link">Quay lại</a>
        </div>

    </form:form>
</div>

</body>
</html>
