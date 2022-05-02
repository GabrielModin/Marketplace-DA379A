
let url = "http://localhost:8080";

$(document).ready(function(){
    $("#header").load("header.html");
});

function addProductToProductTable(jsonObject) {
    let productAttributes = [];
    productAttributes[0] = jsonObject.id;
    productAttributes[1] = jsonObject.name;
    productAttributes[2] = jsonObject.price;
    productAttributes[3] = jsonObject.date;
    productAttributes[4] = jsonObject.type;
    productAttributes[5] = jsonObject.color;
    productAttributes[6] = jsonObject.condition;
    productAttributes[7] = jsonObject.status;
    productAttributes[8] = jsonObject.seller;

    var productsTable = document.getElementById("products");

    var htmlString = "<tr>" +
        "<tbody id = temp>";
    for (let i = 0; i < productAttributes.length; i++) {
        htmlString += "<td>"+productAttributes[i]+"</td>"
    }
    htmlString +="<td><input type=\"button\" value=\"buy\" onclick=\"addItemToCart("+productAttributes[0]+")\"></td>"
    htmlString += "</tr>" +
        "</tbody>";

    productsTable.innerHTML += htmlString;


}
function getProducts() {
    console.log("got products : ")
    $.ajax({url: url + "/products", success: function(result){
            console.log(result)
            let unparsedJson = result;
            const jsonObject = JSON.parse(unparsedJson);
            console.log(jsonObject);
            addProductToProductTable(jsonObject);
    }});
}

function addItemToCart(productID) {
    console.log("buying : " + productID);
}
/*
Object { id: "01", price: 20000000, date: "nov. 7, 3899", type: "electronics", color: "green", condition: "whack", status: "Not sold its broken what did you expect", seller: "you :eyesemoji:", name: "macbook" }

id: "01"
name: "macbook"
price: 20000000
date: "nov. 7, 3899"
type: "electronics"
color: "green"
condition: "whack"
status: "Not sold its broken what did you expect"
seller: "you :eyesemoji:"


 */