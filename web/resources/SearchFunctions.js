/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var req;
function searchText() {
    var search = document.getElementById("search").value;
    if (window.xmlHttpRequest) {
        req = new xmlHttpRequest();
    } else if (window.ActiveXObject) {
        req = new ActiveXObject("Microsoft.XMLHttp");
    }
    var url = "/SearchServlet?search=" + search;
    req.open("get", url, true);
    req.onreadystatechange = callback;
    req.send(null);
}
function callback() {
    if (req.readyState == 4) {
        var result = document.getElementById('result');
        result.innerHTML = '';
        var respText = req.responseText;
        alert(respText);
        if ((respText.length) != 0) {
            document.getElementById('result').style.display =
                    'block';
            var str = req.responseText.split("\n");
            var items;
            for (i = 0; i < str.length - 1; i++) {
                items = '<div onclick="setText(this.innerHTML);" ';
                items += '>' + str[i] + '</div>';
                result.innerHTML += items;
            }
        } else
        {
            document.getElementById('result').style.display = 'none';
        }
    }
}
function focusIn() {
    document.getElementById("search").focus( );
}
function setText(value) {
    document.getElementById('search').value = value;
    document.getElementById('result').style.display = 'none';
    document.getElementById('result').innerHTML = '';
}


