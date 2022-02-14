package dev.trodrigues.ead.authuser.configs

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport

@Configuration
class ResolversConfig : WebMvcConfigurationSupport() {

    override fun addArgumentResolvers(argumentResolvers: MutableList<HandlerMethodArgumentResolver>) {
        argumentResolvers.add(SpecificationArgumentResolver())
        argumentResolvers.add(PageableHandlerMethodArgumentResolver())
        super.addArgumentResolvers(argumentResolvers)
    }

}
