import axios from "axios";

const BOOKING_API_URL = "http://localhost:8090/api/booking";
const CLIENT_API_URL = "http://localhost:8090/api/client";
const REPORT_API_URL = "http://localhost:8090/api/report";

// ------------------ Booking ------------------
function saveBooking(data) {
    return axios.post(`${BOOKING_API_URL}/save`, data);
}

function getBooking(){
    return axios.get(`${BOOKING_API_URL}/getBookings`);
}

function getBookingByUserRut(userRut){
    return axios.get(`${BOOKING_API_URL}/getBookings/${userRut}`);
}

function getBookingTimesByDate(date){
    return axios.get(`${BOOKING_API_URL}/getBookingTimesByDate/${date}`);
}

function getBookingTimesEndByDate(date){
    return axios.get(`${BOOKING_API_URL}/getBookingTimesEndByDate/${date}`)
}

function confirmBooking(bookingId){
    return axios.post(`${BOOKING_API_URL}/confirm/${bookingId}`);
}

function cancelBooking(bookingId){
    return axios.post(`${BOOKING_API_URL}/cancel/${bookingId}`);
}

function getConfirmedBookings(){
    return axios.get(`${BOOKING_API_URL}/getConfirmedBookings`);
}

function sendVoucherByEmail(bookingId){
    return axios.post(`${BOOKING_API_URL}/send/${bookingId}`);
}

// ------------------ Report ------------------

function getBookingsForReport1(lapsOrTimeMax){
    return axios.get(`${REPORT_API_URL}/getBookingsForReport1/${lapsOrTimeMax}`);
}

function getIncomesForLapsOfMonth(){
    return axios.get(`${REPORT_API_URL}/getTotalForReport1`);
}

function getBookingsForReport2(people){
    return axios.get(`${REPORT_API_URL}/getBookingsForReport2/${people}`);
}

function getIncomesForNumOfPeopleOfMonth(){
    return axios.get(`${REPORT_API_URL}/getTotalForReport2`);
}

// ------------------ Client ------------------
function saveClient(client){
    return axios.post(`${CLIENT_API_URL}/save`, client);
}

function getClientByRut(rut){
    return axios.get(`${CLIENT_API_URL}/get/${rut}`);
}


// ------------------ Export -------------------

export default {
    saveBooking,
    getBooking,
    getBookingByUserRut,
    getBookingTimesByDate,
    getBookingTimesEndByDate,
    getConfirmedBookings,
    confirmBooking,
    cancelBooking,
    saveClient,
    sendVoucherByEmail,
    getBookingsForReport1,
    getIncomesForLapsOfMonth,
    getBookingsForReport2,
    getIncomesForNumOfPeopleOfMonth,
    getClientByRut
};