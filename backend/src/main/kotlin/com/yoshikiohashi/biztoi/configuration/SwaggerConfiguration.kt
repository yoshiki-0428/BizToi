package com.yoshikiohashi.biztoi.configuration

import org.reactivestreams.Publisher
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux
import org.springframework.web.reactive.config.ResourceHandlerRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Configuration
@EnableSwagger2WebFlux
class SwaggerConfiguration: WebFluxConfigurer {
    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .genericModelSubstitutes(Mono::class.java, Flux::class.java, Publisher::class.java)
            .apiInfo(ApiInfoBuilder()
                    .description("BizToi API")
                    .title("BizToi")
                    .version("1.0.0")
                    .build()
            )
            .enable(true)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/swagger**")
                .addResourceLocations("classpath:/META-INF/resources/")

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
    }
}