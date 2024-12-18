package ua.mibal.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.mibal.service.CosmoCatService;
import ua.mibal.web.dto.CosmoCatDto;
import ua.mibal.web.mapper.CosmoCatMapper;

import java.util.List;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order/cosmo-cats")
public class CosmoCatController {
    private final CosmoCatService service;
    private final CosmoCatMapper mapper;
    
    @GetMapping
    public List<CosmoCatDto> getCosmoCats() {
        return mapper.toDto(
                service.getCosmoCats()
        );
    }
}
