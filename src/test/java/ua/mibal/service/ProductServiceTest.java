package ua.mibal.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import ua.mibal.domain.Product;
import ua.mibal.repository.ProductRepository;
import ua.mibal.service.exception.EntityNotFoundException;
import ua.mibal.service.mapper.ProductMapper;
import ua.mibal.service.model.ProductForm;
import ua.mibal.test.annotation.UnitTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@UnitTest
class ProductServiceTest {
    private final ProductRepository repository = mock();
    private final ProductService service = new ProductService(repository, ProductMapper.getInstance());
    
    @Captor
    private ArgumentCaptor<Product> productArg;

    @Test
    void getAll() {
        given(Product.builder()
                .id(1L)
                .name("Product 1")
                .description("Description 1")
                .price(100.0)
                .build());

        List<Product> actual = service.getAll();

        assertThat(actual).hasSize(1);
        Product actualProduct = actual.get(0);

        assertThat(actualProduct.getId()).isEqualTo(1L);
        assertThat(actualProduct.getName()).isEqualTo("Product 1");
        assertThat(actualProduct.getDescription()).isEqualTo("Description 1");
        assertThat(actualProduct.getPrice()).isEqualTo(100.0);
    }

    @Test
    void getAll_empty() {
        givenEmptyRepository();

        List<Product> actual = service.getAll();

        assertThat(actual).isEmpty();
    }

    @Test
    void getOneById() {
        given(Product.builder()
                .id(1L)
                .name("Product 1")
                .description("Description 1")
                .price(100.0)
                .build());

        Product actual = service.getOneById(1L);

        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getName()).isEqualTo("Product 1");
        assertThat(actual.getDescription()).isEqualTo("Description 1");
        assertThat(actual.getPrice()).isEqualTo(100.0);
    }

    @Test
    void getOneById_shouldThrowOnNotFound() {
        givenEmptyRepository();

        assertThrows(
                EntityNotFoundException.class,
                () -> service.getOneById(1L)
                
        );
    }

    @Test
    void create() {
        ProductForm form = ProductForm.builder()
                .name("Product 1")
                .description("Description 1")
                .price(100.0)
                .build();
        
        service.create(form);
        
        verify(repository)
                .save(productArg.capture());
        Product actual = productArg.getValue();

        assertThat(actual.getName()).isEqualTo("Product 1");
        assertThat(actual.getDescription()).isEqualTo("Description 1");
        assertThat(actual.getPrice()).isEqualTo(100.0);
    }

    @Test
    void update_shouldUpdateOnExisting() {
        given(Product.builder()
                .id(1L)
                .name("Product 1")
                .description("Description 1")
                .price(100.0)
                .build());
        ProductForm form = ProductForm.builder()
                .name("Product 2")
                .description("Description 2")
                .price(200.0)
                .build();

        service.update(1L, form);
        
        Product actual = repository.findById(1L).get();
        assertThat(actual.getName()).isEqualTo("Product 2");
        assertThat(actual.getDescription()).isEqualTo("Description 2");
        assertThat(actual.getPrice()).isEqualTo(200.0);
    }

    @Test
    void update_shouldCreateOnNew() {
        givenEmptyRepository();
        ProductForm form = ProductForm.builder()
                .name("Product 2")
                .description("Description 2")
                .price(200.0)
                .build();

        service.update(1L, form);
        
        verify(repository)
                .save(productArg.capture());

        Product actual = productArg.getValue();
        assertThat(actual.getName()).isEqualTo("Product 2");
        assertThat(actual.getDescription()).isEqualTo("Description 2");
        assertThat(actual.getPrice()).isEqualTo(200.0);
    }

    @Test
    void deleteById() {
        given(Product.builder()
                .id(1L)
                .name("Product 1")
                .description("Description 1")
                .price(100.0)
                .build());
        
        assertDoesNotThrow(
                () -> service.deleteById(1L)
        );
        
        verify(repository)
                .deleteById(1L);
    }


    @Test
    void deleteById_onNotFoundShouldNotThrow() {
        givenEmptyRepository();

        assertDoesNotThrow(
                () -> service.deleteById(1L)
        );

        verify(repository)
                .deleteById(1L);
    }

    private void givenEmptyRepository() {
        reset(repository);

        when(repository.findAll()).thenReturn(List.of());
        when(repository.findById(any()))
                .thenReturn(Optional.empty());
    }

    private void given(Product... products) {
        when(repository.findAll()).thenReturn(List.of(products));

        when(repository.findById(any()))
                .thenReturn(Optional.empty());
        for (Product product : products) {
            when(repository.findById(product.getId()))
                    .thenReturn(Optional.of(product));
        }
    }
}
