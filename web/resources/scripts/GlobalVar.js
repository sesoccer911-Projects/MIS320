/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*jslint sub: true, maxerr: 50, indent: 4, browser: true */

(function (global) {
    document.getElementById("Login").addEventListener("click", function () {
        global.localStorage.setItem("mySharedData", document.getElementById("username").value);
    }, false);
}(window));
/*jslint sub: true, maxerr: 50, indent: 4, browser: true */

(function (global) {
    document.getElementById("output").value = global.localStorage.getItem("mySharedData");
}(window));