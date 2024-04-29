async function getHotels() {
    try {
        var path = "http://localhost:8080/hotels";
        var response = await fetch(path, {method: "GET"});
        var resp_body = await response.json();
        var hotelDtoList = resp_body.hotelDtoList;

        var scroll_box = document.getElementById("scroll-box");

        for (var i = 0; i < hotelDtoList.length; i++) {
            var hotelDto = hotelDtoList[i];
            var div_row = document.createElement("div");
            div_row.className = "row";

            var hotel_name = document.createElement("label");
            hotel_name.className = "hotel-name";
            hotel_name.textContent = hotelDto.name;
            var hotel_roomCount = document.createElement("label");
            hotel_roomCount.className = "hotel-roomCount";
            hotel_roomCount.textContent = hotelDto.availableRoomCount + " / " + hotelDto.roomCount;
            var button = document.createElement("button");
            button.textContent = "View";

            button.addEventListener("click", function(event) {
                openPopup(event);
            });

            div_row.appendChild(hotel_name);
            div_row.appendChild(hotel_roomCount);
            div_row.appendChild(button);

            scroll_box.appendChild(div_row);
        }
    } catch (error) {
        console.error("Error while getting hotels:", error);
    }
}

getHotels();

function openPopup(event) {}


function closePopup() {
    document.getElementById("hotel-info").style.display = "none";
}
