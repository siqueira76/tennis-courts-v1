package com.tenniscourts.schedules;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "schedules")
public class ScheduleController extends BaseRestController {

    private final ScheduleService scheduleService;

    @ApiOperation(value = "Creates a schedule")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Schedule created"),
            @ApiResponse(code = 400, message = "Invalid guest/schedule ID")})
    @Produces(MediaType.APPLICATION_JSON)
    @PostMapping
    public ResponseEntity<Void> addScheduleTennisCourt(@RequestBody @Valid CreateScheduleRequestDTO createScheduleRequestDTO) {
        return ResponseEntity.created(locationByEntity(scheduleService.addSchedule(createScheduleRequestDTO.getTennisCourtId(), createScheduleRequestDTO).getId())).build();
    }

    @ApiOperation(value = "Finds a schedule")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Schedule returned"),
            @ApiResponse(code = 400, message = "Invalid schedule ID")})
    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping(value = "list/{startDate}/{endDate}")
    public ResponseEntity<List<ScheduleDTO>> findSchedulesByDates(@PathVariable @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate startDate,
                                                                  @PathVariable @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate endDate) {
        return ResponseEntity.ok(scheduleService.findSchedulesByDates(LocalDateTime.of(startDate, LocalTime.of(0, 0)), LocalDateTime.of(endDate, LocalTime.of(23, 59))));
    }

    @ApiOperation(value = "Finds a schedule")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Schedule returned"),
            @ApiResponse(code = 400, message = "Invalid schedule ID")})
    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping(value = "{scheduleId}")
    public ResponseEntity<ScheduleDTO> findByScheduleId(@PathVariable Long scheduleId) {
        return ResponseEntity.ok(scheduleService.findSchedule(scheduleId));
    }
}
