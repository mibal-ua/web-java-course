package ua.mibal.web.mapper;

import org.mapstruct.Mapper;
import ua.mibal.domain.CosmoCat;
import ua.mibal.web.dto.CosmoCatDto;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Mapper(componentModel = SPRING)
public interface CosmoCatMapper {

    List<CosmoCatDto> toDto(List<CosmoCat> cosmoCats);
}
