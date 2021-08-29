package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@AllArgsConstructor
@RestController
@RequestMapping(value = "reservations")
public class ReservationController extends BaseRestController {

    private final ReservationService reservationService;

    @ApiOperation(value = "Books a reservation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation booked"),
            @ApiResponse(code = 400, message = "Invalid guest/schedule ID")})
    @Produces(MediaType.APPLICATION_JSON)
    @PostMapping(value = "book")
    public ResponseEntity<Void> bookReservation(@RequestBody @Valid CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }

    @ApiOperation(value = "Finds a reservation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation returned"),
            @ApiResponse(code = 400, message = "Invalid reservation ID")        })
    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping(value = "{reservationId}")
    public ResponseEntity<ReservationDTO> findReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }

    @ApiOperation(value = "Cancels a reservation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation cancelled"),
            @ApiResponse(code = 400, message = "Invalid reservation ID")})
    @Produces(MediaType.APPLICATION_JSON)
    @DeleteMapping(value = "cancel/{reservationId}")
    public ResponseEntity<ReservationDTO> cancelReservation(@PathVariable Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }

    @ApiOperation(value = "Reschedules a reservation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Reservation rescheduled"),
            @ApiResponse(code = 400, message = "Invalid reservation/schedule ID")})
    @Produces(MediaType.APPLICATION_JSON)
    @PutMapping(value = "reschedule/{reservationId}/{scheduleId}")
    public ResponseEntity<ReservationDTO> rescheduleReservation(@PathVariable Long reservationId, @PathVariable Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }
}
