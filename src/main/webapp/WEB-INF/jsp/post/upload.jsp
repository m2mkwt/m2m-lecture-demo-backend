<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head ></head>
<body>
<head ></head>
<!--사진 업로드페이지 중앙배치-->
<main class="uploadContainer">
    <!--사진업로드 박스-->
    <section class="upload">

        <!--사진업로드 로고-->
        <div class="upload-top">
            <a href="/user/story.html" class="">
                <img src="/img/logo_text.png" alt="">
            </a>
            <p></p>
            <h6>사진 업로드</h6>
        </div>
        <!--사진업로드 로고 end-->

        <!--사진업로드 Form-->
        <form class="upload-form" action="/post/upload" method="post" enctype="multipart/form-data">
            <input  type="file" name="uploadImgUrl" id="uploadImgUrl" onchange="imageChoose(this.files)" name=filename accept=".gif, .jpg, .png" multiple>
            <div class="upload-img">
                <img src="/img/default_profile.jpg" alt="" id="imageUploadPreview" />
            </div>

            <!--사진설명 + 업로드버튼-->
            <div class="upload-form-detail">
                <textarea class="input_upload" name="text"  rows="5"> </textarea>
                <input type=submit value=업로드>
            </div>
            <!--사진설명end-->

        </form>
        <!--사진업로드 Form-->
    </section>
    <!--사진업로드 박스 end-->
</main>
<br/><br/>
<footer></footer>
</body>
</html>