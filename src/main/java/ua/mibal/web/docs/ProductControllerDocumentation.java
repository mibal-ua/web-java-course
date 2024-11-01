package ua.mibal.web.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import ua.mibal.service.model.ProductForm;
import ua.mibal.web.dto.ConstraintViolationProblemDetails;
import ua.mibal.web.dto.ProductDto;

import java.util.List;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public interface ProductControllerDocumentation {

    @Operation(summary = "Get all Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products received")
    })
    List<ProductDto> getAll();

    @Operation(summary = "Get one Product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product received"),
            @ApiResponse(responseCode = "404", description = "Product not found",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
    })
    ProductDto getOne(Long id);

    @Operation(summary = "Create new Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Passed form to create is not valid",
                    content = @Content(schema = @Schema(implementation = ConstraintViolationProblemDetails.class))),
            @ApiResponse(responseCode = "409", description = "Conflict in unique fields",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
    })
    ProductDto create(ProductForm product);

    @Operation(summary = "Update/create Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated"),
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Passed form to update is not valid",
                    content = @Content(schema = @Schema(implementation = ConstraintViolationProblemDetails.class))),
    })
    ResponseEntity<ProductDto> update(Long id, ProductForm product);

    @Operation(summary = "Delete Product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deleted"),
    })
    void delete(Long id);
}
