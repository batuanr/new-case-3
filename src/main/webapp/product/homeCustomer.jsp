<%--
  Created by IntelliJ IDEA.
  User: HI
  Date: 11/17/2021
  Time: 9:58 PM
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
            </ul>

            <div class="text-end">
                <a href="/customers?action=info&email=${email}">
                    <button type="button" class="btn btn-outline-light me-2">Thông tin cá nhân</button>
                </a>
                <a href="/customers?action=order&email=${email}">
                    <button type="button" class="btn btn-outline-light me-2">Giỏ hàng</button>
                </a>
            </div>

        </div>
    </div>
</header>
<div>

    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-indicators">
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                    aria-label="Slide 2"></button>
            <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                    aria-label="Slide 3"></button>
        </div>
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="https://mcdn.coolmate.me/uploads/November2021/1092x466_(4).jpg" class="d-block w-100"
                     alt="...">
            </div>
            <div class="carousel-item">
                <img src="https://mcdn.coolmate.me/uploads/October2021/BANNER_THANKYOU-03_copy.jpg"
                     class="d-block w-100" alt="...">

            </div>
            <div class="carousel-item">
                <img src="https://mcdn.coolmate.me/uploads/November2021/1092x466_(6).jpg" class="d-block w-100"
                     alt="...">
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
                data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
</body>
</html>
