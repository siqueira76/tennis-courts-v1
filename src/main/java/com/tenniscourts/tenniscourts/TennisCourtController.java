package com.tenniscourts.tenniscourts;

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
@RequestMapping(value = "TennisCourts")
public class TennisCourtController extends BaseRestController {

    private final TennisCourtService tennisCourtService;

    @ApiOperation(value = "Registers a tennis court")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tennis court registered"),
            @ApiResponse(code = 400, message = "Invalid data")})
    @Produces(MediaType.APPLICATION_JSON)
    @PostMapping
    public ResponseEntity<Void> addTennisCourt(@RequestBody @Valid           TennisCourtDTO tennisCourtDTO) {
        return ResponseEntity.created(locationByEntity(tennisCourtService.addTennisCourt(tennisCourtDTO).getId())).build();
    }

    @ApiOperation(value = "Finds a Tennis court")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tennis court returned"),
            @ApiResponse(code = 400, message = "Invalid tennis court ID")})
    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping(value = "{tennisCourtId}")
    public ResponseEntity<TennisCourtDTO> findTennisCourtById(@PathVariable Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtById(tennisCourtId));
    }

    @ApiOperation(value = "Finds schedules with a Tennis court ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Tennis court returned"),
            @ApiResponse(code = 400, message = "Invalid tennis court ID")})
    @Produces(MediaType.APPLICATION_JSON)
    @GetMapping(value = "byIdTennisCourtId/{tennisCourtId}")
    public ResponseEntity<TennisCourtDTO> findTennisCourtWithSchedulesById(@PathVariable Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId));
    }
}
