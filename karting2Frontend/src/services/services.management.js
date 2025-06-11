import axios from "axios";

const GATEWAY_INTERNAL_URL = "http://gateway-service-service";

const BOOKING_API_URL = `${GATEWAY_INTERNAL_URL}/ms-booking`;
const CLIENT_API_URL = `${GATEWAY_INTERNAL_URL}/ms-client`;
const VOUCHER_API_URL = `${GATEWAY_INTERNAL_URL}/ms-voucher`;
const RACK_API_URL = `${GATEWAY_INTERNAL_URL}/ms-rack`;
const REPORT_API_URL = `${GATEWAY_INTERNAL_URL}/ms-report`;

// ------------------ Booking ------------------
function saveBooking(data) {
    return axios.post(`${BOOKING_API_URL}/save`, data);
}

function getBooking(){
    return axios.get(`${BOOKING_API_URL}/getBookings`);
}

function getBookingByUserRut(rut){
    return axios.get(`${BOOKING_API_URL}/getBookingsByUser/${rut}`);
}

function getTimesByDate(date){
    return axios.get(`${BOOKING_API_URL}/getTimesByDate/${date}`);
}

function getTimesEndByDate(date){
    return axios.get(`${BOOKING_API_URL}/getTimesEndByDate/${date}`)
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

// ------------------ Voucher ------------------

function sendVoucherByEmail (bookingId){
    return axios.post(`${VOUCHER_API_URL}/send/${bookingId}`);
}
// ------------------ Rack ------------------
function getBookingsForRack(month, year){
    return axios.get(`${RACK_API_URL}/getBookingsForRack/${month}/${year}`);
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
    getTimesByDate,
    getTimesEndByDate,
    getConfirmedBookings,
    confirmBooking,
    cancelBooking,
    saveClient,
    sendVoucherByEmail,
    getBookingsForRack,
    getBookingsForReport1,
    getIncomesForLapsOfMonth,
    getBookingsForReport2,
    getIncomesForNumOfPeopleOfMonth,
    getClientByRut
};