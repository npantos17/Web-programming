<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>

<button id="newPostBtn">New post</button>
<script>
    const btn = document.getElementById('newPostBtn');
    btn.addEventListener('click', e=>{
        fetch('http://localhost:8080/api/tag', {
            method: 'GET'
        }).then(response => {
            // return response.json();
        }).then(posts => {
            // init()
            // for (const post of posts) {
            //     // init()
            //     addPostElements(post)
            // }
        })

    })

</script>
</body>
</html>

