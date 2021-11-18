<%--
  Created by IntelliJ IDEA.
  User: HI
  Date: 11/17/2021
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
<div>
    <header class="p-3 bg-dark text-white">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                        <use xlink:href="#bootstrap"></use>
                    </svg>
                </a>

                <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                    <li><a href="/customers?action=home&email=${email}" class="nav-link px-2 text-secondary">HOME</a></li>
                    <li>
                        <div class="dropdown">
                            <button class="btn text-white dropdown-toggle" type="button" id="dropdown"
                                    data-bs-toggle="dropdown" aria-expanded="false">Giới tính
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdown">
                                <li><a class="dropdown-item" href="/products?action=selectG&typeId=1&email=${email}">Nam</a></li>
                                <li><a class="dropdown-item" href="/products?action=selectG&typeId=2&email=${email}">Nữ</a></li>
                            </ul>
                        </div>
                    </li>
                    <li>
                        <div class="dropdown">
                            <button class="btn text-white dropdown-toggle" type="button" id="dropdown1"
                                    data-bs-toggle="dropdown" aria-expanded="false">Kiểu
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="dropdown1">
                                <li><a class="dropdown-item" href="/products?action=selectS&styleId=1&email=${email}">Mùa Đông</a></li>
                                <li><a class="dropdown-item" href="/products?action=selectS&styleId=2&email=${email}">Mùa hè</a></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="#" class="nav-link px-2 text-white">bảng quy đổi size</a></li>
                </ul>


                <div class="text-end">
                    <button type="button" class="btn btn-outline-light me-2">Xem giỏ hàng</button>
                </div>
            </div>
        </div>
    </header>
</div>

<div class="content1">
    <h1 align="center">THÔNG TIN CÁ NHÂN</h1>
    <br>
    <br>
    <br>
    <div align="center">
        <form action="/customers?action=info" method="post">
            <fieldset>
                <table>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" value="${customer.name}"></td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td><input type="text" name="phone" value="${customer.phone}"></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td>${customer.email}</td>
                        <td><input type="text" name="email" value="${customer.email}" hidden></td>
                    </tr>
                    <tr>
                        <td>Pass</td>
                        <td><input type="password" name="pass" value="${customer.pass}"></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Xác nhận"></td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
