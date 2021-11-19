<html lang="en">

<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <title>Product Detail</title>
    <link href="https://fonts.googleapis.com/css?family=Bentham|Playfair+Display|Raleway:400,500|Suranna|Trocchi" rel="stylesheet">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<style>

    body {
        background-color: #fdf1ec;
    }

    .wrapper {
        height: 420px;
        width: 654px;
        margin: 50px auto;
        border-radius: 7px 7px 7px 7px;
        /* VIA CSS MATIC https://goo.gl/cIbnS */
        -webkit-box-shadow: 0px 14px 32px 0px rgba(0, 0, 0, 0.15);
        -moz-box-shadow: 0px 14px 32px 0px rgba(0, 0, 0, 0.15);
        box-shadow: 0px 14px 32px 0px rgba(0, 0, 0, 0.15);
    }

    .product-img {
        float: left;
        height: 470px;
        width: 327px;
    }

    .product-img img {
        border-radius: 7px 0 0 7px;
    }

    .product-info {
        float: left;
        height: 520px;
        width: 327px;
        border-radius: 0 7px 10px 7px;
        background-color: #ffffff;
    }

    .product-text {
        height: 300px;
        width: 327px;
    }

    .product-text h1 {
        text-align: center;
        padding-top: 52px;
        font-size: 34px;
        color: #474747;
    }

    .product-text h1,
    .product-price-btn p {
        font-family: 'Bentham', serif;
    }

    .product-text h2 {
        text-align: center;
        font-size: 13px;
        font-family: 'Raleway', sans-serif;
        font-weight: 400;
        text-transform: uppercase;
        color: #d2d2d2;
        letter-spacing: 0.2em;
    }

    .product-text p {
        height: 125px;
        margin: 0 0 0 38px;
        font-family: 'Playfair Display', serif;
        color: #8d8d8d;
        line-height: 1.7em;
        font-size: 15px;
        font-weight: lighter;
        overflow: hidden;
    }

    .product-price-btn {
        height: 103px;
        width: 327px;
        margin-top: 17px;
        position: relative;
    }

    .product-price-btn p {
        display: inline-block;
        position: absolute;
        top: -13px;
        height: 50px;
        font-family: 'Trocchi', serif;
        margin: 0 0 0 38px;
        font-size: 28px;
        font-weight: lighter;
        color: #474747;
    }

    span {
        display: inline-block;
        height: 50px;
        font-family: 'Suranna', serif;
        font-size: 34px;
    }

    .product-price-btn button {
        float: right;
        display: inline-block;
        height: 50px;
        width: 176px;
        margin: 0 40px 0 16px;
        box-sizing: border-box;
        border: transparent;
        border-radius: 60px;
        font-family: 'Raleway', sans-serif;
        font-size: 14px;
        font-weight: 500;
        text-transform: uppercase;
        letter-spacing: 0.2em;
        color: #ffffff;
        background-color: #9cebd5;
        cursor: pointer;
        outline: none;
    }

    .product-price-btn button:hover {
        background-color: #79b0a1;
    }
</style>
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
<div class="wrapper">
    <div class="product-img">
        <img src="${product.url}" height="470" width="327">
    </div>
    <div class="product-info">
        <div class="product-text">
            <h1>${product.name}</h1>
            <h2>by Group Ke Huy Diet Module3</h2>
            <p><span>${product.price}</span>$</p>
        </div>
        <form action="/addOrder">
            <div style="text-align: center">
                <select name="size" id="size">
                    <c:forEach items="${sizeList}" var="s" >
                        <option value="${s.id}">${s.size}</option>
                    </c:forEach>
                </select>
            </div>
            <div  style="text-align: center">
                <label>Quantity</label>
                <input type="number" name="quantity">
                <input type="text" name="email" value="${email}" hidden>
                <input type="text" name="id" value="${product.id}" hidden>

            </div>
            <div class="product-price-btn">
                <button type="submit">Them Vao Gio Hang</button>

            </div>
        </form>

    </div>
</div>

</body>

</html>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

