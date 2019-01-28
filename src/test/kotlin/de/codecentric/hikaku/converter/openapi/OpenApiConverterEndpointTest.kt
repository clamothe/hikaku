package de.codecentric.hikaku.converter.openapi

import de.codecentric.hikaku.endpoints.Endpoint
import de.codecentric.hikaku.endpoints.HttpMethod.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class OpenApiConverterEndpointTest {

    @Test
    fun `extract two different paths`() {
        //given
        val file = Paths.get(OpenApiConverterEndpointTest::class.java.classLoader.getResource("openapi/endpoints/endpoints_two_different_paths.yaml").toURI())
        val implementation = setOf(
                Endpoint("/todos", GET),
                Endpoint("/tags", GET)
        )

        //when
        val specification = OpenApiConverter(file).conversionResult

        //then
        assertThat(specification).isEqualTo(implementation)
    }

    @Test
    fun `extract two paths of which one is nested`() {
        //given
        val file = Paths.get(OpenApiConverterEndpointTest::class.java.classLoader.getResource("openapi/endpoints/endpoints_two_nested_paths.yaml").toURI())
        val implementation = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos/{id}", GET)
        )

        //when
        val specification = OpenApiConverter(file).conversionResult

        //then
        assertThat(specification).isEqualTo(implementation)
    }

    @Test
    fun `extract all http methods`() {
        //given
        val file = Paths.get(OpenApiConverterEndpointTest::class.java.classLoader.getResource("openapi/endpoints/endpoints_all_http_methods.yaml").toURI())
        val implementation = setOf(
                Endpoint("/todos", GET),
                Endpoint("/todos", POST),
                Endpoint("/todos", PUT),
                Endpoint("/todos", PATCH),
                Endpoint("/todos", DELETE),
                Endpoint("/todos", HEAD),
                Endpoint("/todos", OPTIONS),
                Endpoint("/todos", TRACE)
        )

        //when
        val specification = OpenApiConverter(file).conversionResult

        //then
        assertThat(specification).isEqualTo(implementation)
    }
}
