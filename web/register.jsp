<%-- 
    Document   : login
    Created on : Mar 30, 2017, 12:53:00 PM
    Author     : sgreen
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <title>Blockbuster User Registration</title>
    <head>
        <link rel="stylesheet" href="resources/css/main.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sign up Page</title>
    </head>
    <body>
        <form method="post" action="RegisterServlet"
              onsubmit="return checkform(this);">
            <div class="container">
                <label><b>First Name</b></label>
                <input type="text" placeholder="Enter First Name" name="fName" required>

                <label><b>Last Name</b></label>
                <input type="text" placeholder="Enter Last Name" name="lName" required>

                <label><b>Email</b></label>
                <input type="text" placeholder="Enter Email" name="email" required>

                <label><b>User ID</b></label>
                <input type="text" placeholder="Enter User ID" name="userID" required>

                <label><b>Password (Minimum 8 Characters Alphanumeric)</b></label>
                <input type="password" pattern="(?=.*\d)(?=.*[a-z]).{8,}" placeholder="Enter Password" name="password" required>
                <label><b>Store</b></label>
                <select name="storeID">
                    <option value="1">Store 1</option>
                    <option value="2">Store 2</option>
                </select>
                <p>By creating an account you agree to our <a href="#">Terms & Privacy</a>.</p>

                <!-- START CAPTCHA -->
                <br>
                <div class="capbox">

                    <div id="CaptchaDiv"></div>

                    <div class="capbox-inner">
                        Type the above number:<br>

                        <input type="hidden" id="txtCaptcha">
                        <input type="text" name="CaptchaInput" id="CaptchaInput" size="15"><br>

                    </div>
                </div>
                <br><br>
                <!-- END CAPTCHA -->
                <button type="submit" class="loginbtn">Sign Up</button>
            </div>
        </form>
        <div class="button-container">
            <form method="get" action="login.jsp">
                <div>
                    <button type="submit"class="cancelbtn">Cancel</button>
                </div>
            </form>
        </div>
        <script type="text/javascript">

            // Captcha Script

            function checkform(theform) {
                var why = "";

                if (theform.CaptchaInput.value == "") {
                    why += "- Please Enter CAPTCHA Code.\n";
                }
                if (theform.CaptchaInput.value != "") {
                    if (ValidCaptcha(theform.CaptchaInput.value) == false) {
                        why += "- The CAPTCHA Code Does Not Match.\n";
                    }
                }
                if (why != "") {
                    alert(why);
                    return false;
                }
            }

            var a = Math.ceil(Math.random() * 9) + '';
            var b = Math.ceil(Math.random() * 9) + '';
            var c = Math.ceil(Math.random() * 9) + '';
            var d = Math.ceil(Math.random() * 9) + '';
            var e = Math.ceil(Math.random() * 9) + '';

            var code = a + b + c + d + e;
            document.getElementById("txtCaptcha").value = code;
            document.getElementById("CaptchaDiv").innerHTML = code;

            // Validate input against the generated number
            function ValidCaptcha() {
                var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
                var str2 = removeSpaces(document.getElementById('CaptchaInput').value);
                if (str1 == str2) {
                    return true;
                } else {
                    return false;
                }
            }

            // Remove the spaces from the entered and generated code
            function removeSpaces(string) {
                return string.split(' ').join('');
            }
        </script>
    </body>
</html>
