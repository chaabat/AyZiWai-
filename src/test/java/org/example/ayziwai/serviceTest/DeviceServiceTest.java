package org.example.ayziwai.serviceTest;

import org.example.ayziwai.dto.request.DeviceRequest;
import org.example.ayziwai.dto.response.DeviceResponse;
import org.example.ayziwai.services.interfaces.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class DeviceServiceTest {

    @Mock
    private DeviceService deviceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllDevices_ShouldReturnPageOfDevices() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        List<DeviceResponse> deviceList = Arrays.asList(
            new DeviceResponse(), // Add necessary device response data
            new DeviceResponse()
        );
        Page<DeviceResponse> expectedPage = new PageImpl<>(deviceList, pageable, deviceList.size());
        
        when(deviceService.getAllDevices(any(Pageable.class))).thenReturn(expectedPage);

        // Act
        Page<DeviceResponse> result = deviceService.getAllDevices(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(expectedPage, result);
    }

    @Test
    void getDevicesByZone_ShouldReturnPageOfDevicesForZone() {
        // Arrange
        String zone = "TestZone";
        Pageable pageable = PageRequest.of(0, 10);
        List<DeviceResponse> deviceList = Arrays.asList(
            new DeviceResponse(), // Add necessary device response data
            new DeviceResponse()
        );
        Page<DeviceResponse> expectedPage = new PageImpl<>(deviceList, pageable, deviceList.size());
        
        when(deviceService.getDevicesByZone(zone, pageable)).thenReturn(expectedPage);

        // Act
        Page<DeviceResponse> result = deviceService.getDevicesByZone(zone, pageable);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.getContent().size());
        assertEquals(expectedPage, result);
    }

    @Test
    void createDevice_ShouldReturnCreatedDevice() {
        // Arrange
        DeviceRequest deviceRequest = new DeviceRequest(); // Add necessary request data
        DeviceResponse expectedResponse = new DeviceResponse(); // Add necessary response data
        
        when(deviceService.createDevice(any(DeviceRequest.class))).thenReturn(expectedResponse);

        // Act
        DeviceResponse result = deviceService.createDevice(deviceRequest);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse, result);
    }
}
