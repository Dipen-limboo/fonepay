package fonepay.ecommerce.ecomerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fonepay.ecommerce.ecomerce.Service.SalesMetricsService;
import fonepay.ecommerce.ecomerce.dto.SalesMetricsDto;

@RestController
@RequestMapping("/api/metrics")
public class SalesMetricsController {

    @Autowired
    private SalesMetricsService salesMetricsService;

    @GetMapping("/sales")
    public ResponseEntity<SalesMetricsDto> getSalesMetrics(@RequestParam String period) {
        try {
            SalesMetricsDto metrics = salesMetricsService.getSalesMetrics(period);
            return ResponseEntity.ok(metrics);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
