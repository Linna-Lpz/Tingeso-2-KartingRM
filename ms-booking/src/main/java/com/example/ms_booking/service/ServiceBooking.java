package com.example.ms_booking.service;

import com.example.ms_booking.entity.EntityBooking;
import com.example.ms_booking.entity.EntityClient;
import com.example.ms_booking.repository.RepoBooking;
import com.example.ms_booking.repository.RepoClient;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class ServiceBooking {
    @Autowired
    RepoBooking repoBooking;
    @Autowired
    RepoClient repoClient;
    @Autowired
    RestTemplate restTemplate;

    public void saveBooking(EntityBooking booking) {
        // Establecer tarifa base y duración de la reserva
        Integer lapsOrMaxTimeAllowed = booking.getLapsOrMaxTimeAllowed();
        Integer basePrice = getBasePrice(lapsOrMaxTimeAllowed);
        booking.setBasePrice(String.valueOf(basePrice));
        Integer duration = getDuration(lapsOrMaxTimeAllowed);
        booking.setBookingTimeEnd(booking.getBookingTime().plusMinutes(duration));

        // Establecer descuentos
        Integer numOfPeople = booking.getNumOfPeople();
        String[] clientsRut = booking.getClientsRUT().split(",");
        Integer discount; // Tarifa con el descuento aplicado
        StringBuilder discountsList = new StringBuilder();
        StringBuilder discountsListType = new StringBuilder();
        String bookingDayMonth = booking.getBookingDate().format(DateTimeFormatter.ofPattern("dd-MM"));

        if (1 == numOfPeople || numOfPeople == 2) {
            for (String rut : clientsRut) {
                EntityClient client = repoClient.findByClientRUT(rut);
                if (client != null) {
                    discount = discountForVisitsPerMonth(client.getVisitsPerMonth(), basePrice);
                    if (Objects.equals(discount, basePrice)) {
                        discountsListType.append("no,");
                    } else {
                        discountsListType.append("visitas,");
                    }
                    client.setVisitsPerMonth(client.getVisitsPerMonth() + 1);
                    discountsList.append(discount).append(",");
                } else {
                    discountsList.append(basePrice).append(",");// Si el cliente no está registrado, no se aplica descuento
                    discountsListType.append("no,");
                }
            }
        }
        if (3 <= numOfPeople && numOfPeople <= 5) {
            int bDayDiscountApplied = 0;
            for (String rut : clientsRut) {
                EntityClient client = repoClient.findByClientRUT(rut);
                if (client != null) {
                    String clientBirthday = client.getClientBirthday();
                    if (bDayDiscountApplied == 0 && !Objects.equals(discountForBirthday(clientBirthday, bookingDayMonth, basePrice), basePrice)) {
                        discount = discountForBirthday(clientBirthday, bookingDayMonth, basePrice);
                        bDayDiscountApplied = 1;
                        discountsListType.append("cumpleaños,");
                    } else {
                        discount = discountForVisitsPerMonth(client.getVisitsPerMonth(), basePrice);
                        if (Objects.equals(discount, basePrice)) {
                            discount = discountForNumOfPeople(numOfPeople, basePrice); // Descuento por grupo de 3 a 5 personas
                            discountsListType.append("integrantes,");
                        } else {
                            discountsListType.append("visitas,");
                        }
                    }
                    client.setVisitsPerMonth(client.getVisitsPerMonth() + 1);
                    discountsList.append(discount).append(",");
                } else {
                    discountsList.append(basePrice).append(",");// Si el cliente no está registrado, no se aplica descuento
                    discountsListType.append("no,");
                }
            }
        }
        if (6 <= numOfPeople && numOfPeople <= 10) {
            int bDayDiscountApplied = 0;
            for (String rut : clientsRut) {
                EntityClient client = repoClient.findByClientRUT(rut);
                if (client != null) {
                    String clientBirthday = client.getClientBirthday();
                    if (bDayDiscountApplied < 3 && !Objects.equals(discountForBirthday(clientBirthday, bookingDayMonth, basePrice), basePrice)) {
                        discount = discountForBirthday(clientBirthday, bookingDayMonth, basePrice);
                        bDayDiscountApplied += 1;
                        discountsListType.append("cumpleaños,");
                    } else {
                        discount = discountForVisitsPerMonth(client.getVisitsPerMonth(), basePrice);
                        if (!Objects.equals(discount, basePrice)) {
                            discountsListType.append("visitas,");
                        } else {
                            discount = discountForNumOfPeople(numOfPeople, basePrice); // Descuento por el grupo de 6 a 10 personas
                            discountsListType.append("integrantes,");
                        }
                    }
                    client.setVisitsPerMonth(client.getVisitsPerMonth() + 1);
                    discountsList.append(discount).append(",");
                } else {
                    discountsList.append(basePrice).append(",");// Si el cliente no está registrado, no se aplica descuento
                    discountsListType.append("no,");
                }
            }
        }
        if (11 <= numOfPeople && numOfPeople <= 15) {
            for (String rut : clientsRut) {
                EntityClient client = repoClient.findByClientRUT(rut);
                if (client != null) {
                    discount = discountForNumOfPeople(numOfPeople, basePrice); // Descuento por grupo de 11 a 15 personas
                    client.setVisitsPerMonth(client.getVisitsPerMonth() + 1);
                    discountsListType.append("integrantes,");
                    discountsList.append(discount).append(",");
                } else {
                    discountsList.append(basePrice).append(",");// Si el cliente no está registrado, no se aplica descuento
                    discountsListType.append("no,");
                }
            }
        }

        booking.setDiscounts(discountsListType.toString()); // Lista de descuentos aplicados (cumpleaños, visitas, integrantes)
        booking.setTotalPrice(discountsList.toString()); // Lista de precios con descuento

        // Establecer estado de la reserva
        booking.setBookingStatus("sin confirmar");

        // Guardar la reserva
        repoBooking.save(booking);
    }

    /**
     * Método para obtener el precio base de acuerdo a la cantidad de vueltas o tiempo máximo permitido
     * @param lapsOrMaxTimeAllowed vueltas o tiempo máximo permitido
     * @return ResponseEntity<Integer>
     */
    public Integer getBasePrice(Integer lapsOrMaxTimeAllowed) {
        return restTemplate.getForObject("http://ms-rates/rates/basePrice/" + lapsOrMaxTimeAllowed, Integer.class);
    }

    /**
     * Método para obtener la duración de acuerdo a la cantidad de vueltas o tiempo máximo permitido
     * @param lapsOrMaxTimeAllowed vueltas o tiempo máximo permitido
     * @return ResponseEntity<Integer>
     */
    public Integer getDuration(Integer lapsOrMaxTimeAllowed) {
        return restTemplate.getForObject("http://ms-rates/rates/duration/" + lapsOrMaxTimeAllowed, Integer.class);
    }

    public Integer discountForNumOfPeople(int numOfPeople, int basePrice) {
        return restTemplate.getForObject("http://ms-discounts1/discounts1/discount/" + numOfPeople + "/" + basePrice, Integer.class);
    }

    public Integer discountForVisitsPerMonth(int visitsPerMonth, int basePrice) {
        return restTemplate.getForObject("http://ms-discounts2/discounts2/discount/" + visitsPerMonth + "/" + basePrice, Integer.class);
    }

    public Integer discountForBirthday(String clientBirthday, String bookingDayMonth, int basePrice) {
        return restTemplate.getForObject("http://ms-special-rates/special-rates/discount/" + clientBirthday + "/" + bookingDayMonth + "/" + basePrice, Integer.class);
    }

    /**
     * Método para obtener una lista de reservas
     * @return lista de reservas
     */
    public List<EntityBooking> getBookings() {
        return repoBooking.findByBookingStatusContains("confirmada");
    }

    /**
     * Método para confirmar una reserva
     * @param bookingId id de la reserva
     */
    public void confirmBooking(Long bookingId) {
        EntityBooking booking = repoBooking.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + bookingId));
        booking.setBookingStatus("confirmada");
        repoBooking.save(booking);
    }

    /**
     * Método para cancelar una reserva
     * @param bookingId id de la reserva
     */
    public void cancelBooking(Long bookingId) {
        EntityBooking booking = repoBooking.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + bookingId));
        booking.setBookingStatus("cancelada");
        repoBooking.save(booking);
    }

}
